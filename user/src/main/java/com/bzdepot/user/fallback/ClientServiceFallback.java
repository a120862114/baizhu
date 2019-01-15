package com.bzdepot.user.fallback;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.user.fegin.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ClientServiceFallback implements ClientService {

    private final static Logger loger = LoggerFactory.getLogger(ClientServiceFallback.class);

    @Override
    public Object UpdateUserInfo(Map<String, ?> formParams){
        loger.error("ClientUser调用异常");
        return JsonReturn.SetMsg(10010,"创建子数据失败!","");
    }
}
