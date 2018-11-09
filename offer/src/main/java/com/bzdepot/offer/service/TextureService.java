package com.bzdepot.offer.service;

import com.bzdepot.offer.mapper.TextureMapper;
import com.bzdepot.offer.model.Texture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TextureService {

    @Autowired
    private TextureMapper textureMapper;

    /*
    *  添加材质数据
    * */
    public Long addTexture(Texture texture){
        texture.setCreateTime(new Date().getTime());
        texture.setUpdateTime(new Date().getTime());
        textureMapper.insertSelective(texture);
        return  texture.getId();
    }

    /*
    *  修改材质数据
    * */
    public int editTexture(Texture texture){
        texture.setUpdateTime(new Date().getTime());
       return textureMapper.updateByPrimaryKeySelective(texture);
    }

    /**
     * 获取单条的材质数据
     * @param sellerId
     * @param title
     * @return
     */
    public Texture findTextureByTitle(Long sellerId,String title){
        return textureMapper.selectByTitleAndSellerId(sellerId,title);
    }
}
