package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.model.SpotColor;
import com.bzdepot.special.service.SpotColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/spot/color")
public class SpotColorController {

    @Autowired
    private SpotColorService spotColorService;

    @GetMapping(value = "/list")
    public Object listSpotColorApi(){
        List<SpotColor> spotColors = spotColorService.findSpotColorList();
        if(spotColors != null && spotColors.size() > 0){
            return JsonReturn.SetMsg(0,"获取专色颜色类成功!",spotColors);
        }
        return JsonReturn.SetMsg(10011,"暂无颜色专色类数据!","");
    }
}
