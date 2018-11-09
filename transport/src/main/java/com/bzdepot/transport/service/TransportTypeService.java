package com.bzdepot.transport.service;

import com.bzdepot.transport.mapper.TransportTypeMapper;
import com.bzdepot.transport.model.TransportType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportTypeService {

    @Autowired
    private TransportTypeMapper transportTypeMapper;

    /**
     * 获取快递类型
     * @return
     */
    public List<TransportType> getTransportTypeList(){
        return transportTypeMapper.findAll();
    }
}
