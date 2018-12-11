package com.bzdepot.offer.service;

import com.bzdepot.offer.mapper.TextureGroupMapper;
import com.bzdepot.offer.mapper.TextureMapper;
import com.bzdepot.offer.model.TextureGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TextureGroupService {

    @Autowired
    private TextureGroupMapper textureGroupMapper;

    @Autowired
    private TextureMapper textureMapper;

    /*
    *  添加材质分组
    * */
    public Long addTextureGroup(TextureGroup textureGroup){
        textureGroup.setCreateTime(new Date().getTime());
        textureGroup.setUpdateTime(new Date().getTime());
        textureGroupMapper.insertSelective(textureGroup);
        return textureGroup.getId();
    }

    /*
    *  修改材质分组
    * */
    public int editTextureGroup(TextureGroup textureGroup){
        textureGroup.setUpdateTime(new Date().getTime());
        return textureGroupMapper.updateByPrimaryKeySelective(textureGroup);
    }

    /*
     *  关联查询获取分组与分类信息
     * */
    public List<TextureGroup> findAllClassGroupAndJoin(Long SellerId,Long ClassId){
        List<TextureGroup> ResultData = textureGroupMapper.findJionGroupData(SellerId,ClassId);
        return ResultData;
    }

    /*
    *  删除材质分组方法
    * */
    public int delTextureGroup(Long ClassId,Long SellerId,Long Id){
        int nums = textureMapper.countTexture(ClassId,SellerId,Id);
        if(nums > 0){
            return -1;
        }
        return textureGroupMapper.deleteByPrimaryKey(Id);
    }

    /**
     * 获取单条的材质分组
     * @param sellerId
     * @param groupName
     * @return
     */
    public TextureGroup findTextureGroupByGroupName(Long sellerId,String groupName){
       return textureGroupMapper.selectBySellerIdAndGroupName(sellerId,groupName);
    }

    /**
     * 获取单条的材质分组
     * @param sellerId
     * @param groupName
     * @return
     */
    public TextureGroup findTextureGroupByGroupNameMore(Long sellerId,Long classId,String groupName){
        return textureGroupMapper.countTextureGroupByMore(sellerId,classId,groupName);
    }
}
