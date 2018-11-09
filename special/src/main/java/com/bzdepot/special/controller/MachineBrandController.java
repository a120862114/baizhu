package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.model.MachineBrand;
import com.bzdepot.special.service.MachineBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/machine/brand")
public class MachineBrandController {

    private final static Logger loger = LoggerFactory.getLogger(MachineBrandController.class);

    @Autowired
    private MachineBrandService machineBrandService;

    /**
     * 更新机器品牌API接口
     * @param machineBrand
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateBrand(@Valid @ModelAttribute MachineBrand machineBrand, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(machineBrandService.updateMachineBrand(machineBrand) > 0){
            return JsonReturn.SetMsg(0,"更新机器品牌成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新机器品牌失败!","");
    }

    /**
     * 获取机器品牌列表API接口
     * @return
     */
    @GetMapping(value = "/list")
    public Object listBrand(){
        List<MachineBrand> machineBrands = machineBrandService.listMachineBrand();
        if(machineBrands != null && machineBrands.size() > 0){
            return JsonReturn.SetMsg(0,"获取机器品牌列表成功!",machineBrands);
        }
        return JsonReturn.SetMsg(10011,"获取机器品牌列表失败!","");
    }

    /**
     * 删除机器品牌API接口
     * @param brandId
     * @return
     */
    @GetMapping(value = "/del/{brandId}")
    public Object deleteBrand(@PathVariable("brandId") Long brandId){
        if(brandId == null){
            return JsonReturn.SetMsg(10010,"机器品牌编号不能为空!","");
        }
        if(machineBrandService.deleteMachineBrand(brandId) > 0){
            return JsonReturn.SetMsg(0,"删除机器品牌成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除机器品牌失败!","");
    }
}
