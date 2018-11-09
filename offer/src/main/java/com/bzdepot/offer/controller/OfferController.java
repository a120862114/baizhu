package com.bzdepot.offer.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.offer.service.OfferService;
import com.bzdepot.offer.vo.ChaJiaBeenVo;
import com.bzdepot.offer.vo.SelectOfferMoneyVo;
import com.bzdepot.offer.vo.SelectOfferVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/select")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping(value = "/money")
    public Object selectMoney (@Valid @ModelAttribute ChaJiaBeenVo chaJiaBeenVo, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        System.out.println(chaJiaBeenVo.toString());
        SelectOfferVo selectOfferVo = offerService.findOfferMoney(chaJiaBeenVo.getOfferId(),chaJiaBeenVo.getNums());
        System.out.println(selectOfferVo);
        if(selectOfferVo != null){
            BigDecimal money = offerService.getProfitConfig(chaJiaBeenVo.getOfferId(),selectOfferVo.getXmoney(),selectOfferVo,chaJiaBeenVo.getNums(),chaJiaBeenVo.getComanyId(),chaJiaBeenVo.getCityId(),chaJiaBeenVo.getInTypes());
            System.out.println("------------------------------------------------------------");
            System.out.println(money);
            return JsonReturn.SetMsg(0,"获取数据成功!",money);
        }
        return JsonReturn.SetMsg(10011,"获取数据失败!","");
    }

    @PostMapping(value = "/offer/money")
    public Object selectOfferMoney(@Valid @ModelAttribute SelectOfferMoneyVo selectOfferMoneyVo, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }

        List<SelectOfferVo> selectOfferVos = offerService.findAllOfferAndSonDatas(selectOfferMoneyVo);
        return JsonReturn.SetMsg(0,"获取接口测试",selectOfferVos);
    }
}
