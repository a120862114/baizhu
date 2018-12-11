package com.bzdepot.offer.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.offer.model.Texture;
import com.bzdepot.offer.service.TextureService;
import com.bzdepot.offer.vo.SidCidTidBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        Texture textureData = textureService.findTextureByClassIdAndSellerIdAndTitleForOne(texture.getSellerId(),texture.getClassId(),texture.getTitle());
        if(textureData != null){
            return JsonReturn.SetMsg(10010,"材质名称已经存在，请更换名称后在试!","");
        }
        Long tid = textureService.addTexture(texture);
        if(tid != null){
            return JsonReturn.SetMsg(0,"添加材质数据成功",tid);
        }
        return JsonReturn.SetMsg(10011,"添加材质数据失败 !","");
    }

    @PostMapping(value = "/edit")
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
        Texture textureData = textureService.findTextureByClassIdAndSellerIdAndTitleForOne(texture.getSellerId(),texture.getClassId(),texture.getTitle());
        if(textureData != null && textureData.getId() != texture.getId()){
            return JsonReturn.SetMsg(10010,"材质名称已经存在，请更换名称后在试!","");
        }
        int Ok = textureService.editTexture(texture);
        if(Ok == 1){
            return JsonReturn.SetMsg(0,"修改材质数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"添加材质数据失败!","");
    }

    /**
     * 删除材质分类数据,同时mysql触发器删除产品图片数据product_img
     * @param sidCidTidBo
     * @param result
     * @return
     */
    @PostMapping(value = "/del")
    public Object delTextureData(@ModelAttribute SidCidTidBo sidCidTidBo,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        int Code = textureService.deleteTexture(sidCidTidBo.getSellerId(),sidCidTidBo.getClassId(),sidCidTidBo.getTextureId());
        if(Code == -1){
            return JsonReturn.SetMsg(10011,"材质分类下包含报价配置数据，暂不能删除！","");
        }
        if(Code == 1){
            return JsonReturn.SetMsg(0,"删除材质分类数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除材质分类失败!","");
    }

    /**
     * 获取材质的列表API接口
     * @param sellerId
     * @return
     */
    @GetMapping(value = "/list/{sellerId}")
    public Object listTextureApi(@PathVariable("sellerId") Long sellerId){
        if(sellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号不能为空!","");
        }
        List<Texture> textures = textureService.findTextureList(sellerId);
        if(textures != null && textures.size() > 0){
            return JsonReturn.SetMsg(0,"获取材质列表成功!",textures);
        }
        return JsonReturn.SetMsg(10011,"此商家暂无材质数据信息!","");
    }

}
