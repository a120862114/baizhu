package com.bzdepot.offer.fallback;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.offer.fegin.ProfitConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ProfitConfigServiceFallback implements ProfitConfigService {
    private final static Logger loger = LoggerFactory.getLogger(ProfitConfigServiceFallback.class);

    @Override
    public Object callFunc(@PathVariable("offerId") Long offerId){
        loger.error("ProfitConfigService调用异常");
        return JsonReturn.SetMsg(10011,"ProfitConfigService调用异常!","");
    }

    @Override
    public Object findDataByLevel(@PathVariable("sellerid") Long sellerid,@PathVariable("comanyid") Long comanyid,@PathVariable("types") Byte types,@PathVariable("levelid") Long levelid){
        loger.error("发票信息调用异常");
        return JsonReturn.SetMsg(10011,"发票信息调用异常!","");
    }

}
