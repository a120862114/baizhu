package com.bzdepot.offer.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.common.util.UserUtil;
import com.bzdepot.offer.model.TextureGroup;
import com.bzdepot.offer.service.TextureGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/texture/group")
public class TextureGroupController {

    @Autowired
    private TextureGroupService textureGroupService;

    @PostMapping(value = "/add")
    public Object addTextureGroup(@Valid @ModelAttribute TextureGroup textureGroup, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        TextureGroup textureGroupData = textureGroupService.findTextureGroupByGroupName(textureGroup.getSellerId(),textureGroup.getGroupName());
        if(textureGroupData != null){
            return JsonReturn.SetMsg(10010,"材质分组名称已经存在!","");
        }
        Long Tid = textureGroupService.addTextureGroup(textureGroup);
        if(Tid != null){
            return JsonReturn.SetMsg(0,"添加材质分组成功!",Tid);
        }
        return JsonReturn.SetMsg(10011,"添加材质分组失败!","");
    }

    @PostMapping(value = "edit")
    public Object editTextureGroup(@Valid @ModelAttribute TextureGroup textureGroup,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(textureGroup.getId() == null){
            return JsonReturn.SetMsg(10010,"材质分组编号不能为空!","");
        }
        TextureGroup textureGroupData = textureGroupService.findTextureGroupByGroupName(textureGroup.getSellerId(),textureGroup.getGroupName());
        if(textureGroupData != null && textureGroupData.getId() != textureGroup.getId()){
            return JsonReturn.SetMsg(10010,"材质分组名称已经存在!","");
        }
        int Ok = textureGroupService.editTextureGroup(textureGroup);
        if(Ok == 1){
            return JsonReturn.SetMsg(0,"材质分组数据修改成功!","");
        }
        return JsonReturn.SetMsg(10011,"材质分组数据添加失败!","");
    }

    /*
     *   查询分类分组与分类的相关接口
     * */
    @PostMapping(value = "list")
    public Object classGroupList(HttpServletRequest request){
        String SellerIdStr = request.getParameter("seller_id");
        String ClassIdStr = request.getParameter("class_id");
        Long SellerId = null;
        try {
            SellerId = Long.parseLong(SellerIdStr);
        }catch (NumberFormatException e){
            return  JsonReturn.SetMsg(10010,"商家编号转码失败!",null);
        }
        if(SellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号转码后为空!","");
        }
        Long ClassId = null;
        try {
            ClassId = Long.parseLong(ClassIdStr);
        }catch (NumberFormatException e){
            return  JsonReturn.SetMsg(10010,"分类编号转码失败!",null);
        }
        if(ClassId == null){
            return JsonReturn.SetMsg(10010,"分类编号转码后为空!","");
        }

        List<TextureGroup> ResultData = textureGroupService.findAllClassGroupAndJoin(SellerId,ClassId);
        if(ResultData.size() > 0){
            return JsonReturn.SetMsg(0,"获取材质数据成功!",ResultData);
        }
        return JsonReturn.SetMsg(10011,"获取材质数据失败!","");
    }

    /*
    *  删除材质分组接口
    */
    @GetMapping(value = "/del/{sellerid}/{classid}/{id}")
    public Object delTextureGroup(@PathVariable("sellerid") Long sellerid,@PathVariable("classid") Long classid,@PathVariable("id") Long id){
        System.out.println(UserUtil.getUsername());
       int Ok = textureGroupService.delTextureGroup(classid,sellerid,id);
       switch (Ok){
           case -1:
               return JsonReturn.SetMsg(10011,"不能删除包含材质数据的材质分组信息!","");
           case 1:
               return JsonReturn.SetMsg(0,"材质分组数据删除成功!","");
       }

       return JsonReturn.SetMsg(10011,"材质分组数据删除失败!","");
    }
}