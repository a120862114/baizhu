package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.model.PaperCost;
import com.bzdepot.special.model.PaperDrum;
import com.bzdepot.special.service.PaperCostService;
import com.bzdepot.special.service.PaperDrumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/paper/drum")
public class PaperDrumController {

    private final static Logger loger = LoggerFactory.getLogger(PaperDrumController.class);

    @Autowired
    private PaperDrumService paperDrumService;

    @Autowired
    private PaperCostService paperCostService;

    /**
     * 更新纸张卷筒数据API接口
     * @param paperDrum
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateDrum(@Valid @ModelAttribute PaperDrum paperDrum, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(paperDrumService.updatePaperDrum(paperDrum) > 0){
            return JsonReturn.SetMsg(0,"更新纸张卷筒数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新纸张卷筒数据失败!","");
    }

    /**
     * 获取纸张卷筒数据API接口
     * @return
     */
    @GetMapping(value = "/list")
    public Object listPaperDrum(){
        List<PaperDrum> paperDrums = paperDrumService.listPaperDrum();
        if(paperDrums != null && paperDrums.size() > 0){
            return JsonReturn.SetMsg(0,"获取纸张卷筒数据成功!",paperDrums);
        }
        return JsonReturn.SetMsg(10011,"获取纸张卷筒数据失败!","");
    }

    /**
     * 删除纸张卷筒数据API接口
     * @param drumId
     * @return
     */
    @GetMapping(value = "/del/{drumId}")
    public Object deleteDrum(@PathVariable("drumId") Long drumId){
        if(drumId == null){
            return JsonReturn.SetMsg(10010,"纸张卷筒编号不能为空!","");
        }
        List<PaperCost> paperCosts = paperCostService.findInSetDrumIdsInPaperCost(drumId);
        System.out.println(paperCosts.toString());
        if(paperCosts != null && paperCosts.size() > 0){
            return JsonReturn.SetMsg(10010,"此卷筒纸尺寸存在于其他的纸张费配置中，暂不能删除!","");
        }
        if(paperDrumService.deletePaperDrum(drumId) > 0){
            return JsonReturn.SetMsg(0,"删除纸张卷筒数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除纸张卷筒数据失败!","");
    }
}
