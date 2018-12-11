package com.bzdepot.special.service;

import com.bzdepot.special.bo.ProductTechnologyBo;
import com.bzdepot.special.mapper.ProductTechnologyMapper;
import com.bzdepot.special.mapper.productTechnologySonMapper;
import com.bzdepot.special.model.ProductTechnology;
import com.bzdepot.special.model.productTechnologySon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@CacheConfig(cacheNames = "ProductTechnologyService")
@Service
public class ProductTechnologyService {

    private final static Logger loger = LoggerFactory.getLogger(ProductTechnologyService.class);

    @Autowired
    private ProductTechnologyMapper productTechnologyMapper;

    @Autowired
    private productTechnologySonMapper productTechnologySonMappers;

    /**
     * 更新产品配置的材质相关
     * @return
     */
    @CacheEvict(allEntries = true)
    @Transactional
    public int updateProductTechnologyAll(ProductTechnologyBo productTechnologyBo){
        System.out.println("产品配置的工艺参数===============================================");
        System.out.println(productTechnologyBo);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        int Code = 0;
        try {
            //遍历需要添加的材质主数据
            for(int i = 0; i < productTechnologyBo.getTechnology().size(); i++){
                ProductTechnology productTechnology = productTechnologyBo.getTechnology().get(i);
                productTechnology.setSellerId(productTechnologyBo.getSellerId());
                productTechnology.setPconfigId(productTechnologyBo.getPconfigId());
                //验证数据是否已经添加过了，如没添加过则添加
                ProductTechnology DataRes = this.findProductTechnologyByMoreConditon(productTechnologyBo.getSellerId(),productTechnologyBo.getPconfigId(),productTechnology.getTechnologyId());
                if(DataRes == null){
                    Code = productTechnologyMapper.insertSelective(productTechnology);
                    if(Code > 0){
                        //添加子数据工艺选择
                        for(int a = 0; a < productTechnology.getTechnologySon().size(); a++){
                            productTechnologySon productTechnologySonData = productTechnology.getTechnologySon().get(a); //添加工艺选择数据
                            productTechnologySonData.setSellerId(productTechnology.getSellerId());
                            productTechnologySonData.setClassId(productTechnology.getPconfigId());
                            productTechnologySonData.setTechnologyId(productTechnology.getTechnologyId());
                            Code = productTechnologySonMappers.insertSelective(productTechnologySonData);
                            if(Code == 0){
                                break;
                            }
                        }
                        if(Code == 0){
                            loger.error("添加工艺选择数据失败!");
                            throw new RuntimeException("添加工艺选择数据失败!");
                        }
                    }
                }else{
                    //修改
                    if(productTechnology.getId() != null){
                        Code = productTechnologyMapper.updateByPrimaryKeySelective(productTechnology);
                    }
                }
                if(Code == 0){
                    break;
                }
            }
            if(Code == 0){
                loger.error("添加产品配置的工艺映射数据失败!");
                throw new RuntimeException("添加产品配置的工艺映射数据失败!");
            }
        }catch (Exception e){
            loger.error("更新产品配置的材工艺相关失败，事务回滚!");
            loger.error(e.toString());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Code;
    }

    /**
     * 根据主键删除材质主数据
     * @param id
     * @return
     */
    @CacheEvict(allEntries = true)
    public int deleteProductTechnologyMaster(Long id){
        return productTechnologyMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据多条件删除产品材质的主数据
     * @param sellerId
     * @param pconfigId
     * @param technologyId
     * @return
     */
    @CacheEvict(allEntries = true)
    public int deleteProductTechnologyByMoreCondition(Long sellerId,Long pconfigId,Long technologyId){
        return productTechnologyMapper.deleteBySellerIdAndPconfigIdAndTechnologyId(sellerId,pconfigId,technologyId);
    }

    /**
     * 根据主键查找单条的产品配置的材质主数据映射
     * @param id
     * @return
     */
    public ProductTechnology findProductTechnologyByIdForOne(Long id){
        return productTechnologyMapper.selectByPrimaryKey(id);
    }

    /**
     * 根绝多条件查找单条的产品配置的材质数据
     * @param sellerId
     * @param pconfigId
     * @param technologyId
     * @return
     */
    public ProductTechnology findProductTechnologyByMoreConditon(Long sellerId,Long pconfigId,Long technologyId){
        return productTechnologyMapper.selectOneBySellerIdPconfigIdAndTechnologyId(sellerId,pconfigId,technologyId);
    }

    /**
     * 更新产品配置的工艺
     * @param productTechnology
     * @return
     */
    @CacheEvict(allEntries = true)
    public int updateProductTechnology(ProductTechnology productTechnology){
        return productTechnologyMapper.updateByPrimaryKeySelectiveForDefaul(productTechnology);
    }

    /**
     * 获取工艺的映射数据列表
     * @param sellerId
     * @param pconfigId
     * @return
     */
    @Cacheable()
    public List<ProductTechnology> findList(Long sellerId,Long pconfigId){
        return productTechnologyMapper.selectBySellerIdAndPconfigIdForList(sellerId,pconfigId);
    }

    /**
     * 获取工艺选择列表
     * @param sellerId
     * @param pconfigId
     * @param technologyId
     * @return
     */
    @Cacheable()
    public List<productTechnologySon> findAttrList(Long sellerId,Long pconfigId,Long technologyId){
        return productTechnologySonMappers.selectAttrData(sellerId,pconfigId,technologyId);
    }
}
