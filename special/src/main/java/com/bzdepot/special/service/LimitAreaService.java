package com.bzdepot.special.service;

import com.bzdepot.common.util.UserUtil;
import com.bzdepot.special.mapper.LimitAreaMapper;
import com.bzdepot.special.model.LimitArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LimitAreaService {

    @Autowired
    private LimitAreaMapper limitAreaMapper;

    /**
     * 更新限制面积
     * @param limitArea
     * @return
     */
    public int updateLimitArea(LimitArea limitArea){
        limitArea.setSellerId(UserUtil.getId());
        if(limitArea.getId() == null){
            return limitAreaMapper.insertSelective(limitArea);
        }else {
            return limitAreaMapper.updateByPrimaryKeySelective(limitArea);
        }
    }

    /**
     * 获取面积限制单条数据
     * @param id
     * @return
     */
    public LimitArea findLimitArea(Long id){
        return limitAreaMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除面积限制单体数据
     * @param id
     * @return
     */
    public int deleteLimitArea(Long id){
        Long sellerId = UserUtil.getId();
        LimitArea limitArea = this.findLimitArea(id);
        if(limitArea == null){
            return 0;
        }
        if(sellerId != limitArea.getSellerId()){
            return 0;
        }
        return limitAreaMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取面积限制列表
     * @return
     */
    public List<LimitArea> listLimitArea(){
        return limitAreaMapper.selectBySellerId(UserUtil.getId());
    }
}
