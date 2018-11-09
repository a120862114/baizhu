package com.bzdepot.communal.service;

import com.bzdepot.communal.mapper.ConsigneeAccessMapper;
import com.bzdepot.communal.model.ConsigneeAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsigService {

    @Autowired
    private ConsigneeAccessMapper consigneeAccessMapper;


    public  int saveConsig(ConsigneeAccess consig){
       return   consigneeAccessMapper.insert(consig);
    }


    public  int delConsig(long id){
        return consigneeAccessMapper.deleteByPrimaryKey(id);
    }


    public  int updateConsig(ConsigneeAccess consig){
         return  consigneeAccessMapper.updateByPrimaryKey(consig);
    }


    public  ConsigneeAccess selConsig(long id){
      return   consigneeAccessMapper.selectByPrimaryKey(id);
    }

}
