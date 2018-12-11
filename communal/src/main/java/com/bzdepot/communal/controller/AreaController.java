package com.bzdepot.communal.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.communal.model.Area;
import com.bzdepot.communal.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping(value = "/province")
    public Object getProvinceList(){
        Integer ParentId = -1;
       List<Area> ResData = areaService.selectCityParentList(ParentId);
       if(ResData != null && ResData.size() > 0){
           return JsonReturn.SetMsg(0,"获取全国省名称成功111!",ResData);
       }
       return JsonReturn.SetMsg(10011,"获取全国省名称失败111!","");
    }
}
