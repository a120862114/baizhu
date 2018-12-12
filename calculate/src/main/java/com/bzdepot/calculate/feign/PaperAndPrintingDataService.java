package com.bzdepot.calculate.feign;

import com.bzdepot.calculate.config.CoreFeignConfiguration;
import com.bzdepot.calculate.fallback.PaperAndPrintingDataServiceFallback;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(value = "special-service",fallback = PaperAndPrintingDataServiceFallback.class,configuration = CoreFeignConfiguration.class)
public interface PaperAndPrintingDataService {

    @RequestMapping(value = "/paper/config/join/paper/printing", method = POST, consumes = "application/x-www-form-urlencoded")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    public Object getPaperConfigJoinPrintingData(Map<String, ?> formParams);

}
