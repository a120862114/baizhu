package com.bzdepot.offer.fegin;

import com.bzdepot.offer.config.CoreFeignConfiguration;
import com.bzdepot.offer.fallback.ExpressMoneyServiceFallback;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(value = "bz-transport",fallback =ExpressMoneyServiceFallback.class,configuration = CoreFeignConfiguration.class)
public interface ExpressMoneyService {

    @RequestMapping(value = "/block/city/config", method = POST, consumes = "application/x-www-form-urlencoded")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    public Object findCityConfig(Map<String, ?> formParams);

}
