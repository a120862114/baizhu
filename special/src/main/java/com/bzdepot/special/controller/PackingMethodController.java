package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.model.PackingMethod;
import com.bzdepot.special.service.PackingMethodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/paper/packing")
public class PackingMethodController {

    private final static Logger loger = LoggerFactory.getLogger(PackingMethodController.class);

    @Autowired
    private PackingMethodService packingMethodService;

    /**
     * 更新纸张包装方式API接口
     * @param packingMethod
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updatePackingMethod(@Valid @ModelAttribute PackingMethod packingMethod, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(packingMethodService.updatePackingMethod(packingMethod) > 0){
            return JsonReturn.SetMsg(0,"更新纸张包装方式成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新纸张包装失败!","");
    }

    /**
     * 获取纸张包装方式API接口
     * @return
     */
    @GetMapping(value = "/list")
    public Object listPackingMethod(){
        List<PackingMethod> packingMethods = packingMethodService.listPackingMethod();
        if(packingMethods != null && packingMethods.size() > 0){
            return JsonReturn.SetMsg(0,"获取纸张包装方式列表成功!",packingMethods);
        }
        return JsonReturn.SetMsg(10011,"获取纸张包装方式列表失败!","");
    }

    /**
     * 删除纸张包装方式的API接口
     * @param packingId
     * @return
     */
    @GetMapping(value = "/del/{packingId}")
    public Object deletePackingMethod(@PathVariable("packingId") Long packingId){
        if(packingId == null){
            return JsonReturn.SetMsg(10010,"纸张包装方式编号不能为空!","");
        }
        if(packingMethodService.deletePackingMethod(packingId) > 0){
            return JsonReturn.SetMsg(0,"删除纸张包装方式成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除纸张包装方式失败!","");
    }

}
