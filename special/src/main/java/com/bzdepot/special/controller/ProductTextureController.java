package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.bo.ProductTextureBo;
import com.bzdepot.special.model.ProductTexture;
import com.bzdepot.special.service.ProductTextureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/product/texture")
public class ProductTextureController {

    private final static Logger loger = LoggerFactory.getLogger(ProductTextureController.class);

    @Autowired
    private ProductTextureService productTextureService;

    /**
     * 更新材质映射数据API接口
     * @param productTextureBo
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateProductTextureApi(@Valid @ModelAttribute ProductTextureBo productTextureBo, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(productTextureService.updateProductTexture(productTextureBo) > 0){
            return JsonReturn.SetMsg(0,"更新产品配置的材质映射数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新数据失败!","");
    }

    /**
     * 移除材质数据API接口
     * @param sellerId
     * @param pconfigId
     * @param textureId
     * @return
     */
    @GetMapping(value = "/del/more/{sellerId}/{pconfigId}/{textureId}")
    public Object deleteProductTextureMoreApi(@PathVariable("sellerId") Long sellerId,@PathVariable("pconfigId") Long pconfigId,@PathVariable("textureId") Long textureId){
        if(sellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号不能为空!","");
        }
        if(pconfigId == null){
            return JsonReturn.SetMsg(10010,"产品分类编号不能为空!","");
        }
        if(textureId == null){
            return JsonReturn.SetMsg(10010,"材质编号不能为空!","");
        }
        if(productTextureService.deleteProductTextureByMore(sellerId,pconfigId,textureId) > 0){
            return JsonReturn.SetMsg(0,"移除产品配置的材质数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"移除产品配置的材质数据失败!","");
    }

    /**
     * 产品配置材质映射数据列表API接口
     * @param sellerId
     * @param pconfigId
     * @return
     */
    @GetMapping(value = "/list/{sellerId}/{pconfigId}")
    public Object listProductTextureApi(@PathVariable("sellerId") Long sellerId,@PathVariable("pconfigId") Long pconfigId){
        if(sellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号不能为空!","");
        }
        if(pconfigId == null){
            return JsonReturn.SetMsg(10010,"产品分类编号不能为空!","");
        }
        List<ProductTexture> productTextures = productTextureService.findListByMore(sellerId,pconfigId);
        if(productTextures != null && productTextures.size() > 0){
            return JsonReturn.SetMsg(0,"获取产品配置材质映射数据列表成功!",productTextures);
        }
        return JsonReturn.SetMsg(10011,"此产品分类下暂无材质映射数据!","");
    }

    /**
     * 设置默认材质API接口
     * @param productTexture
     * @param result
     * @return
     */
    @PostMapping(value = "/set/default")
    public Object setDefaultProductTextureApi(@Valid @ModelAttribute ProductTexture productTexture, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(productTextureService.setDefaultProductTexture(productTexture.getSellerId(),productTexture.getPconfigId(),productTexture.getTextureId()) > 0){
            return JsonReturn.SetMsg(0,"设置默认成功!","");
        }
        return JsonReturn.SetMsg(10011,"设置默认失败!","");
    }
}
