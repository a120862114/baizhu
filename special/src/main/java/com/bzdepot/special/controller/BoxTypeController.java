package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.model.BoxType;
import com.bzdepot.special.service.BoxTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/box/type")
public class BoxTypeController {

    @Autowired
    private BoxTypeService boxTypeService;

    /**
     *  更新盒型APi接口
     * @param boxType
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object update(@Valid @ModelAttribute BoxType boxType, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(boxTypeService.updateBoxType(boxType) > 0){
            return JsonReturn.SetMsg(0,"更新盒型成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新盒型失败!","");
    }

    /**
     * 获取盒型列表API接口
     * @param sellerId
     * @return
     */
    @GetMapping(value = "/list/{sellerId}")
    public Object getListApi(@PathVariable("sellerId") Long sellerId){
        if(sellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号不能为空!","");
        }
        List<BoxType> boxTypes = boxTypeService.findBoxTypeBySellerId(sellerId);
        if(boxTypes != null && boxTypes.size() > 0){
            return JsonReturn.SetMsg(0,"获取盒型列表成功!",boxTypes);
        }
        return JsonReturn.SetMsg(10011,"暂无盒型数据!","");
    }

    /**
     * 删除盒型数据API接口
     * @param id
     * @return
     */
    @GetMapping(value = "/del/{id}")
    public Object deleteBoxType(@PathVariable("id") Long id){
        if(id == null){
            return JsonReturn.SetMsg(10010,"盒型编号不能为空!","");
        }
        if(boxTypeService.deleteById(id) > 0){
            return JsonReturn.SetMsg(0,"删除盒型数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除盒型数据失败!","");
    }

    /**
     * 获取单条盒型数据API
     * @param id
     * @return
     */
    @GetMapping(value = "/find/{id}")
    public Object findBoxType(@PathVariable("id") Long id){
        if(id == null){
            return JsonReturn.SetMsg(10010,"盒型编号不能为空!","");
        }
        BoxType boxType = boxTypeService.findBoxTypeData(id);
        if(boxType != null){
            return JsonReturn.SetMsg(0,"获取盒型数据成功!",boxType);
        }
        return JsonReturn.SetMsg(10011,"获取盒型数据失败!","");
    }



}
