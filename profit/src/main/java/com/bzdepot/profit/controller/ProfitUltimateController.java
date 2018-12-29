package com.bzdepot.profit.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.profit.bo.ProfitUltimateBo;
import com.bzdepot.profit.bo.ProfitUltimateListPostBo;
import com.bzdepot.profit.model.ProfitUltimate;
import com.bzdepot.profit.service.ProfitUltimateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/profit/ultimate")
public class ProfitUltimateController {

    private final static Logger loger = LoggerFactory.getLogger(ProfitUltimateController.class);

    @Autowired
    private ProfitUltimateService profitUltimateService;

    /**
     * 利润更新API接口
     * @param profitUltimateBo
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateProfitUltimate (@Valid @ModelAttribute ProfitUltimateBo profitUltimateBo, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }        System.out.println(profitUltimateBo.toString());

        if(profitUltimateService.updateProfitUltimate(profitUltimateBo) > 0){
            return JsonReturn.SetMsg(0,"更新利润配置成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新利润配置失败!","");
    }

    /**
     * 删除利润API接口
     * @param id
     * @return
     */
    @GetMapping(value = "/del/{id}")
    public Object deleteProfitUltimate(@PathVariable("id") Long id){
        if(id == null){
            return JsonReturn.SetMsg(10010,"利润编号不能为空!","");
        }
        if(profitUltimateService.deleteProfitUltimate(id) > 0){
            return JsonReturn.SetMsg(0,"删除利润成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除利润失败!","");
    }

    /**
     * 获取利润列表API接口
     * @param profitUltimateListPostBo
     * @param result
     * @return
     */
    @PostMapping(value = "/list")
    public Object listProfitUltimate(@Valid @ModelAttribute ProfitUltimateListPostBo profitUltimateListPostBo,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        List<ProfitUltimate> profitUltimates = profitUltimateService.selectListProfitUltimate(profitUltimateListPostBo.getSellerId(),profitUltimateListPostBo.getClassId(),profitUltimateListPostBo.getTextureId(),profitUltimateListPostBo.getGramId());
        if(profitUltimates != null && profitUltimates.size() > 0){
            return JsonReturn.SetMsg(0,"获取利润列表成功!",profitUltimates);
        }
        return JsonReturn.SetMsg(10011,"获取利润列表失败!","");
    }

}
