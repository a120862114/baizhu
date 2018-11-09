package com.bzdepot.special.service;

import com.bzdepot.common.util.UserUtil;
import com.bzdepot.special.mapper.PaperDrumMapper;
import com.bzdepot.special.model.PaperDrum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperDrumService {

    @Autowired
    private PaperDrumMapper paperDrumMapper;

    /**
     * 更新纸张卷筒
     * @param paperDrum
     * @return
     */
    public int updatePaperDrum(PaperDrum paperDrum){
        paperDrum.setSellerId(UserUtil.getId());
        if(paperDrum.getId() == null){
            return paperDrumMapper.insertSelective(paperDrum);
        }else {
            return paperDrumMapper.updateByPrimaryKeySelective(paperDrum);
        }
    }

    /**
     * 查询单条纸张卷筒数据
     * @param id
     * @return
     */
    public PaperDrum findPaperDrum(Long id){
        return paperDrumMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除单条全通数据，必须删除属于自己的数据
     * @param id
     * @return
     */
    public int deletePaperDrum(Long id){
        Long sellerId = UserUtil.getId();
        PaperDrum paperDrum = this.findPaperDrum(id);
        if(paperDrum == null){
            return 0;
        }
        if(paperDrum.getSellerId() != sellerId){
            return 0;
        }
        return paperDrumMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询卷筒数据列表
     * @return
     */
    public List<PaperDrum> listPaperDrum(){
        return paperDrumMapper.selectBySellerId(UserUtil.getId());
    }
}
