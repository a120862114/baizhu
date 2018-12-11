package com.bzdepot.special.service;

import com.bzdepot.common.util.UserUtil;
import com.bzdepot.special.mapper.PaperTextureMapper;
import com.bzdepot.special.model.PaperTexture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames="PaperTexture")
public class PaperTextureService {

    @Autowired
    private PaperTextureMapper paperTextureMapper;

    /**
     * 更新纸张材质
     * @param paperTexture
     * @return
     */
    @CacheEvict(allEntries = true)
    public int updatePaperTexture(PaperTexture paperTexture){

        paperTexture.settBs(paperTexture.getNames());

        if(paperTexture.getId() == null){
            return paperTextureMapper.insertSelective(paperTexture);
        }else {
            return paperTextureMapper.updateByPrimaryKeySelective(paperTexture);
        }
    }

    /**
     * 根据材质名称查找当前商家是否存在这个名称的材质
     * @param sellerId
     * @param names
     * @return
     */
    public PaperTexture findPaperTextureByNames(Long sellerId,String names){
        return paperTextureMapper.selectBySellerIdAndNames(sellerId,names);
    }
    /**
     * 查询单条的纸张材质
     * @param id
     * @return
     */
    @Cacheable()
    public PaperTexture findPaperTexture(Long id){
        return paperTextureMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除纸张材质
     * @param id
     * @return
     */
    @CacheEvict(allEntries = true)
    public int deletePaperTexture(Long id){
        Long sellerId = UserUtil.getId();
        PaperTexture paperTexture = this.findPaperTexture(id);
        if(paperTexture == null){
            return 0;
        }
        if(paperTexture.getSellerId() != sellerId){
            return 0;
        }
        return paperTextureMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取纸张材质列表
     * @return
     */
    @Cacheable()
    public List<PaperTexture> listPaperTexture(Long UserId){
        return paperTextureMapper.selectBySellerId(UserId);
    }
}
