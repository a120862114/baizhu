package com.bzdepot.mem.fallback;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.mem.fegin.FRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class RegisterServiceFallback implements FRegisterService {
    private final static Logger loger = LoggerFactory.getLogger(RegisterServiceFallback.class);

    @Override
    public Object getRegisterInfo(Map<String, ?> formParams){
        return JsonReturn.SetMsg(10010,"用户校验失败!","");
    }
}
