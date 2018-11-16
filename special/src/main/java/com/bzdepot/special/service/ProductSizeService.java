package com.bzdepot.special.service;

import com.bzdepot.special.mapper.ProductSizeMapper;
import com.bzdepot.special.model.ProductSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSizeService {

    @Autowired
    private ProductSizeMapper productSizeMapper;

    /**
     * 更新尺寸
     * @param productSize
     * @return
     */
    public int updateroductSize(ProductSize productSize){
        int Code = 0;
        if(productSize.getId() == null){
            Code = productSizeMapper.insertSelective(productSize);
        }else{
            Code = productSizeMapper.updateByPrimaryKeySelective(productSize);
        }
        return Code;
    }

    /**
     * 查找单条尺寸数据
     * @param id
     * @return
     */
    public ProductSize findProductSizeOne(Long id){
        return productSizeMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除产品尺寸
     * @param id
     * @return
     */
    public int deleteProductSize(Long id){
        return productSizeMapper.deleteByPrimaryKey(id);
    }


}
