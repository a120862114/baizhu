package com.bzdepot.calculate.controller;

import com.bzdepot.calculate.async.CalculateTaskTools;
import com.bzdepot.calculate.bo.JoinSelectBo;
import com.bzdepot.calculate.bo.MinLengthBo;
import com.bzdepot.calculate.bo.PapercutNumber;
import com.bzdepot.calculate.bo.PrintSizeBo;
import com.bzdepot.calculate.model.PaperCostWithBLOBs;
import com.bzdepot.calculate.model.PrintSizeModel;
import com.bzdepot.calculate.model.PrintingCostModel;
import com.bzdepot.calculate.model.TaskPaperSizeModel;
import com.bzdepot.calculate.service.AlgorithmLogicService;
import com.bzdepot.calculate.service.CalculateLogicService;
import com.bzdepot.calculate.service.PaperAndPrintingDataLogicService;
import com.bzdepot.common.message.JsonReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;
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

    @Autowired
    private CalculateLogicService calculateLogicService;

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

    @PostMapping(value = "/get/minlength")
    public Object getMinlength(@Valid @ModelAttribute MinLengthBo minLengthBo,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        Integer minLengthNumber = algorithmLogicService.getMinLengthLogic(minLengthBo);
        if(minLengthNumber != 0){
            Integer[] PinSizeNumber = calculateLogicService.getPinSize("90*80"); //解析拼版尺寸
            BigDecimal length = calculateLogicService.getJuanTongLength(new BigDecimal("370"),PinSizeNumber[0],PinSizeNumber[1],1000);
            BigDecimal test = calculateLogicService.getUnitPrice(length,new BigDecimal("370"),new BigDecimal("157"),new BigDecimal("1000"));
            Integer maxNumber = calculateLogicService.getYuanCaiLiaoNumber(length,new BigDecimal("370"),PinSizeNumber[0],PinSizeNumber[1]);
            System.out.println(test);
            System.out.println(maxNumber);
            return JsonReturn.SetMsg(0,"获取最小长度数据成功!",minLengthNumber);
        }
        return JsonReturn.SetMsg(10011,"获取最小长度失败!","");
    }

    @PostMapping(value = "/get/config")
    public Object getConfig(@Valid @ModelAttribute JoinSelectBo joinSelectBo, BindingResult result){
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("请求参数："+joinSelectBo.toString());
        System.out.println("错误信息："+result.toString());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        System.out.println("----------=======================----------------------=====================-------------");
        System.out.println("请求参数："+joinSelectBo.toString());
        System.out.println("----------=======================----------------------=====================-------------");
        List<PaperCostWithBLOBs> paperCostModels = null;
        try {
            paperCostModels = Collections.synchronizedList(paperAndPrintingDataLogicService.getPaperAndPrintConfigData(joinSelectBo));
        }catch (Exception e){
           System.out.println("暂无符合的纸张配置与印刷机配置数据!");
        }

        if(paperCostModels != null && paperCostModels.size() > 0){
            List<Future<TaskPaperSizeModel>> futures = Collections.synchronizedList(new ArrayList<>());
            int postthreedCount = 0;
            for (int i = 0; i < paperCostModels.size(); i++){
                List<PrintingCostModel> printingCostModels = Collections.synchronizedList(paperCostModels.get(i).getPrintingData());
                if(printingCostModels == null || printingCostModels.size() == 0){
                    continue;
                }
                for(int a = 0; a < printingCostModels.size(); a++){
                    Future<TaskPaperSizeModel> futureList = calculateTaskTools.doGetPrintSizeTaskRun(paperCostModels.get(i),printingCostModels.get(a),joinSelectBo);
                    futures.add(futureList);
                    postthreedCount++;
                }
            }
            List<TaskPaperSizeModel> response = Collections.synchronizedList(new ArrayList<>());
            int getResultsCount = 0;
            for (Future future : futures) {
                try {
                    TaskPaperSizeModel taskRsultData = (TaskPaperSizeModel) future.get();
                    if(taskRsultData != null){
                        getResultsCount++;
                        response.add(taskRsultData);
                    }
                }catch (Exception e){
                    System.out.println(e.toString());
                }
            }
            System.out.println("组合的结果：==="+response.toString());
            Map<String,Object> JsonData = new HashMap<>();
            JsonData.put("postThreadCount",postthreedCount);
            JsonData.put("getResultsCount",getResultsCount);
            JsonData.put("sizeDataLength",response.size());
           // JsonData.put("printData",paperCostModels);
            JsonData.put("sizeData",response);
            return JsonReturn.SetMsg(0,"获取纸张配置与符合条件的印刷机数据成功!",JsonData);
        }
        return JsonReturn.SetMsg(10011,"暂无符合的纸张配置与印刷机配置数据!","");
    }
}
