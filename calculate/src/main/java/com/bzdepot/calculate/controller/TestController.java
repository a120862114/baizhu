package com.bzdepot.calculate.controller;

import com.bzdepot.calculate.async.CalculateTaskTools;
import com.bzdepot.calculate.bo.JoinSelectBo;
import com.bzdepot.calculate.bo.PapercutNumber;
import com.bzdepot.calculate.bo.PrintSizeBo;
import com.bzdepot.calculate.model.PaperCostWithBLOBs;
import com.bzdepot.calculate.model.PrintSizeModel;
import com.bzdepot.calculate.model.PrintingCostModel;
import com.bzdepot.calculate.service.AlgorithmLogicService;
import com.bzdepot.calculate.service.PaperAndPrintingDataLogicService;
import com.bzdepot.common.message.JsonReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private AlgorithmLogicService algorithmLogicService;

    @Autowired
    private PaperAndPrintingDataLogicService paperAndPrintingDataLogicService;

    @Autowired
    private CalculateTaskTools calculateTaskTools;

    @PostMapping(value = "/get/nums")
    public Object getNums(@Valid @ModelAttribute PapercutNumber papercutNumber, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        Integer Num = algorithmLogicService.getPapercutNumberLogic(papercutNumber);
        return JsonReturn.SetMsg(0,"获取拼数量成功!",Num);
    }

    @PostMapping(value = "/get/size")
    public Object getPrintSize(@Valid @ModelAttribute PrintSizeBo printSizeBo,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        List<PrintSizeModel> printSizeModels = algorithmLogicService.getPrintSizeLogic(printSizeBo);
        if(printSizeModels != null && printSizeModels.size() > 0){
            return JsonReturn.SetMsg(0,"获取自定义尺寸成功!",printSizeModels);
        }
        return JsonReturn.SetMsg(10011,"获取自定义尺寸失败!","");
    }

    @PostMapping(value = "/get/config")
    public Object getConfig(@Valid @ModelAttribute JoinSelectBo joinSelectBo, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }

        List<PaperCostWithBLOBs> paperCostModels = paperAndPrintingDataLogicService.getPaperAndPrintConfigData(joinSelectBo);
        if(paperCostModels != null && paperCostModels.size() > 0){
            List<Future<List<PrintSizeModel>>> futures = new ArrayList<>();
            for (int i = 0; i < paperCostModels.size(); i++){
                List<PrintingCostModel> printingCostModels = paperCostModels.get(i).getPrintingData();
                if(printingCostModels == null || printingCostModels.size() == 0){
                    continue;
                }
                for(int a = 0; a < printingCostModels.size(); a++){
                    PrintSizeBo printSizeBo = new PrintSizeBo();
                    printSizeBo.setPblength(210);
                    printSizeBo.setPbwidth(140);
                    printSizeBo.setMaxlength(1020);
                    printSizeBo.setMaxwidth(720);
                    printSizeBo.setMinlength(285);
                    printSizeBo.setMinwidth(210);
                    Future<List<PrintSizeModel>> futureList = calculateTaskTools.doGetPrintSizeTaskRun(printSizeBo);
                    futures.add(futureList);
                }
            }
            List<List<PrintSizeModel>> response = new ArrayList<>();
            for (Future future : futures) {
                try {
                    List<PrintSizeModel> taskRsultData = (List<PrintSizeModel>) future.get();
                    response.add(taskRsultData);
                }catch (Exception e){
                    System.out.println(e.toString());
                }
            }
            System.out.println(response.toString());

            return JsonReturn.SetMsg(0,"获取纸张配置与符合条件的印刷机数据成功!",paperCostModels);
        }
        return JsonReturn.SetMsg(10011,"获取纸张配置与符合条件的印刷机数据失败!","");
    }
}
