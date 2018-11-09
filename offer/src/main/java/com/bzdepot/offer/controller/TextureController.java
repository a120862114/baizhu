package com.bzdepot.offer.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.offer.model.Texture;
import com.bzdepot.offer.service.TextureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/texture/list")
public class TextureController {

    @Autowired
    private TextureService textureService;

    @PostMapping(value = "/add")
    public Object addTexture(@Valid @ModelAttribute Texture texture, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        Texture textureData = textureService.findTextureByTitle(texture.getSellerId(),texture.getTitle());
        if(textureData != null){
            return JsonReturn.SetMsg(10010,"材质名称已经存在，请更换名称后在试!","");
        }
        Long tid = textureService.addTexture(texture);
        if(tid != null){
            return JsonReturn.SetMsg(0,"添加材质数据成功",tid);
        }
        return JsonReturn.SetMsg(10011,"添加材质数据失败 !","");
    }

    @PostMapping(value = "edit")
    public Object editTexture(@Valid @ModelAttribute Texture texture, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(texture.getId() == null){
            return JsonReturn.SetMsg(10010,"材质数据编号不能为空!","");
        }
        Texture textureData = textureService.findTextureByTitle(texture.getSellerId(),texture.getTitle());
        if(textureData != null && textureData.getId() != texture.getId()){
            return JsonReturn.SetMsg(10010,"材质名称已经存在，请更换名称后在试!","");
        }
        int Ok = textureService.editTexture(texture);
        if(Ok == 1){
            return JsonReturn.SetMsg(0,"修改材质数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"添加材质数据失败!","");
    }
}
