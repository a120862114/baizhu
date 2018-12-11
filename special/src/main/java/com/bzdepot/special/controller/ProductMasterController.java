package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.model.ProductMaster;
import com.bzdepot.special.service.ProductMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/product/master")
public class ProductMasterController {

    private final static Logger loger = LoggerFactory.getLogger(ProductMasterController.class);

    @Autowired
    private ProductMasterService productMasterService;

    /**
     * 更新产品配置主参数API接口
     * @param productMaster
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateProductMasterApi(@Valid @ModelAttribute ProductMaster productMaster, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(productMasterService.updateProductMaster(productMaster) > 0){
            return JsonReturn.SetMsg(0,"更新产品配置主参数成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新产品配置主参数失败!","");
    }

    /**
     * 获取单条的产品配置参数的API接口
     * @param sellerId
     * @param classId
     * @return
     */
    @GetMapping(value = "/find/{sellerId}/{classId}")
    public Object findProductMasterApi(@PathVariable("sellerId") Long sellerId,@PathVariable("classId") Long classId){
        if(sellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号不能为空!","");
        }
        if(classId == null){
            return JsonReturn.SetMsg(10010,"产品分类不能为空!","");
        }
        ProductMaster productMaster = productMasterService.findOneBySellerIdAndClassId(sellerId,classId);
        if(productMaster != null){
            return JsonReturn.SetMsg(0,"获取产品配置主参数成功!",productMaster);
        }
        return JsonReturn.SetMsg(10011,"此产品分类下暂无产品配置参数数据!","");
    }
}
