package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.model.ProductSize;
import com.bzdepot.special.service.ProductSizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/product/size")
public class ProductSizeController {

    private final static Logger loger = LoggerFactory.getLogger(ProductSizeController.class);

    @Autowired
    private ProductSizeService productSizeService;

    /**
     * 更新产品配置的尺寸数据API接口
     * @param productSize
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateProductSize(@Valid @ModelAttribute ProductSize productSize, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        ProductSize Condition = new ProductSize();
        Condition.setSellerId(productSize.getSellerId());
        Condition.setPconfigId(productSize.getPconfigId());
        Condition.setLongs(productSize.getLongs());
        Condition.setWidth(productSize.getWidth());
        Condition.setHeight(productSize.getHeight());
        ProductSize Result = productSizeService.moreConditionFindSize(Condition);
        if(productSize.getId() == null && Result != null){
            return JsonReturn.SetMsg(10010,"当前添加的尺寸数据已存在，不能重复添加!","");
        }
        if(productSize.getId() != null && Result != null && Result.getId() != productSize.getId()){
            return JsonReturn.SetMsg(10010,"您要修改的尺寸数据已经存在!","");
        }
        if(productSizeService.updateroductSize(productSize) > 0){
            return JsonReturn.SetMsg(0,"更新产品配置的尺寸数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新产品配置的尺寸数据失败!","");
    }

    /**
     * 获取尺寸列表API接口
     * @param sellerId
     * @return
     */
    @GetMapping(value = "/list/{sellerId}/{pconfigId}")
    public Object listProductSize(@PathVariable("sellerId") Long sellerId,@PathVariable("pconfigId") Long pconfigId){
        if(sellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号不能为空!","");
        }
        if(pconfigId == null){
            return JsonReturn.SetMsg(10010,"分类编号不能为空!","");
        }
        List<ProductSize> productSizes = productSizeService.findProductSizeList(sellerId,pconfigId);
        if(productSizes != null && productSizes.size() > 0){
            return JsonReturn.SetMsg(0,"获取尺寸列表数据成功!",productSizes);
        }
        return JsonReturn.SetMsg(10011,"暂无尺寸数据列表!","");
    }

    /**
     * 移除单条的尺寸数据API接口
     * @param id
     * @return
     */
    @GetMapping(value = "/del/{id}")
    public Object deleteProductSize(@PathVariable("id") Long id){
        if(id == null){
            return JsonReturn.SetMsg(10010,"尺寸编号不能为空!","");
        }
        if(productSizeService.deleteProductSize(id) > 0){
            return JsonReturn.SetMsg(0,"移除尺寸数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"移除尺寸数据失败!","");
    }

    /**
     * 获取单条的尺寸数据API接口
     * @param id
     * @return
     */
    @GetMapping(value = "/find/{id}")
    public Object findProductSize(@PathVariable("id") Long id){
        if(id == null){
            return JsonReturn.SetMsg(10010,"尺寸编号不能为空!","");
        }
        ProductSize productSize = productSizeService.findProductSizeOne(id);
        if(productSize != null){
            return JsonReturn.SetMsg(0,"获取尺寸数据成功!",productSize);
        }
        return JsonReturn.SetMsg(10011,"获取尺寸数据失败!","");
    }

    /**
     * 设置尺寸默认数据
     * @param productSize
     * @param result
     * @return
     */
    @PostMapping(value = "/set/default")
    public Object setDefaultApi(@Valid @ModelAttribute ProductSize productSize,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        System.out.println("尺寸默认参数:"+productSize.toString());
        if(productSizeService.setDefaultSize(productSize) > 0){
            return JsonReturn.SetMsg(0,"设置尺寸默认成功!","");
        }
        return JsonReturn.SetMsg(10011,"设置尺寸默认失败!","");
    }
}
