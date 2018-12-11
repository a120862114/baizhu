package com.bzdepot.offer.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.offer.service.OfferService;
import com.bzdepot.offer.vo.ChaJiaBeenVo;
import com.bzdepot.offer.vo.SelectOfferMoneyVo;
import com.bzdepot.offer.vo.SelectOfferVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
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
        List<SelectOfferVo> selectOfferVos = null;
        if(selectOfferMoneyVo.getIsSumer().equals(new Byte("0"))){
            selectOfferVos = offerService.findAllOfferAndSonDatas(selectOfferMoneyVo);
        }else {
            selectOfferVos = offerService.findAllForSummer(selectOfferMoneyVo);
        }
        if(selectOfferVos != null && selectOfferVos.size() > 0){
            return JsonReturn.SetMsg(0,"报价计算成功!",selectOfferVos);
        }
        return JsonReturn.SetMsg(10011,"由于某些其他原因报价计算失败,请联系商家完善报价配置参数!","");
    }

    @GetMapping("/math/check/{Nums}")
    public Object mathCheckNumsApi(@PathVariable("Nums") BigDecimal Nums){
        List<BigDecimal> list = new ArrayList<BigDecimal>();
        for (int i = 0; i < 10; i++)
        {
            list.add(new BigDecimal(i));
        }
        // 接近的数字
        BigDecimal nearNum = Nums;
        // 差值实始化
        BigDecimal tmpNums = list.get(0).subtract(nearNum);
        BigDecimal diffNum = tmpNums.abs();
        // 最终结果
        BigDecimal result = list.get(0);
        for (BigDecimal bigDecimalNums : list)
        {
            BigDecimal forTmpNums = bigDecimalNums.subtract(nearNum);
            BigDecimal diffNumTemp = forTmpNums.abs();
            if(diffNumTemp.compareTo(diffNum) == -1)
            {
                diffNum = diffNumTemp;
                result = bigDecimalNums;
            }
        }
       return JsonReturn.SetMsg(0,"最相似的值",result);
    }

    @PostMapping(value = "/offer/moneys")
    public Object selectOfferMoneys(@Valid @ModelAttribute SelectOfferMoneyVo selectOfferMoneyVo, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }

        List<SelectOfferVo> selectOfferVos = offerService.findAllForSummer(selectOfferMoneyVo);
        return JsonReturn.SetMsg(0,"获取接口测试",selectOfferVos);
    }
}
