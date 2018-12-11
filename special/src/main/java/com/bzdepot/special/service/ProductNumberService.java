package com.bzdepot.special.service;

import com.bzdepot.special.mapper.ProductNumberMapper;
import com.bzdepot.special.model.ProductNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductNumberService {

    @Autowired
    private ProductNumberMapper productNumberMapper;

    /**
     * 更新产品配置的数量数据
     * @param productNumber
     * @return
     */
    public int updateProductNumber(ProductNumber productNumber){
        int Code = 0;
        if(productNumber.getId() == null){
            Code = productNumberMapper.insertSelective(productNumber);
        }else{
            Code = productNumberMapper.updateByPrimaryKeySelective(productNumber);
        }
        return Code;
    }

    /**
     * 查询单条产品配置的数量
     * @param id
     * @return
     */
    public ProductNumber findProductNumberOne(Long id){
        return productNumberMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除单条的产品配置的数量
     * @param id
     * @return
     */
    public int deleteProductNumber(Long id){
        return productNumberMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询产品配置的数量列表
     * @param sellerId
     * @return
     */
    public List<ProductNumber> findProductNumberList(Long sellerId,Long pconfigId){
        return productNumberMapper.selectBySellerId(sellerId,pconfigId);
    }

    /**
     * 根据多条件查找单条的产品配置数量数据
     * @param mapCondition
     * @return
     */
    public ProductNumber findProductNumberMoreCondition(ProductNumber mapCondition){
        return productNumberMapper.findByMoreCondition(mapCondition);
    }

    /**
     * 设置数量的默认
     * @param sellerId
     * @param pconfigId
     * @param nums
     * @return
     */
    public int setIsDefaultNumber(Long sellerId,Long pconfigId,Integer nums){
        ProductNumber productNumber = new ProductNumber();
        productNumber.setSellerId(sellerId);
        productNumber.setPconfigId(pconfigId);
        productNumber.setNums(nums);
        return productNumberMapper.setIsDefault(productNumber);
    }
}
