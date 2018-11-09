package com.bzdepot.offer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.offer.model.Profit;
import com.bzdepot.offer.model.ProfitDefault;
import com.bzdepot.offer.service.ProfitService;
import com.bzdepot.offer.vo.ProfitAttrVo;
import com.bzdepot.offer.vo.ProfitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/profit/config")
public class ProfitController {

    @Autowired
    private ProfitService profitService;

    /*
    *   添加利润配置
    * */
    @PostMapping(value = "/update")
    public Object addProfit(@Valid @ModelAttribute ProfitVo profitvo, BindingResult result, HttpServletRequest request){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        Byte Types = 2;
        if(profitvo.getTypes() == Types && profitvo.getParentOfferId() == null){
             return JsonReturn.SetMsg(10010,"类型为调用模式时，所继承的配置数据编号不能为空!","");
        }
        if(request.getParameter("config") == null){
            return JsonReturn.SetMsg(10010,"没有需要修改的数据!","");
        }
        List<ProfitAttrVo> profitAttrVos =  JSON.parseObject(request.getParameter("config"),new TypeReference<List<ProfitAttrVo>>(){});
        if(profitAttrVos.size() == 0){
            return JsonReturn.SetMsg(10010,"请填写完整的利润配置信息!","");
        }
        int Ok = profitService.addProfit(profitvo,profitAttrVos);
        if(Ok > 0){
            return JsonReturn.SetMsg(0,"添加利润配置成功!","");
        }
        return JsonReturn.SetMsg(10011,"添加利润配置失败!","");
    }

    /**
     * 修改利润配置
     * @param request
     * @return
     */
    @PostMapping(value = "/edit")
    public Object editProfit(HttpServletRequest request){
      if(request.getParameter("config") == null){
          return JsonReturn.SetMsg(10010,"没有需要修改的数据!","");
      }
        List<Profit> profit =  JSON.parseObject(request.getParameter("config"),new TypeReference<List<Profit>>(){});
        //开始修改数据
        if(profit.size() == 0){
            return JsonReturn.SetMsg(10010,"请填写完整的利润配置信息!","");
        }
        int Status = profitService.editProfit(profit);
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
     * 获取所属报价的利润数据列表
     * @param request
     * @return
     */
    @PostMapping(value = "/list/profit")
    public Object delProfigById(HttpServletRequest request){
        if(request.getParameter("offer_id") == null){
            return JsonReturn.SetMsg(10010,"报价配置编号不能为空!","");
        }
        Long OfferId = null;
        try {
            OfferId = Long.parseLong(request.getParameter("offer_id"));
        }catch (NumberFormatException e){
            return JsonReturn.SetMsg(10010,"报价配置编号转码失败!","");
        }
        List<Profit> ReData = profitService.getProfitList(OfferId);
        if(ReData.size() > 0){
            ProfitDefault profitDefault = profitService.findDefaultProfitType(OfferId);
            Map<String,Object> datas = new HashMap<String, Object>();
            datas.put("profit",ReData);
            datas.put("default",profitDefault);
            return JsonReturn.SetMsg(0,"利润数据获取成功!",datas);
        }
        return JsonReturn.SetMsg(10011,"利润数据获取失败!","");
    }

    @PostMapping(value = "/update/dafault")
    public Object updateDafault(@Valid @ModelAttribute ProfitDefault profitDefault,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }

        Long Id = profitService.updateProfitDefault(profitDefault);
        if(Id != null){
            return JsonReturn.SetMsg(0,"更新默认配置成功!",Id);
        }
        return JsonReturn.SetMsg(10011,"更新默认配置失败!","");
    }

    @GetMapping(value = "/find/default/{offerid}")
    public Object findDefaultData(@PathVariable("offerid") Long offerid){
        if(offerid == null){
            return JsonReturn.SetMsg(10010,"配置编号不能为空!","");
        }
        ProfitDefault profitDefault = profitService.findDefaultProfitType(offerid);
        if(profitDefault != null){
            return JsonReturn.SetMsg(0,"获取默认配置成功",profitDefault);
        }
        return JsonReturn.SetMsg(10011,"暂无默认利润数据!","");
    }
}
