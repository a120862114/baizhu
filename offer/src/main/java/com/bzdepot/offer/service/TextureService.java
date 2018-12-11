package com.bzdepot.offer.service;

import com.bzdepot.offer.mapper.OfferMapper;
import com.bzdepot.offer.mapper.TextureMapper;
import com.bzdepot.offer.model.Offer;
import com.bzdepot.offer.model.Texture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TextureService {

    @Autowired
    private TextureMapper textureMapper;

    @Autowired
    private OfferMapper offerMapper;

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

    /**
     * 获取单挑材质数据
     * @param sellerId
     * @param classId
     * @param title
     * @return
     */
    public Texture findTextureByClassIdAndSellerIdAndTitleForOne(Long sellerId,Long classId,String title){
        return textureMapper.selectByTitleAndSellerIdAndClassId(sellerId,classId,title);
    }
    /**
     * 删除材质数据,同时MYSQL触发器删除材质对应的产品图片
     * @param sellerId
     * @param classId
     * @param textureId
     * @return
     */
    public int deleteTexture(Long sellerId,Long classId,Long textureId){
       List<Offer> offers = offerMapper.selectBySidCidTidAll(sellerId,classId,textureId);
       if(offers != null && offers.size() > 0){
           return -1;
       }else{
           if(textureMapper.deleteByPrimaryKey(textureId) > 0){
               return 1;
           }
       }
        return 0;
    }

    /**
     * 获取当前商家下所有材质名称
     * @param sellerId
     * @return
     */
    public List<Texture> findTextureList(Long sellerId){
        return textureMapper.selectBySellerId(sellerId);
    }
}
