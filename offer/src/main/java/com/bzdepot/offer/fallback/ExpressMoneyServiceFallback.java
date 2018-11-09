package com.bzdepot.offer.fallback;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.offer.fegin.ExpressMoneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExpressMoneyServiceFallback implements ExpressMoneyService {
    private final static Logger loger = LoggerFactory.getLogger(ExpressMoneyServiceFallback.class);
    @Override
    public Object findCityConfig(Map<String, ?> formParams){
        loger.error("计算快递费服务调用异常失败!");
        return JsonReturn.SetMsg(10010,"计算快递费服务调用异常失败!","");
    }
}
