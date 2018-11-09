package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.model.PaperSupplier;
import com.bzdepot.special.service.PaperSupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/paper/supplier")
public class PaperSupplierController {

    private final static Logger loger = LoggerFactory.getLogger(PaperSupplierController.class);

    @Autowired
    private PaperSupplierService paperSupplierService;

    /**
     * 更新纸张供应商API接口
     * @param paperSupplier
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateSupplier(@Valid @ModelAttribute PaperSupplier paperSupplier, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(paperSupplierService.updatePaperSupplier(paperSupplier) > 0){
            return JsonReturn.SetMsg(0,"更新纸张供应商成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新纸张供应商失败!","");
    }

    /**
     * 获取纸张供应商API接口
     * @return
     */
    @GetMapping(value = "/list")
    public Object listSupplier(){
        List<PaperSupplier> paperSuppliers = paperSupplierService.listPaperSupplier();
        if(paperSuppliers != null && paperSuppliers.size() > 0){
            return JsonReturn.SetMsg(0,"获取纸张供应商列表!",paperSuppliers);
        }
        return JsonReturn.SetMsg(10011,"获取纸张供应商失败!","");
    }

    /**
     * 删除纸张供应商API接口
     * @param superId
     * @return
     */
    @GetMapping(value = "/del/{superId}")
    public Object deleteSupplier(@PathVariable("superId") Long superId){
        if(superId == null){
            return JsonReturn.SetMsg(10010,"纸张供应商编号不能为空!","");
        }
        if(paperSupplierService.deletePaperSupplier(superId) >  0){
            return JsonReturn.SetMsg(0,"删除纸张供应商成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除纸张供应商失败!","");
    }
}
