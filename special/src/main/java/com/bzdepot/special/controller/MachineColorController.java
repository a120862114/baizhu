package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.model.MachineColor;
import com.bzdepot.special.service.MachineColorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/machine/color")
public class MachineColorController {

    private final static Logger loger = LoggerFactory.getLogger(MachineColorController.class);

    @Autowired
    private MachineColorService machineColorService;

    /**
     * 更新机器颜色配置API接口
     * @param machineColor
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateColor(@Valid @ModelAttribute MachineColor machineColor, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(machineColorService.updateColor(machineColor) > 0){
            return JsonReturn.SetMsg(0,"更新机器颜色成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新机器颜色失败!","");
    }

    /**
     * 获取机器颜色列表API接口
     * @return
     */
    @GetMapping(value = "/list")
    public Object listColor(){
        List<MachineColor> machineColors = machineColorService.listMachineColor();
        if(machineColors != null && machineColors.size() > 0){
            return JsonReturn.SetMsg(0,"获取机器颜色列表成功!",machineColors);
        }
        return JsonReturn.SetMsg(10011,"获取机器颜色列表失败!","");
    }

    /**
     * 删除机器颜色API接口
     * @param colorId
     * @return
     */
    @GetMapping(value = "/del/{colorId}")
    public Object deleteColor(@PathVariable("colorId") Long colorId){
        if(colorId == null){
            return JsonReturn.SetMsg(10011,"机器颜色编号不能为空!","");
        }
        if(machineColorService.deleteMachineColor(colorId) > 0){
            return JsonReturn.SetMsg(0,"删除机器颜色数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除机器颜色数据失败!","");
    }

}
