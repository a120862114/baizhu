package com.bzdepot.communal.controller;


import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.communal.model.ConsigneeAccess;
import com.bzdepot.communal.service.ConsigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/consig")
public class ConsigneeController {

    @Autowired
    private ConsigService consigService;



    @RequestMapping(value = "savaConsig", method =RequestMethod.POST)
    public Object savaConsig(@RequestBody ConsigneeAccess consig){


        java.util.Date dt = new Date();
        System.out.println(dt.toString());   //java.util.Date的含义
        long lSysTime1 = dt.getTime() / 1000;   //得到秒数，Date类型的getTime()返回毫秒数

        consig.setCreateTime(lSysTime1);
        int i=  consigService.saveConsig(consig);
        if(i<0){
            return JsonReturn.SetMsg(0,"新增用户异常！","");
        }else {
            return JsonReturn.SetMsg(0,"新增用户成功！","");
        }


    }

    @RequestMapping(value = "delConsig",method = RequestMethod.GET)
    public Object delConsig(long id){
        int i= consigService.delConsig(id);
        if(i<0){
            return JsonReturn.SetMsg(0,"删除用户异常！","");
        }else {

            return JsonReturn.SetMsg(0,"删除用户成功！","");
        }
    }

    @RequestMapping(value = "updadelConsig",method = RequestMethod.POST)
    public Object updadelConsig(@RequestBody ConsigneeAccess consig){
        java.util.Date dt = new Date();
        System.out.println(dt.toString());   //java.util.Date的含义
        long lSysTime1 = dt.getTime() / 1000;   //得到秒数，Date类型的getTime()返回毫秒数

        consig.setUpdateTime(lSysTime1);
        int i= consigService.updateConsig(consig);
        if(i<0){
            return JsonReturn.SetMsg(0,"更新用户异常！","");
        }else {

            return JsonReturn.SetMsg(0,"更新用户成功！","");
        }
    }

    @RequestMapping(value = "selConsig",method = RequestMethod.GET)
    public Object selConsig(long id){





        ConsigneeAccess consigneeAccess =  consigService.selConsig(id);
        if(consigneeAccess!=null){
            return JsonReturn.SetMsg(0,"查询用户成功！",consigneeAccess);
        }else {
            return JsonReturn.SetMsg(0,"删除用户异常！",consigneeAccess);
        }
    }
}
