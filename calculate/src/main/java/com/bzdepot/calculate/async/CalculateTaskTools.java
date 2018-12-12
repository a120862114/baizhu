package com.bzdepot.calculate.async;

import com.bzdepot.calculate.bo.PrintSizeBo;
import com.bzdepot.calculate.model.PrintSizeModel;
import com.bzdepot.calculate.service.AlgorithmLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Future;

@Component
public class CalculateTaskTools {

    @Autowired
    private AlgorithmLogicService algorithmLogicService;

    @Async("calculateExecutor")
    public Future<List<PrintSizeModel>> doGetPrintSizeTaskRun(PrintSizeBo printSizeBo){
       List<PrintSizeModel> printSizeModels = algorithmLogicService.getPrintSizeLogic(printSizeBo);
       if(printSizeModels != null && printSizeModels.size() > 0){
           return new AsyncResult<>(printSizeModels);
       }
       return new AsyncResult<>(null);
    }
}
