package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.model.PaperGram;
import com.bzdepot.special.service.PaperGramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/paper/gram")
public class PaperGramController {

    private final static Logger loger = LoggerFactory.getLogger(PaperGramController.class);

    @Autowired
    private PaperGramService paperGramService;

    /**
     * 更新纸张克重API接口
     * @param paperGram
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateGram(@Valid @ModelAttribute PaperGram paperGram, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(paperGramService.updatePaperGram(paperGram) > 0){
            return JsonReturn.SetMsg(0,"更新纸张克重成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新纸张克重失败!","");
    }

    /**
     * 获取纸张克重列表API接口
     * @return
     */
    @GetMapping(value = "/list")
    public Object listGram(){
        List<PaperGram> paperGrams = paperGramService.listPaperGram();
        if(paperGrams != null && paperGrams.size() > 0){
            return JsonReturn.SetMsg(0,"获取纸张克重列表成功!",paperGrams);
        }
        return JsonReturn.SetMsg(10011,"获取纸张克重列表失败!","");
    }

    /**
     * 删除纸张克重API接口
     * @param gramId
     * @return
     */
    @GetMapping(value = "/del/{gramId}")
    public Object deleteGram(@PathVariable("gramId") Long gramId){
        if(gramId == null){
            return JsonReturn.SetMsg(10010,"纸张克重编号不能为空!","");
        }
        if(paperGramService.deletePaperGram(gramId) > 0){
            return JsonReturn.SetMsg(0,"删除纸张克重成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除纸张克重失败!","");
    }
}
