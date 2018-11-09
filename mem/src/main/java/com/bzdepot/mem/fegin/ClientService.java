package com.bzdepot.mem.fegin;

import com.bzdepot.mem.config.CoreFeignConfiguration;
import com.bzdepot.mem.fallback.ClientServiceFallback;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@FeignClient(value = "bz-client-service",fallback = ClientServiceFallback.class,configuration = CoreFeignConfiguration.class)
public interface  ClientService {

    @RequestMapping(value = "/user/add/info", method = POST, consumes = "application/x-www-form-urlencoded")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    public Object AddUserInfo(Map<String, ?> formParams);


    @RequestMapping(value = "/user/login/clien", method = POST, consumes = "application/x-www-form-urlencoded")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    public Object getUserInfo(Map<String, ?> formParams);

}
