package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.model.Manufactor;
import com.bzdepot.special.service.ManufactorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/machine/manufactor")
public class ManufactorController {

    private final static Logger loger = LoggerFactory.getLogger(ManufactorController.class);

    @Autowired
    private ManufactorService manufactorService;

    /**
     * 更新机器厂家API接口
     * @param manufactor
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateManufactor(@Valid @ModelAttribute Manufactor manufactor, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(manufactorService.updateManufactor(manufactor) > 0){
            return JsonReturn.SetMsg(0,"更新机器所属厂家成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新机器所属厂家失败!","");
    }

    /**
     * 获取机器厂家列表API接口
     * @return
     */
    @GetMapping(value = "/list")
    public Object listManufactor(){
        List<Manufactor> manufactors = manufactorService.listManufactor();
        if(manufactors != null && manufactors.size() > 0){
            return JsonReturn.SetMsg(0,"获取机器厂家列表成功!",manufactors);
        }
        return JsonReturn.SetMsg(10011,"获取机器厂家列表失败!","");
    }

    /**
     * 删除机器厂家API接口
     * @param factorId
     * @return
     */
    @GetMapping(value = "/del/{factorId}")
    public Object deleteFactor(@PathVariable("factorId") Long factorId){
        if(factorId == null){
            return JsonReturn.SetMsg(10010,"机器厂家编号不能为空!","");
        }
        if(manufactorService.deleteManufactor(factorId) > 0){
            return JsonReturn.SetMsg(0,"删除机器厂家成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除机器厂家失败!","");
    }
}
