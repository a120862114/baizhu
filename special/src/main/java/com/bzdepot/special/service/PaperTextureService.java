package com.bzdepot.special.service;

import com.bzdepot.common.util.UserUtil;
import com.bzdepot.special.mapper.PaperTextureMapper;
import com.bzdepot.special.model.PaperTexture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperTextureService {

    @Autowired
    private PaperTextureMapper paperTextureMapper;

    /**
     * 更新纸张材质
     * @param paperTexture
     * @return
     */
    public int updatePaperTexture(PaperTexture paperTexture){
        paperTexture.setSellerId(UserUtil.getId());
        paperTexture.settBs(paperTexture.getNames());
        if(paperTexture.getId() == null){
            return paperTextureMapper.insertSelective(paperTexture);
        }else {
            return paperTextureMapper.updateByPrimaryKeySelective(paperTexture);
        }
    }

    /**
     * 查询单条的纸张材质
     * @param id
     * @return
     */
    public PaperTexture findPaperTexture(Long id){
        return paperTextureMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除纸张材质
     * @param id
     * @return
     */
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
    public List<PaperTexture> listPaperTexture(){
        return paperTextureMapper.selectBySellerId(UserUtil.getId());
    }
}
