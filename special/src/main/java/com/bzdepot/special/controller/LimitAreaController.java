package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.model.LimitArea;
import com.bzdepot.special.service.LimitAreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/technology/limit")
public class LimitAreaController {

    private final static Logger loger = LoggerFactory.getLogger(LimitAreaController.class);

    @Autowired
    private LimitAreaService limitAreaService;

    /**
     * 更新限制面积API接口
     * @param limitArea
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateLimitArea(@Valid @ModelAttribute LimitArea limitArea, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(limitAreaService.updateLimitArea(limitArea) > 0){
            return JsonReturn.SetMsg(0,"更新加工费面积限制数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新加工费面积限制数据失败!","");
    }

    /**
     * 获取面积限制列表API接口
     * @return
     */
    @GetMapping(value = "/list")
    public Object listLimitArea(){
        List<LimitArea> limitAreas = limitAreaService.listLimitArea();
        if(limitAreas != null && limitAreas.size() > 0){
            return JsonReturn.SetMsg(0,"获取面积限制列表成功!",limitAreas);
        }
        return JsonReturn.SetMsg(10011,"获取面积限制列表失败!","");
    }

    /**
     * 删除面积限制API接口
     * @param limitId
     * @return
     */
    @GetMapping(value = "/del/{limitId}")
    public Object deleteLimitArea(@PathVariable("limitId") Long limitId){
        if(limitId == null){
            return JsonReturn.SetMsg(10010,"面积限制编号不能为空!","");
        }
        if(limitAreaService.deleteLimitArea(limitId) > 0){
            return JsonReturn.SetMsg(0,"删除面积限制数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除面积限制数据失败!","");
    }
}
