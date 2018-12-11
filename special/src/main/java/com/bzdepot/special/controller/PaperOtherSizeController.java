package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.model.PaperCost;
import com.bzdepot.special.model.PaperOtherSize;
import com.bzdepot.special.service.PaperCostService;
import com.bzdepot.special.service.PaperOtherSizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/paper/other")
public class PaperOtherSizeController {

    private final static Logger loger = LoggerFactory.getLogger(PaperOtherSizeController.class);

    @Autowired
    private PaperOtherSizeService paperOtherSizeService;

    @Autowired
    private PaperCostService paperCostService;

    /**
     * 更新纸张其他尺寸API接口
     * @param paperOtherSize
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateOther(@Valid @ModelAttribute PaperOtherSize paperOtherSize, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(paperOtherSizeService.updatePaperOtherSize(paperOtherSize) > 0){
            return JsonReturn.SetMsg(0,"更新纸张其他尺寸数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新纸张其他尺寸数据失败!","");
    }

    /**
     * 获取纸张其他尺寸列表API接口
     * @return
     */
    @GetMapping(value = "/list")
    public Object listOther(){
        List<PaperOtherSize> paperOtherSizes = paperOtherSizeService.listPaperOtherSize();
        if(paperOtherSizes != null && paperOtherSizes.size() > 0){
            return JsonReturn.SetMsg(0,"获取纸张其他尺寸列表数据!",paperOtherSizes);
        }
        return JsonReturn.SetMsg(10011,"获取纸张其他尺寸列表失败!","");
    }

    /**
     * 删除纸张其他尺寸API接口
     * @param otherId
     * @return
     */
    @GetMapping(value = "/del/{otherId}")
    public Object deleteOther(@PathVariable("otherId") Long otherId){
        if(otherId == null){
            return JsonReturn.SetMsg(10010,"纸张其他尺寸编号不能为空!","");
        }
        List<PaperCost> paperCosts = paperCostService.findInSetOtherSizeIdsInPaperCost(otherId);
        if(paperCosts != null && paperCosts.size() > 0){
            return JsonReturn.SetMsg(10010,"此尺寸规格存在于其他纸张费配置中，暂不能删除!","");
        }
        if(paperOtherSizeService.deletePaperOtherSize(otherId) > 0){
            return JsonReturn.SetMsg(0,"删除纸张其他尺寸数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除纸张其他尺寸数据失败!","");
    }
}
