package com.bzdepot.calculate.fallback;

import com.bzdepot.calculate.bo.MinLengthBo;
import com.bzdepot.calculate.bo.PapercutNumber;
import com.bzdepot.calculate.bo.PrintSizeBo;
import com.bzdepot.calculate.bo.ValuesPrintSizeBo;
import com.bzdepot.calculate.feign.AlgorithmService;
import com.bzdepot.common.message.JsonReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AlgorithmServiceFallback implements AlgorithmService {

    private final static Logger loger = LoggerFactory.getLogger(AlgorithmServiceFallback.class);

    @Override
    public Object getPapercutNumber(@RequestBody PapercutNumber papercutNumber){
        loger.error("调用计算拼版数量异常!");
        return JsonReturn.SetMsg(-1,"服务调用异常!","");
    }

    @Override
    public Object getPrintSize(@RequestBody PrintSizeBo printSizeBo){
        loger.error("调用计算指定最小尺寸与最大尺寸间所有自定义尺寸的拼版尺寸异常!");
        return JsonReturn.SetMsg(-1,"调用计算指定最小尺寸与最大尺寸间所有自定义尺寸的拼版尺寸异常!","");
    }

    @Override
    public Object getMinLength(@RequestBody MinLengthBo minLengthBo){
        loger.error("调用无限长纸张计算最小长度异常!");
        return JsonReturn.SetMsg(-1,"调用无限长纸张计算最小长度异常!","");
    }

    @Override
    public Object getValuesPrintSize(@RequestBody ValuesPrintSizeBo valuesPrintSizeBo){
        loger.error("调用计算性价比最高尺寸异常!");
        return JsonReturn.SetMsg(-1,"调用计算性价比最高尺寸异常","");
    }

    @Override
    public Object getCalcSymmetry(@RequestBody PapercutNumber papercutNumber){
        loger.error("调用计算对称性异常!");
        return JsonReturn.SetMsg(-1,"调用计算对称性异常","");
    }
}
