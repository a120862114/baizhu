package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.model.CommonColor;
import com.bzdepot.special.service.CommonColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/common/color")
public class CommonColorController {

    @Autowired
    private CommonColorService commonColorService;

    @GetMapping(value = "/list")
    public Object listCommonColor(){
        List<CommonColor> commonColors = commonColorService.findListCommonColor();
        if(commonColors != null && commonColors.size() > 0){
            return JsonReturn.SetMsg(0,"获取颜色成功!",commonColors);
        }
        return JsonReturn.SetMsg(10011,"暂无颜色配置数据!","");
    }
}
