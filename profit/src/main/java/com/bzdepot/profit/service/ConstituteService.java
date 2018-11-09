package com.bzdepot.profit.service;

import com.bzdepot.profit.mapper.ConstituteMapper;
import com.bzdepot.profit.model.Constitute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstituteService {

    @Autowired
    private ConstituteMapper constituteMapper;

    /**
     * 更新价格构成
     * @param constitute
     * @return
     */
    public int update(Constitute constitute){
        int Ok = 0;
        if(constitute.getId() == null){
            Ok = constituteMapper.insertSelective(constitute);
        }else {
            Ok = constituteMapper.updateByPrimaryKeySelective(constitute);
        }
        return Ok;
    }

    /**
     * 查询单条的报价构成
     * @param offerId
     * @param sellerId
     * @return
     */
    public Constitute findConstitute(Long offerId,Long sellerId){
        return constituteMapper.selectByOfferIdAndSellerId(offerId,sellerId);
    }

}
