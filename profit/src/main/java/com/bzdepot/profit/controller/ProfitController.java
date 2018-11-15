package com.bzdepot.profit.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.profit.bo.ProfitDataBo;
import com.bzdepot.profit.model.Constitute;
import com.bzdepot.profit.model.Profit;
import com.bzdepot.profit.model.ProfitRuler;
import com.bzdepot.profit.service.ConstituteService;
import com.bzdepot.profit.service.ProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/profit")
public class ProfitController {

    @Autowired
    private ProfitService profitService;

    @Autowired
    private ConstituteService constituteService;

    @GetMapping(value = "/get/{id}")
    public Object findOneData(@PathVariable("id") Long id){
        ProfitRuler profitRuler = profitService.findOne(id);
        return JsonReturn.SetMsg(0,"HAHAHAHAH",profitRuler);
    }

    /***
     * 获取当前产品配置的利润配置（结合类型获取最适合的配置）
     * @param offerid
     * @return
     */
    @GetMapping(value = "/call/{offerid}")
    public Object callFunc(@PathVariable("offerid") Long offerid){
       List<ProfitDataBo> profitDataBos = profitService.callMysqlFunc(offerid);
       if(profitDataBos.size() > 0){
           Map<String,Object> Datas = new HashMap<String, Object>();
           Datas.put("profit",profitDataBos);
           return JsonReturn.SetMsg(0,"获取数据成功",Datas);
       }
       return JsonReturn.SetMsg(10011,"获取数据失败!","");
    }

    /**
     * 更新利润配置APi包含添加与修改
     * @param request
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateProfit(@Valid @ModelAttribute ProfitDataBo profitDataBo, BindingResult result, HttpServletRequest request){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }

        if(request.getParameter("config") == null){
            return JsonReturn.SetMsg(10010,"没有需要修改的数据!","");
        }

        List<Profit> profit =  JSON.parseObject(request.getParameter("config"),new TypeReference<List<Profit>>(){});
        //开始修改数据
        if(profit.size() == 0){
            return JsonReturn.SetMsg(10010,"请填写完整的利润配置信息!","");
        }
        //解析报价构成参数
        if(request.getParameter("constitute") == null){
            return JsonReturn.SetMsg(10010,"请选择一个报价构成类型!","");
        }
        Constitute constitute =  JSON.parseObject(request.getParameter("constitute"),new TypeReference<Constitute>(){});
        System.out.println(constitute);
        int Status = profitService.editProfit(profit,profitDataBo.getRuler_type(),profitDataBo.getDafault_type(),constitute);
        if(Status > 0){
            return JsonReturn.SetMsg(0,"利润数据修改成功!","");
        }
        return JsonReturn.SetMsg(10011,"修改利润配置数据失败!","");
    }

    /**
     * 删除利润数据
     * @param request
     * @return
     */
    @PostMapping(value = "/del/data")
    public Object delProfit(HttpServletRequest request){
        if(request.getParameter("id") == null){
            return JsonReturn.SetMsg(10010,"利润编号不能为空!","");
        }
        Long id = null;
        try {
            id = Long.parseLong(request.getParameter("id"));
        }catch (NumberFormatException e){
            return JsonReturn.SetMsg(10010,"利润编号转码失败!","");
        }
        int Status = profitService.delProfit(id);
        if(Status > 0){
            return JsonReturn.SetMsg(0,"利润数据删除成功!","");
        }
        return JsonReturn.SetMsg(10011,"利润数据删除失败!","");
    }

    /**
     * 通过offerId 删除利润配置及规则
     * @param offerId
     * @return
     */
    @GetMapping(value = "/del/by/offer/{offerId}")
    public Object deleteProfitByOfferIdApi(@PathVariable("offerId") Long offerId){
        if(offerId == null){
            return JsonReturn.SetMsg(10010,"报价配置编号不能为空!","");
        }
        if(profitService.deleteProfitByOfferId(offerId) > 0){
            return JsonReturn.SetMsg(0,"删除利润配置及规则成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除利润配置及规则失败!","");
    }
}
