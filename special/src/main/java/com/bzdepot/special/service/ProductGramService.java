package com.bzdepot.special.service;

import com.bzdepot.special.bo.ProductGramBo;
import com.bzdepot.special.mapper.ProductGramMapper;
import com.bzdepot.special.model.ProductGram;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class ProductGramService {

    private final static Logger loger = LoggerFactory.getLogger(ProductGramService.class);

    @Autowired
    private ProductGramMapper productGramMapper;

    /**
     * 更新添加厚度数据
     * @param productGramBo
     * @return
     */
    @Transactional
    public int updateProductGram(ProductGramBo productGramBo){
        int Code = 0;
        if(productGramBo.getGramId() == null){
            return Code;
        }
        try {
            for(int i = 0; i < productGramBo.getGramId().length; i++){
                Long gramId = productGramBo.getGramId()[i];
                ProductGram PostData = new ProductGram();
                PostData.setSellerId(productGramBo.getSellerId());
                PostData.setPtId(productGramBo.getTextureId());
                PostData.setGramId(gramId);
                //验证数据是否存在,如存在则添加
                ProductGram productGram = this.findOneByMore(PostData.getSellerId(),PostData.getPtId(),PostData.getGramId());
                if(productGram == null){
                    Code = productGramMapper.insertSelective(PostData);
                    if(Code < 1){
                        break;
                    }
                }
            }
            if(Code == 0){
                throw new RuntimeException("更新产品配置的厚度失败，事务回滚!");
            }
        }catch (Exception e){
            loger.error("更新产品配置的厚度失败，事务回滚!");
            loger.error(e.toString());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Code;
    }

    /**
     * 查询单条的数据
     * @param sellerId
     * @param textureId
     * @param gramId
     * @return
     */
    public ProductGram findOneByMore(Long sellerId,Long textureId,Long gramId){
        return productGramMapper.selectByMore(sellerId,textureId,gramId);
    }

    /**
     * 获取厚度列表
     * @param sellerId
     * @param textureId
     * @return
     */
    public List<ProductGram> findListByMore(Long sellerId,Long textureId){
        return productGramMapper.selectForList(sellerId,textureId);
    }

    /**
     * 删除厚度映射数据
     * @param sellerId
     * @param textureId
     * @param gramId
     * @return
     */
    public int deleteProductGram(Long sellerId,Long textureId,Long gramId){
        return productGramMapper.deleteByMore(sellerId,textureId,gramId);
    }

    /**
     * 设置默认的厚度数据
     * @param sellerId
     * @param textureId
     * @param gramId
     * @return
     */
    public int setDefaultProductGram(Long sellerId,Long textureId,Long gramId){
        return productGramMapper.setIsDefault(sellerId,textureId,gramId);
    }
}
