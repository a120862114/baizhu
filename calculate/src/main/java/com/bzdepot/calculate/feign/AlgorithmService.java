package com.bzdepot.calculate.feign;

import com.bzdepot.calculate.bo.MinLengthBo;
import com.bzdepot.calculate.bo.PapercutNumber;
import com.bzdepot.calculate.bo.PrintSizeBo;
import com.bzdepot.calculate.bo.ValuesPrintSizeBo;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "algorithm-service",url = "http://106.15.203.69:8080/api")
public interface AlgorithmService {

    @RequestMapping(value = "/papercut/getMaxNum", method = RequestMethod.POST)
    @Headers("Content-Type: application/json")
    public Object getPapercutNumber(@RequestBody PapercutNumber papercutNumber);

    @RequestMapping(value = "/papercut/getPrintSize", method = RequestMethod.POST)
    @Headers("Content-Type: application/json")
    public Object getPrintSize(@RequestBody PrintSizeBo printSizeBo);

    @RequestMapping(value = "/papercut/getMinLength", method = RequestMethod.POST)
    @Headers("Content-Type: application/json")
    public Object getMinLength(@RequestBody MinLengthBo minLengthBo);

    @RequestMapping(value = "/papercut/getValuesPrintSize", method = RequestMethod.POST)
    @Headers("Content-Type: application/json")
    public Object getValuesPrintSize(@RequestBody ValuesPrintSizeBo valuesPrintSizeBo);

    @RequestMapping(value = "/papercut/calcSymmetry", method = RequestMethod.POST)
    @Headers("Content-Type: application/json")
    public Object getCalcSymmetry(@RequestBody PapercutNumber papercutNumber);
}
