package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.bo.TechnologyPost;
import com.bzdepot.special.model.TechnologyClass;
import com.bzdepot.special.service.TechnologyService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/technology")
public class TechnologyController {

    private final static Logger loger = LoggerFactory.getLogger(TechnologyController.class);

    @Autowired
    private TechnologyService technologyService;

    /**
     * 更新工艺API接口
     * @param technologyPost
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateTechnology(@Valid @ModelAttribute TechnologyPost technologyPost, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(technologyService.updateTechnology(technologyPost) > 0){
            return JsonReturn.SetMsg(0,"更新工艺成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新工艺失败!","");
    }

    /**
     * 删除材质属性API接口
     * @param attrId
     * @return
     */
    @GetMapping(value = "/del/{attrId}/{tId}")
    public Object delAttr(@PathVariable("attrId") Long attrId,@PathVariable("tId") Long tId){
        if(attrId == null){
            return JsonReturn.SetMsg(10011,"材质属性编号不能为空!","");
        }
        if(tId == null){
            return JsonReturn.SetMsg(10010,"材质分类编号不能为空!","");
        }
        if(technologyService.deleteAttr(attrId,tId) > 0){
            return JsonReturn.SetMsg(0,"删除材质属性成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除材质属性失败!","");
    }

    /**
     * 获取材质分页API接口
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/list/{pageNum}/{pageSize}")
    public Object listDataApi(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize){
        if (pageSize == 0){
            pageSize = 0;
        }
        if(pageNum == 0){
            pageNum = 1;
        }
        PageInfo<List<TechnologyClass>> technologyClasses = technologyService.pageListData(pageNum,pageSize);
        if(technologyClasses != null && technologyClasses.getTotal() > 0L){
            return JsonReturn.SetMsg(0,"获取材质数据列表成功!",technologyClasses);
        }
        return JsonReturn.SetMsg(10011,"暂时还没有材质数据!","");
    }

}
