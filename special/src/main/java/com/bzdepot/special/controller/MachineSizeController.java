package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.model.MachineSize;
import com.bzdepot.special.service.MachineSizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/machine/size")
public class MachineSizeController {

    private final static Logger loger = LoggerFactory.getLogger(MachineSizeController.class);

    @Autowired
    private MachineSizeService machineSizeService;

    /**
     * 更新机器大小配置API接口
     * @param machineSize
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateSize(@Valid @ModelAttribute MachineSize machineSize, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(machineSizeService.updateMachineSize(machineSize) > 0){
            return JsonReturn.SetMsg(0,"更新机器大小配置成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新机器大小配置失败!","");
    }

    /**
     * 获取机器大小配置列表API接口
     * @return
     */
    @GetMapping(value = "/list")
    public Object listSize(){
        List<MachineSize> machineSizes = machineSizeService.listMachineSize();
        if(machineSizes != null && machineSizes.size() > 0){
            return JsonReturn.SetMsg(0,"获取机器大小配置列表成功!",machineSizes);
        }
        return JsonReturn.SetMsg(10011,"获取机器大小配置列表失败!","");
    }

    /**
     * 删除机器大小配置API接口
     * @param sizeId
     * @return
     */
    @GetMapping(value = "/del/{sizeId}")
    public Object deleteSize(@PathVariable("sizeId") Long sizeId){
        if(sizeId == null){
            return JsonReturn.SetMsg(10010,"机器大小配置编号不能为空!","");
        }
        if(machineSizeService.deleteMachineSize(sizeId) > 0){
            return JsonReturn.SetMsg(0,"删除机器大小配置成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除机器大小配置失败!","");
    }
}
