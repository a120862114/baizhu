package com.bzdepot.special.service;

import com.bzdepot.special.bo.ProductTextureBo;
import com.bzdepot.special.mapper.ProductTextureMapper;
import com.bzdepot.special.model.ProductTexture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class ProductTextureService {

    private final static Logger loger = LoggerFactory.getLogger(ProductTextureService.class);

    @Autowired
    private ProductTextureMapper productTextureMapper;

    /**
     * 更新材质映射数据
     * @param productTextureBo
     * @return
     */
    @Transactional
    public int updateProductTexture(ProductTextureBo productTextureBo){
        int Code = 0;
        //验证材质编号数据是否为空
        if(productTextureBo.getTextureId() == null){
            return Code;
        }
        try {
            for(int i = 0; i < productTextureBo.getTextureId().length;i++){
                Long TextureId = productTextureBo.getTextureId()[i];
                if(TextureId == null){
                    break;
                }
                //验证是否已经存在
                ProductTexture productTexture = this.findOneByMore(productTextureBo.getSellerId(),productTextureBo.getPconfigId(),TextureId);
                //只有不存在时在执行添加操作
                if(productTexture == null){
                    ProductTexture DataMap = new ProductTexture();
                    DataMap.setSellerId(productTextureBo.getSellerId());
                    DataMap.setPconfigId(productTextureBo.getPconfigId());
                    DataMap.setTextureId(TextureId);
                    Code = productTextureMapper.insertSelective(DataMap);
                    if(Code < 1){
                        break;
                    }
                }
            }
            if(Code == 0){
                throw new RuntimeException("更新产品配置的材质相关失败，事务回滚!");
            }
        }catch (Exception e){
            loger.error("更新产品配置的材质相关失败，事务回滚!");
            loger.error(e.toString());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Code;
    }

    /**
     * 根据Id主键查询映射数据
     * @param id
     * @return
     */
    public ProductTexture findOneById(Long id){
        return productTextureMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据多条件获取单条的材质映射数据
     * @param sellerId
     * @param pconfigId
     * @param textureId
     * @return
     */
    public ProductTexture findOneByMore(Long sellerId, Long pconfigId,Long textureId){
        return productTextureMapper.selectByPconfigIdAndSellerIdAndTextureId(sellerId,pconfigId,textureId);
    }

    /**
     * 获取材质映射的列表
     * @param sellerId
     * @param pconfigId
     * @return
     */
    public List<ProductTexture> findListByMore(Long sellerId, Long pconfigId){
        return productTextureMapper.selectByPconfigIdAndSellerId(sellerId,pconfigId);
    }

    /**
     * 设置默认的材质映射数据
     * @param sellerId
     * @param pconfigId
     * @param textureId
     * @return
     */
    public int setDefaultProductTexture(Long sellerId, Long pconfigId,Long textureId){
        ProductTexture productTexture = new ProductTexture();
        productTexture.setSellerId(sellerId);
        productTexture.setPconfigId(pconfigId);
        productTexture.setTextureId(textureId);
        return productTextureMapper.setIsDefault(productTexture);
    }

    /**
     * 移除材质映射数据
     * @param sellerId
     * @param pconfigId
     * @param textureId
     * @return
     */
    public int deleteProductTextureByMore(Long sellerId,Long pconfigId,Long textureId){
        return productTextureMapper.deleteByMore(sellerId,pconfigId,textureId);
    }
}
