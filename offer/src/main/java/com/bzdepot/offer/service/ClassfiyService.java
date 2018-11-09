package com.bzdepot.offer.service;

import com.bzdepot.offer.mapper.ClassfiyMapper;
import com.bzdepot.offer.mapper.TextureGroupMapper;
import com.bzdepot.offer.model.Classfiy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ClassfiyService {

    @Autowired
    private ClassfiyMapper classfiyMapper;

    @Autowired
    private TextureGroupMapper textureGroupMapper;

    /*添加分类信息*/
    public Long addClassfiy(Classfiy classfiy){
        classfiy.setCreateTime(new Date().getTime());
        classfiy.setUpdateTime(new Date().getTime());
        classfiyMapper.insertSelective(classfiy);
        return classfiy.getId();
    }

    /*修改分类信息*/
    public int updateClassfiy(Classfiy classfiy){
        classfiy.setUpdateTime(new Date().getTime());
        return classfiyMapper.updateByPrimaryKeySelective(classfiy);
    }

    /*删除分类方法*/
    public int delClassfiy(Long id,Long userid){
        int nums = textureGroupMapper.countTextureGroup(userid,id);
        if(nums > 0){
            return -1;
        }
        return classfiyMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询单条的分类数据
     * @param sellerId
     * @param title
     * @return
     */
    public Classfiy findClassfiyByTitle(Long sellerId,String title){
        return classfiyMapper.selectByTitle(sellerId,title);
    }
}
