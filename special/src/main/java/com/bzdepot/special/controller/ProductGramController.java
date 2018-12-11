package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.bo.ProductGramBo;
import com.bzdepot.special.model.ProductGram;
import com.bzdepot.special.service.ProductGramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/product/gram")
public class ProductGramController {

    private final static Logger loger = LoggerFactory.getLogger(ProductGramController.class);

    @Autowired
    private ProductGramService productGramService;

    /**
     * 更新厚度数据API接口
     * @param productGramBo
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateProductGramApi(@Valid @ModelAttribute ProductGramBo productGramBo, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(productGramService.updateProductGram(productGramBo) > 0){
            return JsonReturn.SetMsg(0,"更新厚度数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新厚度数据失败!","");
    }

    /**
     * 获取厚度数据列表API接口
     * @param sellerId
     * @param textureId
     * @return
     */
    @GetMapping(value = "/list/{sellerId}/{textureId}")
    public Object listProductGramApi(@PathVariable("sellerId") Long sellerId,@PathVariable("textureId") Long textureId){
        if(sellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号不能为空!","");
        }
        if(textureId == null){
            return JsonReturn.SetMsg(10010,"材质编号不能为空!","");
        }
        List<ProductGram> productGrams = productGramService.findListByMore(sellerId,textureId);
        if(productGrams != null && productGrams.size() > 0){
            return JsonReturn.SetMsg(0,"获取厚度数据成功!",productGrams);
        }
        return JsonReturn.SetMsg(10011,"此材质下暂无厚度数据!","");
    }

    /**
     * 移除厚度API接口
     * @param sellerId
     * @param textureId
     * @param gramId
     * @return
     */
    @GetMapping(value = "/del/{sellerId}/{textureId}/{gramId}")
    public Object deleteProductGramApi(@PathVariable("sellerId") Long sellerId,@PathVariable("textureId") Long textureId,@PathVariable("gramId") Long gramId){
        if(sellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号不能为空!","");
        }
        if(textureId == null){
            return JsonReturn.SetMsg(10010,"材质编号不能为空!","");
        }
        if(gramId == null){
            return JsonReturn.SetMsg(10010,"厚度编号不能为空!","");
        }
        if(productGramService.deleteProductGram(sellerId,textureId,gramId) > 0){
            return JsonReturn.SetMsg(0,"移除厚度数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"移除厚度数据失败!","");
    }

    /**
     * 设置默认API接口
     * @param sellerId
     * @param textureId
     * @param gramId
     * @return
     */
    @GetMapping(value = "/set/default/{sellerId}/{textureId}/{gramId}")
    public Object setDefaultProductGramApi(@PathVariable("sellerId") Long sellerId,@PathVariable("textureId") Long textureId,@PathVariable("gramId") Long gramId){
        if(sellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号不能为空!","");
        }
        if(textureId == null){
            return JsonReturn.SetMsg(10010,"材质编号不能为空!","");
        }
        if(gramId == null){
            return JsonReturn.SetMsg(10010,"厚度编号不能为空!","");
        }
        if(productGramService.setDefaultProductGram(sellerId,textureId,gramId) > 0){
            return JsonReturn.SetMsg(0,"设置默认成功!","");
        }
        return JsonReturn.SetMsg(10011,"设置默认失败!","");
    }
}
