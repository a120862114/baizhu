package com.bzdepot.transport.fegin;


import com.bzdepot.transport.config.CoreFeignConfiguration;
import com.bzdepot.transport.fallback.InvoiceServiceFallback;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "profit-service",fallback =InvoiceServiceFallback.class,configuration = CoreFeignConfiguration.class)
public interface InvoiceService {


    @RequestMapping(value = "/invoice/find/check/{sellerId}/{companyId}", method =RequestMethod.GET, consumes = "application/x-www-form-urlencoded")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    public Object findCheckInvoiceDataApi(@PathVariable("sellerId") Long sellerId,@PathVariable("companyId") Long companyId);
}
