package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.model.ProductNumber;
import com.bzdepot.special.service.ProductNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/product/number")
public class ProductNumberController {

    private final static Logger loger = LoggerFactory.getLogger(ProductNumberController.class);

    @Autowired
    private ProductNumberService productNumberService;

    /**
     * 更新产品配置数量的API接口
     * @param productNumber
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateProductNumberApi(@Valid @ModelAttribute ProductNumber productNumber, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        ProductNumber mapCondition = new ProductNumber();
        mapCondition.setSellerId(productNumber.getSellerId());
        mapCondition.setNums(productNumber.getNums());
        mapCondition.setPconfigId(productNumber.getPconfigId());
        ProductNumber productNumberResult = productNumberService.findProductNumberMoreCondition(mapCondition);
        if(productNumber.getId() == null && productNumberResult != null){
            return JsonReturn.SetMsg(10010,"数量值已经存在，请勿重复添加!","");
        }
        if(productNumber.getId() != null && productNumberResult != null && productNumberResult.getId() != productNumber.getId()){
            return JsonReturn.SetMsg(10010,"数量值已经存在，请勿重复更新！","");
        }
        if(productNumberService.updateProductNumber(productNumber) > 0){
            return JsonReturn.SetMsg(0,"更新产品配置数量成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新产品配置的数量失败!","");
    }

    /**
     * 获取产品配置的数量列表的API接口
     * @param sellerId
     * @return
     */
    @GetMapping(value = "/list/{sellerId}/{pconfigId}")
    public Object listProductNumberApi(@PathVariable("sellerId") Long sellerId,@PathVariable("pconfigId") Long pconfigId){
        if(sellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号不能为空!","");
        }
        if(pconfigId == null){
            return JsonReturn.SetMsg(10010,"分类编号不能为空!","");
        }
        List<ProductNumber> productNumbers = productNumberService.findProductNumberList(sellerId,pconfigId);
        if(productNumbers != null && productNumbers.size() > 0){
            return JsonReturn.SetMsg(0,"获取产品配置的数量列表成功!",productNumbers);
        }
        return JsonReturn.SetMsg(10011,"暂无产品配置的数量数据!","");
    }

    /**
     * 删除产品配置的数量API接口
     * @param id
     * @return
     */
    @GetMapping(value = "/del/{id}")
    public Object deleteProductNumberApi(@PathVariable("id") Long id){
        if(id == null){
            return JsonReturn.SetMsg(10010,"产品配置数量的编号不能为空!","");
        }
        if(productNumberService.deleteProductNumber(id) > 0){
            return JsonReturn.SetMsg(0,"删除产品配置数量成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除产品配置数量失败!","");
    }

    /**
     * 获取单条产品配置的数量数据的API接口
     * @param id
     * @return
     */
    @GetMapping(value = "/find/{id}")
    public Object findOneProductNumberApi(@PathVariable("id") Long id){
        if(id == null){
            return JsonReturn.SetMsg(10010,"数量主键编号不能为空!","");
        }
        ProductNumber productNumber = productNumberService.findProductNumberOne(id);
        if(productNumber != null){
            return  JsonReturn.SetMsg(0,"获取单条产品配置的数量成功!",productNumber);
        }
        return JsonReturn.SetMsg(10011,"获取单条产品配置的数量失败!","");
    }

    /**
     * 设置数量默认值
     * @param sellerId
     * @param pconfigId
     * @param nums
     * @return
     */
    @GetMapping(value = "/set/default/{sellerId}/{pconfigId}/{nums}")
    public Object setDefaultApi(@PathVariable("sellerId") Long sellerId,@PathVariable("pconfigId") Long pconfigId,@PathVariable("nums") Integer nums){
        if(sellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号不能为空!","");
        }
        if(pconfigId == null){
            return JsonReturn.SetMsg(10010,"产品分类编号不能为空!","");
        }
        if(nums == null){
            return JsonReturn.SetMsg(10010,"数量值不能为空!","");
        }
        if(productNumberService.setIsDefaultNumber(sellerId,pconfigId,nums) > 0){
            return JsonReturn.SetMsg(0,"设置数量默认值成功!","");
        }
        return JsonReturn.SetMsg(10011,"设置数量默认值失败!","");
    }
}
