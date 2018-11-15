package com.bzdepot.offer.fegin;

import com.bzdepot.offer.config.CoreFeignConfiguration;
import com.bzdepot.offer.fallback.ProfitConfigServiceFallback;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "profit-service",fallback =ProfitConfigServiceFallback.class,configuration = CoreFeignConfiguration.class)
public interface ProfitConfigService {

    @RequestMapping(value = "/profit/call/{offerId}", method =RequestMethod.GET, consumes = "application/x-www-form-urlencoded")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    public Object callFunc(@PathVariable("offerId") Long offerId);

    @RequestMapping(value = "/invoice/by/level/{sellerid}/{comanyid}/{types}/{levelid}", method =RequestMethod.GET, consumes = "application/x-www-form-urlencoded")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    public Object findDataByLevel(@PathVariable("sellerid") Long sellerid,@PathVariable("comanyid") Long comanyid,@PathVariable("types") Byte types,@PathVariable("levelid") Long levelid);

    @RequestMapping(value = "/profit/del/by/offer/{offerId}", method =RequestMethod.GET, consumes = "application/x-www-form-urlencoded")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    public Object deleteProfitByOfferIdApi(@PathVariable("offerId") Long offerId);
}
