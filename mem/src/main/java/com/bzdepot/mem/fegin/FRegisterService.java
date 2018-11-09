package com.bzdepot.mem.fegin;

import java.util.Map;
import feign.Headers;
import com.bzdepot.mem.config.CoreFeignConfiguration;
import com.bzdepot.mem.fallback.RegisterServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(value = "message-server",fallback = RegisterServiceFallback.class,configuration = CoreFeignConfiguration.class)
public interface FRegisterService {
    @RequestMapping(value = "/regMes/validCode", method = POST, consumes = "application/x-www-form-urlencoded")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    public Object getRegisterInfo(Map<String, ?> formParams);
}
