package com.bzdepot.special.service;

import com.bzdepot.common.util.UserUtil;
import com.bzdepot.special.mapper.PaperGramMapper;
import com.bzdepot.special.model.PaperGram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperGramService {

    @Autowired
    private PaperGramMapper paperGramMapper;

    /**
     * 更新纸张克重
     * @param paperGram
     * @return
     */
    public int updatePaperGram(PaperGram paperGram){
        paperGram.setSellerId(UserUtil.getId());
        if(paperGram.getId() == null){
            return paperGramMapper.insertSelective(paperGram);
        }else{
            return paperGramMapper.updateByPrimaryKeySelective(paperGram);
        }
    }

    /**
     * 查询单条的纸张克重
     * @param id
     * @return
     */
    public PaperGram findPaperGram(Long id){
        return paperGramMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除单条纸张克重
     * @param id
     * @return
     */
    public int deletePaperGram(Long id){
        //验证删除的数据是否属于当前商家
        PaperGram paperGram = this.findPaperGram(id);
        if(paperGram == null){
            return 0;
        }
        if(paperGram.getSellerId() != UserUtil.getId()){
            return 0;
        }
        return paperGramMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取纸张克重列表
     * @return
     */
    public List<PaperGram> listPaperGram(){
        Long sellerId = UserUtil.getId();
        return paperGramMapper.selectBySellerId(sellerId);
    }
}
