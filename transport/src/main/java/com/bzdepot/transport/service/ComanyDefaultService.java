package com.bzdepot.transport.service;

import com.bzdepot.common.util.UserUtil;
import com.bzdepot.transport.mapper.ComanyDefaultMapper;
import com.bzdepot.transport.model.ComanyDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComanyDefaultService {

    @Autowired
    private ComanyDefaultMapper comanyDefaultMapper;

    /**
     * 更新默认快递
     * @param comanyDefault
     * @return
     */
    public int update(ComanyDefault comanyDefault){
       ComanyDefault res = comanyDefaultMapper.selectBySellerId(UserUtil.getId());
       int Ok = 0;
       if(res == null){
            Ok = comanyDefaultMapper.insertSelective(comanyDefault);
       }else {
           Ok = comanyDefaultMapper.updateBySellerIdSelective(comanyDefault);
       }
       return Ok;
    }

    //查询商家默认快递
    public ComanyDefault findDefault(Long sellerId){
       return comanyDefaultMapper.selectBySellerId(sellerId);
    }
}
