package com.bzdepot.offer.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.offer.model.ClassGroup;
import com.bzdepot.offer.service.ClassGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/class/group")
public class ClassGroupController {

    @Autowired
    private ClassGroupService classGroupService;

    @PostMapping(value = "/add")
    public Object addGroup(@Valid @ModelAttribute ClassGroup classGroup, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        ClassGroup classGroupData = classGroupService.findClassGroupByGroupName(classGroup.getSellerId(),classGroup.getGroupName());
        if(classGroupData != null){
            return JsonReturn.SetMsg(10010,"分类分组名称已经存在!","");
        }
        Long GroupId = classGroupService.addClassGroup(classGroup);
        if(GroupId != null){
            return JsonReturn.SetMsg(0,"添加分组成功！",GroupId);
        }
        return JsonReturn.SetMsg(10011,"添加分组失败!","");
    }

    @PostMapping(value = "/edit")
    public Object editClassGroup(@Valid @ModelAttribute ClassGroup classGroup,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }

        if(classGroup.getId() == null){
            return JsonReturn.SetMsg(10010,"数据编号不能为空！","");
        }
        ClassGroup classGroupData = classGroupService.findClassGroupByGroupName(classGroup.getSellerId(),classGroup.getGroupName());
        if(classGroupData != null && classGroupData.getId() != classGroup.getId()){
            return JsonReturn.SetMsg(10010,"分类分组名称已经存在!","");
        }
        int Status = classGroupService.updateClassGroup(classGroup);
        if(Status == 1){
            return  JsonReturn.SetMsg(0,"数据修改成功！","");
        }
        return  JsonReturn.SetMsg(10011,"数据修改失败！","");
    }

    /*
    *   查询分类分组与分类的相关接口
    * */
    @PostMapping(value = "list")
    public Object classGroupList(HttpServletRequest request){
        String SellerIdStr = request.getParameter("seller_id");
        Long SellerId = null;
        try {
            SellerId = Long.parseLong(SellerIdStr);
        }catch (NumberFormatException e){
            return  JsonReturn.SetMsg(10010,"商家编号转码失败!",null);
        }

        if(SellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号转码后为空!","");
        }

        List<ClassGroup> ResultData = classGroupService.findAllClassGroupAndJoin(SellerId);
        if(ResultData.size() > 0){
            return JsonReturn.SetMsg(0,"获取分组数据成功!",ResultData);
        }
        return JsonReturn.SetMsg(10011,"获取分组数据失败!","");
    }

    /*
    *   删除分组接口
    * */
    @GetMapping(value = "/del/{id}")
    public Object delGroupData(@PathVariable("id") Long id){
        int Ok = classGroupService.delEmptyGroupById(id);
        System.out.println(Ok);
        switch (Ok){
            case -1:
                return JsonReturn.SetMsg(10011,"此分组下包含分类数据不能删除！","");
            case 1:
                return JsonReturn.SetMsg(0,"分组数据删除成功!","");
        }
        return JsonReturn.SetMsg(10011,"分组删除失败!","");
    }
}
