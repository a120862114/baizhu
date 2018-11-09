package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.model.PaperBrand;
import com.bzdepot.special.service.PaperBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/paper/brand")
public class PaperBrandController {

    private final static Logger loger = LoggerFactory.getLogger(PaperBrandController.class);

    @Autowired
    private PaperBrandService paperBrandService;

    /**
     * 更新纸张品牌API接口
     * @param paperBrand
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateBrand(@Valid @ModelAttribute PaperBrand paperBrand, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        int Ok = paperBrandService.UpdatePaperBrand(paperBrand);
        if(Ok > 0){
           return JsonReturn.SetMsg(0,"更新纸张品牌成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新纸张品牌失败!","");
    }

    /**
     * 获取当前商家纸张品牌列表API接口
     * @return
     */
    @GetMapping(value = "/list")
    public Object listBrand(){
       List<PaperBrand> paperBrands = paperBrandService.listPaperBrand();
       if(paperBrands != null && paperBrands.size() > 0){
           return JsonReturn.SetMsg(0,"获取纸张品牌成功!",paperBrands);
       }
       return JsonReturn.SetMsg(10011,"获取纸张品牌失败!","");
    }

    /**
     * 删除当前商家的单条纸张品牌API接口
     * @param brandId
     * @return
     */
    @GetMapping(value = "/del/{brandId}")
    public Object deleteBrand(@PathVariable("brandId") Long brandId){
        if(brandId == null){
            return JsonReturn.SetMsg(10010,"纸张品牌编号不能为空!","");
        }
        if(paperBrandService.deletePaperBrand(brandId) > 0){
            return JsonReturn.SetMsg(0,"删除纸张品牌成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除纸张品牌失败!","");
    }
}
