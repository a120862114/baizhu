package com.bzdepot.mem.fallback;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.mem.fegin.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ClientServiceFallback implements ClientService {

    private final static Logger loger = LoggerFactory.getLogger(ClientServiceFallback.class);

    @Override
    public Object AddUserInfo(Map<String, ?> formParams){
        loger.error("ClientUser调用异常");
        return JsonReturn.SetMsg(10010,"创建子数据失败!","");
    }

    @Override
    public Object getUserInfo(Map<String, ?> formParams) {
        return JsonReturn.SetMsg(10010,"没有找到子数据!","");
    }



}
