package com.bzdepot.transport.fallback;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.transport.fegin.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceFallback implements InvoiceService {
    private final static Logger loger = LoggerFactory.getLogger(InvoiceServiceFallback.class);

    @Override
    public Object findCheckInvoiceDataApi(Long sellerId, Long companyId) {
        return JsonReturn.SetMsg(10011,"调用服务异常","");
    }
}
