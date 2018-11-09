package com.bzdepot.transport.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.transport.model.TransportType;
import com.bzdepot.transport.service.TransportTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/type/")
public class TransportTypeController {

    @Autowired
    private TransportTypeService transportTypeService;

    @GetMapping(value = "/list")
    public Object getList(){
        List<TransportType> ResultData = transportTypeService.getTransportTypeList();
        if(ResultData.size() > 0){
            return JsonReturn.SetMsg(0,"获取运输类型成功!",ResultData);
        }
        return JsonReturn.SetMsg(10011,"获取列表失败!","");
    }

}
