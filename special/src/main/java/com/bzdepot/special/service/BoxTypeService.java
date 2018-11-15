package com.bzdepot.special.service;

import com.bzdepot.special.mapper.BoxTypeMapper;
import com.bzdepot.special.model.BoxType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxTypeService {

    @Autowired
    private BoxTypeMapper boxTypeMapper;

    /**
     * 更新盒型
     * @param boxType
     * @return
     */
    public int updateBoxType(BoxType boxType){
        int Code = 0;
        if(boxType.getId() == null){
            Code = boxTypeMapper.insertSelective(boxType);
        }else{
            Code = boxTypeMapper.updateByPrimaryKeySelective(boxType);
        }
        return Code;
    }

    /**
     * 查询单条的盒型数据
     * @param id
     * @return
     */
    public BoxType findBoxTypeData(Long id){
        return boxTypeMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除单条的盒型数据
     * @param id
     * @return
     */
    public int deleteById(Long id){
        return boxTypeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取盒型数据接口列表
     * @param sellerId
     * @return
     */
    public List<BoxType> findBoxTypeBySellerId(Long sellerId){
        return boxTypeMapper.selectBySellerId(sellerId);
    }
}


