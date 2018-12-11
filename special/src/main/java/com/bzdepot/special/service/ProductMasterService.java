package com.bzdepot.special.service;

import com.bzdepot.special.mapper.ProductMasterMapper;
import com.bzdepot.special.model.ProductMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductMasterService {

    @Autowired
    private ProductMasterMapper productMasterMapper;

    /**
     * 更新产品配置的主参数
     * @param productMaster
     * @return
     */
    public int updateProductMaster(ProductMaster productMaster){
        int Code = 0;
        if(productMaster.getSellerId() == null || productMaster.getClassId() == null){
            return Code;
        }
        ProductMaster ResData = this.findOneBySellerIdAndClassId(productMaster.getSellerId(),productMaster.getClassId());
        if(ResData == null){
            productMaster.setCreateTime(new Date().getTime());
            productMaster.setUpdateTime(new Date().getTime());
            Code = productMasterMapper.insertSelective(productMaster);
        }else{
            productMaster.setUpdateTime(new Date().getTime());
            Code = productMasterMapper.updateByPrimaryKeySelective(productMaster);
        }
        return Code;
    }

    /**
     * 获取单条的配置
     * @param sellerId
     * @param pconfigId
     * @return
     */
    public ProductMaster findOneBySellerIdAndClassId(Long sellerId,Long pconfigId){
        return productMasterMapper.selectBySellerIdAndClassId(sellerId,pconfigId);
    }
}
