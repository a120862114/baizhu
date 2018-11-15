package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.common.util.UserUtil;
import com.bzdepot.special.model.PaperTexture;
import com.bzdepot.special.service.PaperTextureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/paper/texture")
public class PaperTextureController {

    private final static Logger loger = LoggerFactory.getLogger(PaperSupplierController.class);

    @Autowired
    private PaperTextureService paperTextureService;

    /**
     * 更新纸张材质API接口
     * @param paperTexture
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateTexture(@Valid @ModelAttribute PaperTexture paperTexture, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(paperTextureService.updatePaperTexture(paperTexture) > 0){
            return JsonReturn.SetMsg(0,"更新纸张材质成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新纸张材质失败!","");
    }

    /**
     * 纸张材质列表API接口
     * @return
     */
    @GetMapping(value = "/list")
    public Object listTexture(){
        List<PaperTexture> paperTextures = paperTextureService.listPaperTexture(UserUtil.getId());
        if(paperTextures != null && paperTextures.size() > 0){
            return JsonReturn.SetMsg(0,"获取纸张材质列表成功!",paperTextures);
        }
        return JsonReturn.SetMsg(10011,"获取纸张材质列表失败!","");
    }

    /**
     * 删除纸张材质API接口
     * @param textureId
     * @return
     */
    @GetMapping(value = "/del/{textureId}")
    public Object deleteTexture(@PathVariable("textureId") Long textureId){
        if(textureId == null){
            return JsonReturn.SetMsg(10010,"纸张材质编号不能为空!","");
        }
        if(paperTextureService.deletePaperTexture(textureId) > 0){
            return JsonReturn.SetMsg(0,"删除纸张材质成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除纸张材质失败!","");
    }
}
