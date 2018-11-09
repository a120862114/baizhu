package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.bo.PrintingCostBo;
import com.bzdepot.special.service.PrintingCostService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/printing/config")
public class PrintingCostController {

    private final static Logger loger = LoggerFactory.getLogger(PrintingCostController.class);

    @Autowired
    private PrintingCostService printingCostService;

    /**
     * 印刷费配置更新API接口
     * @param printingCostBo
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updatePrintingCost(@Valid @ModelAttribute PrintingCostBo printingCostBo, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(printingCostService.updatePrintingCost(printingCostBo) > 0){
            return JsonReturn.SetMsg(0,"更新纸张印刷费配置成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新纸张印刷费配置失败!","");
    }

    /**
     * 获取印刷费列表API接口
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/list/{pageNum}/{pageSize}")
    public Object listPrintingCost(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize){
        if (pageSize == 0){
            pageSize = 0;
        }
        if(pageNum == 0){
            pageNum = 1;
        }
        PageInfo printingCosts = printingCostService.listPrintingCost(pageNum,pageSize);
        if(printingCosts != null && printingCosts.getTotal() > 0L){
            return JsonReturn.SetMsg(0,"获取印刷费配置列表成功!",printingCosts);
        }
        return JsonReturn.SetMsg(10011,"获取印刷费配置列表失败!","");
    }

    /**
     * 删除印刷费配置API接口
     * @param costId
     * @return
     */
    @GetMapping(value = "/del/{costId}")
    public Object deletePrintingCost(@PathVariable("costId") Long costId){
        if(costId == null){
            return JsonReturn.SetMsg(10010,"印刷配置编号不能为空!","");
        }
        if(printingCostService.deletePrintingCost(costId) > 0){
            return JsonReturn.SetMsg(0,"删除印刷费配置成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除印刷费配置失败!","");
    }
}
