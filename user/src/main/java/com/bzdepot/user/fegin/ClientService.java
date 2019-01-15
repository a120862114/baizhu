package com.bzdepot.user.fegin;

import com.bzdepot.user.config.CoreFeignConfiguration;
import com.bzdepot.user.fallback.ClientServiceFallback;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@FeignClient(value = "bz-client-service",fallback = ClientServiceFallback.class,configuration = CoreFeignConfiguration.class)
public interface  ClientService {

    @RequestMapping(value = "/user/update/info", method = POST, consumes = "application/x-www-form-urlencoded")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    public Object UpdateUserInfo(Map<String, ?> formParams);

}
