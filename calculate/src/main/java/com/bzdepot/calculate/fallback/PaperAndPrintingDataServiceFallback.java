package com.bzdepot.calculate.fallback;

import com.bzdepot.calculate.feign.PaperAndPrintingDataService;
import com.bzdepot.common.message.JsonReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaperAndPrintingDataServiceFallback implements PaperAndPrintingDataService {

    private final static Logger loger = LoggerFactory.getLogger(PaperAndPrintingDataServiceFallback.class);

    @Override
    public Object getPaperConfigJoinPrintingData(Map<String, ?> formParams){
        loger.error("调用专版服务异常!");
        return JsonReturn.SetMsg(-1,"调用专版服务异常!","");
    }
}
