package com.bzdepot.offer.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.offer.model.Classfiy;
import com.bzdepot.offer.service.ClassfiyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/class/list")
public class ClassfiyController {
    private final static Logger loger = LoggerFactory.getLogger(ClassfiyController.class);
    @Autowired
    private ClassfiyService classfiyService;

    /*
    *  添加分类接口
    * */
    @PostMapping(value = "/add")
    public Object addClassfiy(@Valid @ModelAttribute Classfiy classfiy, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                loger.error(result.getFieldError().getField()+"参数传递的类型错误!");
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        Classfiy classfiyData = classfiyService.findClassfiyByTitle(classfiy.getSellerId(),classfiy.getTitle());
        if(classfiyData != null){
            return JsonReturn.SetMsg(10010,"分类名称已经存在，不能重复添加!","");
        }
        Long ClassId = classfiyService.addClassfiy(classfiy);
        if(ClassId == null){
            return JsonReturn.SetMsg(10011,"分类数据添加失败!","");
        }
        return JsonReturn.SetMsg(0,"分类添加成功!",ClassId);
    }

    /*
    *  修改分类接口
    * */
    @PostMapping(value = "/edit")
    public Object editClassfiy(@Valid @ModelAttribute Classfiy classfiy, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                loger.error(result.getFieldError().getField()+"参数传递的类型错误!");
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(classfiy.getId() == null){
            return JsonReturn.SetMsg(10010,"数据编号不能为空!","");
        }
        Classfiy classfiyData = classfiyService.findClassfiyByTitle(classfiy.getSellerId(),classfiy.getTitle());
        if(classfiyData != null && classfiyData.getId() != classfiy.getId()){
            return JsonReturn.SetMsg(10010,"分类名称已经存在，不能重复添加!","");
        }
        int Status = classfiyService.updateClassfiy(classfiy);
        if(Status == 1){
            return  JsonReturn.SetMsg(0,"分类数据修改成功","");
        }
        return JsonReturn.SetMsg(10011,"分类数据修改失败!","");
    }

    /*
    *  删除分类接口
    * */
    @GetMapping(value = "/del/{userid}/{id}")
    public Object delClassfiy(@PathVariable("userid") Long userid,@PathVariable("id") Long id){
        int Ok = classfiyService.delClassfiy(id,userid);
        if(Ok == 1){
            return JsonReturn.SetMsg(0,"分类数据删除成功!","");
        }
        if(Ok == -1){
            return JsonReturn.SetMsg(10011,"此分类下包含材质分组或材质数据不能直接删除!","");
        }
        return JsonReturn.SetMsg(10011,"删除分类数据失败!","");
    }
}
