package com.bzdepot.special.service;

import com.bzdepot.common.util.UserUtil;
import com.bzdepot.special.mapper.PaperOtherSizeMapper;
import com.bzdepot.special.model.PaperOtherSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperOtherSizeService {

    @Autowired
    private PaperOtherSizeMapper paperOtherSizeMapper;

    /**
     * 更新纸张其他尺寸
     * @param paperOtherSize
     * @return
     */
    public int updatePaperOtherSize(PaperOtherSize paperOtherSize){
        paperOtherSize.setSellerId(UserUtil.getId());
        if(paperOtherSize.getId() == null){
            return paperOtherSizeMapper.insertSelective(paperOtherSize);
        }else {
            return paperOtherSizeMapper.updateByPrimaryKeySelective(paperOtherSize);
        }
    }

    /**
     * 查询单条的纸张其他尺寸
     * @param id
     * @return
     */
    public PaperOtherSize findPaperOtherSize(Long id){
        return paperOtherSizeMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除单条的纸张其他尺寸，只能删除自己的数据
     * @param id
     * @return
     */
    public int deletePaperOtherSize(Long id){
        Long sellerId = UserUtil.getId();
        PaperOtherSize paperOtherSize = this.findPaperOtherSize(id);
        if(paperOtherSize == null){
            return 0;
        }
        if(sellerId != paperOtherSize.getSellerId()){
            return 0;
        }
        return paperOtherSizeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取纸张其他尺寸数据列表
     * @return
     */
    public List<PaperOtherSize> listPaperOtherSize(){
        return paperOtherSizeMapper.selectBySellerId(UserUtil.getId());
    }
}
