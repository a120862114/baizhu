package com.bzdepot.calculate.async;

import com.bzdepot.calculate.bo.ColorBo;
import com.bzdepot.calculate.bo.JoinSelectBo;
import com.bzdepot.calculate.bo.PrintSizeBo;
import com.bzdepot.calculate.bo.SpotColorBo;
import com.bzdepot.calculate.model.*;
import com.bzdepot.calculate.service.AlgorithmLogicService;
import com.bzdepot.calculate.service.CalculateLogicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Future;

@Component
public class CalculateTaskTools {

    private final static Logger loger = LoggerFactory.getLogger(CalculateTaskTools.class);

    @Autowired
    private AlgorithmLogicService algorithmLogicService;

    @Autowired
    private CalculatedPriceTaskTools calculatedPriceTaskTools;

    @Autowired
    private CalculateLogicService calculateLogicService;

    /**
     * 异步计算入口
     * @param paperCostWithBLOBs 纸张配置相关的数据
     * @param printingCostModel  印刷机相关的数据
     * @param joinSelectBo  用户发送的请求参数
     * @return
     */
    @Async("calculateExecutor")
    public Future<TaskPaperSizeModel> doGetPrintSizeTaskRun(PaperCostWithBLOBs paperCostWithBLOBs, PrintingCostModel printingCostModel, JoinSelectBo joinSelectBo){
        int IsOut = 0; //是否排除当前印刷机
       //将用户输入的尺寸参数转换为Intger
        Integer UserLength = 0;
        Integer UserWidth = 0;
        Integer UserHemorrhage = 0; //出血尺寸
        try {
            UserLength = (Integer) joinSelectBo.getLongs().intValue();
            UserWidth = (Integer) joinSelectBo.getWidth().intValue();
            UserHemorrhage = (Integer) joinSelectBo.getHemorrhageNums().intValue();
        }catch (Exception e){
            loger.error("用户输入尺寸类型转换："+e.toString());
        }
        if(UserLength == 0 || UserWidth == 0){
            return new AsyncResult<TaskPaperSizeModel>(null);
        }
        //给用户输入的尺寸加上出血的尺寸
        if(UserHemorrhage != 0){
            try {
                UserLength  = (Integer) UserLength.intValue() + UserHemorrhage.intValue()*2;
                UserWidth = (Integer) UserWidth.intValue() + UserHemorrhage.intValue()*2;
            }catch (NumberFormatException e){
                e.printStackTrace();
                loger.error("出血计算类型解析"+e.toString());
            }

        }
        //将印刷机尺寸数据转换为Intger
        Integer PrintRobotMinLength = 0;
        Integer PrintRotbotMinWidth = 0;
        Integer PrintRotbotMaxLength = 0;
        Integer PrintRobotMaxWidth = 0;
        try {
            PrintRobotMinLength = (Integer) printingCostModel.getPaperFeedingMinOne().intValue();
            PrintRotbotMinWidth = (Integer) printingCostModel.getPaperFeedingMinTwo().intValue();
            PrintRotbotMaxLength = (Integer) printingCostModel.getPaperFeedingMaxOne().intValue();
            PrintRobotMaxWidth = (Integer) printingCostModel.getPaperFeedingMaxTwo().intValue();
        }catch (Exception e){
            loger.error("印刷机最大最小类型转换："+e.toString());
        }
        if(PrintRotbotMaxLength == 0 || PrintRotbotMinWidth == 0 || PrintRobotMinLength == 0 || PrintRobotMaxWidth == 0){
            return new AsyncResult<TaskPaperSizeModel>(null);
        }
        //获取此印刷机与用户自定义尺寸的拼版数量与尺寸列表数据
        PrintSizeBo printSizeBo = new PrintSizeBo(); //请求获取拼版尺寸数量的列表的参数
        printSizeBo.setPblength(UserLength); //用户输入的长度
        printSizeBo.setPbwidth(UserWidth);   //用户输入的宽度
        printSizeBo.setMinwidth(PrintRotbotMinWidth);  //印刷机最小进纸宽度
        printSizeBo.setMinlength(PrintRobotMinLength); //印刷机最小进纸长度
        printSizeBo.setMaxwidth(PrintRobotMaxWidth); //印刷机最大进纸宽度
        printSizeBo.setMaxlength(PrintRotbotMaxLength); //印刷机最大进纸长度
        //获取拼版列表数据
       List<PrintSizeModel> printSizeModels = Collections.synchronizedList(algorithmLogicService.getPrintSizeLogic(printSizeBo));
       if(printSizeModels == null || printSizeModels.size() == 0){
           return new AsyncResult<TaskPaperSizeModel>(null);
       }
       //开始整理需要计算的纸张尺寸列表
       List<TaskPaperSizeModel> taskPaperSizeModels = Collections.synchronizedList(new ArrayList<TaskPaperSizeModel>());
       //大度尺寸
       if(paperCostWithBLOBs.getIsMagnanimity().equals(new Byte("1"))){
            TaskPaperSizeModel BigDuSize = new TaskPaperSizeModel();
            BigDuSize.setSizeType(new Byte("1")); //设置类型为大度
            BigDuSize.setLength(new BigDecimal("1194"));
            BigDuSize.setWidth(new BigDecimal("889"));
            taskPaperSizeModels.add(BigDuSize); //添加到尺寸合集中
       }
       //正度尺寸
       if(paperCostWithBLOBs.getIsPositiveDegree().equals(new Byte("1"))){
           TaskPaperSizeModel ZhengDuSize = new TaskPaperSizeModel();
           ZhengDuSize.setSizeType(new Byte("0")); //设置类型为正度
           ZhengDuSize.setLength(new BigDecimal("1092"));
           ZhengDuSize.setWidth(new BigDecimal("787"));
           taskPaperSizeModels.add(ZhengDuSize);
       }
       //卷筒纸的尺寸，首先看单价单位，如果是吨则处理，如果是张则跳过处理
       if(paperCostWithBLOBs.getLastUnitType().equals(new Byte("0")) && paperCostWithBLOBs.getDrumList() != null && paperCostWithBLOBs.getDrumList().size() > 0){
            List<PaperDrumModel> paperDrumModels = Collections.synchronizedList(paperCostWithBLOBs.getDrumList()); //获取到卷筒的列表数据
            for(int i = 0; i < paperDrumModels.size(); i++){
                PaperDrumModel paperDrumModel = paperDrumModels.get(i); //获取单个数据对象
                TaskPaperSizeModel JuanTongSize = new TaskPaperSizeModel();
                JuanTongSize.setSizeType(new Byte("2")); //设置尺寸类型为卷筒
                JuanTongSize.setId(paperDrumModel.getId()); //设置原数据编号
                JuanTongSize.setIndex(i); //这是索引的数量
                JuanTongSize.setWidth(paperDrumModel.getDrum()); //设置卷筒纸的宽度
                taskPaperSizeModels.add(JuanTongSize); //将卷筒尺寸信息添加到列表中
            }
       }
       //其他尺寸
       if(paperCostWithBLOBs.getOtherSizeList() != null && paperCostWithBLOBs.getOtherSizeList().size() > 0){
           List<PaperOtherSizeModel> paperOtherSizeModels = Collections.synchronizedList(paperCostWithBLOBs.getOtherSizeList());
           for(int a = 0; a < paperOtherSizeModels.size(); a++){
               PaperOtherSizeModel paperOtherSizeModel = paperOtherSizeModels.get(a); //获取单个的其他尺寸对象
               TaskPaperSizeModel OtherSize = new TaskPaperSizeModel();
               OtherSize.setSizeType(new Byte("3")); //将尺寸类型设置为其他尺寸类型
               OtherSize.setId(paperOtherSizeModel.getId());
               OtherSize.setIndex(a);
               OtherSize.setLength(paperOtherSizeModel.getSizeOne());
               OtherSize.setWidth(paperOtherSizeModel.getSizeTwo());
               taskPaperSizeModels.add(OtherSize); //将其他尺寸添加到尺寸列表中
           }
       }

       /**
        *  根据原料尺寸集合列表开启异步线程，计算出每个原料尺寸对应拼版尺寸中最便宜的价格
        **/
       List<Future<TaskPaperSizeModel>> futures = Collections.synchronizedList(new ArrayList<>());
       for(TaskPaperSizeModel value : taskPaperSizeModels){
           Future<TaskPaperSizeModel> future = calculatedPriceTaskTools.getBestPinPrice(value,printSizeModels,joinSelectBo,paperCostWithBLOBs.getLastUnitType(),paperCostWithBLOBs.getLastDun(),printingCostModel.getMinBite(),UserHemorrhage);

           futures.add(future);
       }
       Map<Integer,BestPrintSizeModel> bestPrintSizeModelMap = new HashMap<Integer,BestPrintSizeModel>();
       for (Future future : futures) {
            try {
                if(future == null || future.get() == null){
                    continue;
                }
                TaskPaperSizeModel taskRsultData = (TaskPaperSizeModel) future.get();

                if(taskRsultData != null){
                    if(taskRsultData.getSizeList() == null || taskRsultData.getSizeList().size() == 0){
                        continue;
                    }
                    for( PrintSizeModel resData:taskRsultData.getSizeList()){
                        if(resData == null){
                            continue;
                        }
                        if(resData.getNum() == null){
                            continue;
                        }
                        BestPrintSizeModel TmpSizeList = null;

                        if(bestPrintSizeModelMap.containsKey(resData.getNum()) == true){
                            TmpSizeList = bestPrintSizeModelMap.get(resData.getNum());
                        }else{
                            TmpSizeList = new BestPrintSizeModel();
                            TmpSizeList.setNums(resData.getNum());
                        }
                        //开始重组尺寸对象集合
                        List<String> sizeList = null; //拼版的尺寸列表
                        if(TmpSizeList.getSizeList() == null || TmpSizeList.getSizeList().size() == 0){
                            sizeList = Collections.synchronizedList(new ArrayList<>());
                        }else{
                            sizeList = Collections.synchronizedList(TmpSizeList.getSizeList());
                        }
                        sizeList.add(resData.getList().get(0));
                        TmpSizeList.setSizeList(sizeList); //重新设置尺寸列表
                        //开始重组原材料金额对象集合
                        List<BigDecimal> moneyList = null;
                        if(TmpSizeList.getMoneyList() == null || TmpSizeList.getMoneyList().size() == 0){
                            moneyList = Collections.synchronizedList(new ArrayList<>());
                        }else {
                            moneyList = Collections.synchronizedList(TmpSizeList.getMoneyList());
                        }
                        moneyList.add(resData.getMoney().get(0));
                        TmpSizeList.setMoneyList(moneyList); //重新设置原材料金额列表
                        //开始重组原材料类型对象集合
                        List<Byte> sizeTypeList = null;
                        if(TmpSizeList.getSizeTypeList() == null || TmpSizeList.getSizeTypeList().size() == 0){
                            sizeTypeList = Collections.synchronizedList(new ArrayList<>());
                        }else {
                            sizeTypeList = Collections.synchronizedList(TmpSizeList.getSizeTypeList());
                        }
                        sizeTypeList.add(taskRsultData.getSizeType());
                        TmpSizeList.setSizeTypeList(sizeTypeList);
                        //开始重组数据库ID编号列表
                        List<Long> idList = null;
                        if(TmpSizeList.getIdList() == null || TmpSizeList.getIdList().size() == 0){
                            idList = Collections.synchronizedList(new ArrayList<>());
                        }else {
                            idList = Collections.synchronizedList(TmpSizeList.getIdList());
                        }
                        idList.add(taskRsultData.getId());
                        TmpSizeList.setIdList(idList);
                        //开始重组index索引编号
                        List<Integer> indexList = null;
                        if(TmpSizeList.getIndexList() == null || TmpSizeList.getIndexList().size() == 0){
                            indexList = Collections.synchronizedList(new ArrayList<>());
                        }else{
                            indexList = Collections.synchronizedList(TmpSizeList.getIndexList());
                        }
                        indexList.add(new Integer(taskRsultData.getIndex()));
                        TmpSizeList.setIndexList(indexList);
                        //开始重组原材料纸张的长度对象集合
                        List<BigDecimal> lengthList = null;
                        if(TmpSizeList.getLengthList() == null || TmpSizeList.getLengthList().size() == 0){
                            lengthList = Collections.synchronizedList(new ArrayList<>());
                        }else {
                            lengthList = Collections.synchronizedList(TmpSizeList.getLengthList());
                        }
                        lengthList.add(taskRsultData.getLength());
                        TmpSizeList.setLengthList(lengthList);
                        //开始重组原材料纸张的宽度对象集合
                        List<BigDecimal> widthList = null;
                        if(TmpSizeList.getWidthList() == null || TmpSizeList.getWidthList().size() == 0){
                            widthList = Collections.synchronizedList(new ArrayList<>());
                        }else{
                            widthList = Collections.synchronizedList(TmpSizeList.getWidthList());
                        }
                        widthList.add(taskRsultData.getWidth());
                        TmpSizeList.setWidthList(widthList);
                        //开始重组原材料的数量集合
                        List<Integer> YuanCaiLiaoList = null;
                        if(TmpSizeList.getYuanCaiLiaoNumsList() == null || TmpSizeList.getYuanCaiLiaoNumsList().size() == 0){
                            YuanCaiLiaoList = Collections.synchronizedList(new ArrayList<>());
                        }else {
                            YuanCaiLiaoList = Collections.synchronizedList(TmpSizeList.getYuanCaiLiaoNumsList());
                        }
                        YuanCaiLiaoList.add(resData.getYunCaiLiaoNums().get(0));
                        TmpSizeList.setYuanCaiLiaoNumsList(YuanCaiLiaoList);
                        List<String> lastList = null;
                        if(TmpSizeList.getLastSizeList() == null || TmpSizeList.getLastSizeList().size() == 0){
                            lastList = Collections.synchronizedList(new ArrayList<>());
                        }else{
                            lastList = Collections.synchronizedList(TmpSizeList.getLastSizeList());
                        }
                        lastList.add(resData.getLastList().get(0));
                        TmpSizeList.setLastSizeList(lastList);
                        bestPrintSizeModelMap.put(resData.getNum(),TmpSizeList);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                loger.error("拼版列表筛选："+e.toString());
            }
       }

        // 接近的数字
        BigDecimal nearNum = new BigDecimal("0");
        // 差值实始化
        BigDecimal tmpNums = null;
        BigDecimal diffNum = null;
        BestPrintSizeModel LastBestPrintData = null;
        List<BestPrintSizeModel> printDataLog = Collections.synchronizedList(new ArrayList<>());
        for (Map.Entry<Integer,BestPrintSizeModel> m : bestPrintSizeModelMap.entrySet()) {
            BestPrintSizeModel bestPrintSizeModelData = calculateLogicService.getBestPrintSize(m.getValue());
            if(bestPrintSizeModelData != null){
                //计算印刷费
                BigDecimal UserWantNums = new BigDecimal(joinSelectBo.getMakeNumber().toString());
                BigDecimal PinNums = new BigDecimal(bestPrintSizeModelData.getNums().toString());
                BigDecimal PrintNums =  UserWantNums.divide(PinNums,0,BigDecimal.ROUND_UP); //获取印刷的数量

                //计算印刷机放数
              /*  if(printingCostModel.getDischargeNumberInNums() != null && printingCostModel.getDischargeNumberIn() != null){
                    BigDecimal tmpPinNumss = null;
                    if(printingCostModel.getDischargeNumberMax() != null && printingCostModel.getDischargeNumberMaxNums() != null){
                        BigDecimal tmpFnumOne = PrintNums.subtract(new BigDecimal(printingCostModel.getDischargeNumberIn().toString()));
                        BigDecimal tmpFnumTwo = tmpFnumOne.divide(new BigDecimal(printingCostModel.getDischargeNumberMax().toString()),0,BigDecimal.ROUND_UP);
                        BigDecimal tmpFnumThree = tmpFnumTwo.multiply(new BigDecimal(printingCostModel.getDischargeNumberMaxNums().toString()));
                        tmpPinNumss = PinNums.add(tmpFnumThree);
                    }
                    PrintNums = PrintNums.add(new BigDecimal(printingCostModel.getDischargeNumberInNums()));
                    if(tmpPinNumss != null){
                        PrintNums = PrintNums.add(tmpPinNumss);
                    }
                }*/
                System.out.println("原材料纸张数量:"+PrintNums.toString());
                //设置印刷纸张的耗损数量
                bestPrintSizeModelData.setWastageNums(Integer.valueOf(PrintNums.intValue()));
                BigDecimal startPrintNums = new BigDecimal(printingCostModel.getPrintNums()); //获取开机的起印数量
                List<BigDecimal> moneyList = Collections.synchronizedList(new ArrayList<>());
                //计算印刷费
                BigDecimal PrintMoney = null;
                if(PrintNums.compareTo(startPrintNums) <= 0){
                    PrintMoney = bestPrintSizeModelData.getMoneyList().get(0).add(printingCostModel.getBootStrapMoney()).add(paperCostWithBLOBs.getLastMoney());
                    //moneyList.add();
                }else{
                    BigDecimal Synums = PrintNums.subtract(startPrintNums); //剩余需要计算印刷费的数量
                    BigDecimal Moneys = Synums.divide(new BigDecimal(printingCostModel.getExceedNums().toString()),0,BigDecimal.ROUND_UP).multiply(printingCostModel.getExceedMoney());
                    PrintMoney = bestPrintSizeModelData.getMoneyList().get(0).add(printingCostModel.getBootStrapMoney()).add(Moneys).add(paperCostWithBLOBs.getLastMoney());
                    //moneyList.add();
                }
                //是否传递了颜色参数
                if(joinSelectBo.getColorData() != null){
                    ColorBo colorBo = joinSelectBo.getColorData(); //获取到颜色具体配置对象
                    //判定是否为单面印刷
                    if(colorBo.getSided() != null && colorBo.getSided().equals(new Byte("1"))){
                        if(colorBo.getFirstColorType() == null){
                            colorBo.setFirstColorType(3);
                        }
                        //开始根据印刷机颜色与用户选择颜色计算
                        switch (colorBo.getFirstColorType()){
                            case 1:
                                //普通单色
                                break;
                            case 2:
                                //普通双色
                                if(printingCostModel.getRobotColor() != null && printingCostModel.getRobotColor().getColorNumber() != null && printingCostModel.getRobotColor().getColorNumber() < 2){
                                    PrintMoney = PrintMoney.multiply(new BigDecimal("2"));
                                }
                                break;
                             case 3:
                                //彩色
                                 if(printingCostModel.getRobotColor() != null && printingCostModel.getRobotColor().getColorNumber() != null && printingCostModel.getRobotColor().getColorNumber() < 4){
                                     //判定如果当前印刷机为 单色 或 双色 并 勾选 不印四色时淘汰此印刷机
                                     if(printingCostModel.getIsPrintFourColor() != null && printingCostModel.getIsPrintFourColor().equals(new Byte("1")) && (printingCostModel.getRobotColor().getColorNumber() == 1 || printingCostModel.getRobotColor().getColorNumber() == 2)){
                                         IsOut = 1;
                                     }else{
                                         Integer TmpsColorNums = (Integer) (4 - printingCostModel.getRobotColor().getColorNumber().intValue());
                                         BigDecimal TmpsPrintMoney = PrintMoney.multiply(new BigDecimal(TmpsColorNums.toString()));
                                         PrintMoney = PrintMoney.add(TmpsPrintMoney);
                                     }
                                 }
                                 break;
                             case 4:
                                 //彩色+专色
                                 if(printingCostModel.getSpecialColorType() != null && printingCostModel.getSpecialColorType().equals(new Byte("0"))){
                                     //如果不印专色那么淘汰此印刷机
                                     IsOut = 1;
                                 }else if(printingCostModel.getIsPrintFourColor() != null && printingCostModel.getIsPrintFourColor().equals(new Byte("1")) && (printingCostModel.getRobotColor().getColorNumber() == 1 || printingCostModel.getRobotColor().getColorNumber() == 2)) {
                                     //判定如果当前印刷机为 单色 或 双色 并 勾选 不印四色时淘汰此印刷机
                                     IsOut = 1;
                                 }else {
                                     //彩色四色部分的价格
                                     Integer TmpsColorNums = (Integer) (4 - printingCostModel.getRobotColor().getColorNumber().intValue());
                                     BigDecimal TmpsPrintMoney = PrintMoney.multiply(new BigDecimal(TmpsColorNums.toString()));
                                     PrintMoney = PrintMoney.add(TmpsPrintMoney);
                                     //颜色专色计算方式
                                     BigDecimal SpotMoney = this.spotColorMoney(printingCostModel,joinSelectBo.getColorData(),PrintNums);
                                     if(SpotMoney == null){
                                        IsOut = 1;
                                     }else {
                                         PrintMoney = PrintMoney.add(SpotMoney);
                                     }
                                 }
                                 break;
                            case 5:
                                //只印专色
                                if(printingCostModel.getSpecialColorType() != null && printingCostModel.getSpecialColorType().equals(new Byte("0"))){
                                    //如果不印专色那么淘汰此印刷机
                                    IsOut = 1;
                                }else{
                                    //颜色专色计算方式
                                    BigDecimal SpotMoney = this.spotColorMoney(printingCostModel,joinSelectBo.getColorData(),PrintNums);
                                    if(SpotMoney == null){
                                        IsOut = 1;
                                    }else {
                                        //如果专色的价格低于印刷费
                                       if(printingCostModel.getIsAnColorStatus() != null && printingCostModel.getIsAnColorStatus().equals(new Byte("1")) && SpotMoney.compareTo(PrintMoney) == -1){
                                            if(printingCostModel.getColorNumOneIn() == null || printingCostModel.getColorNumOneMoney() == null || printingCostModel.getColorNumTwoMore() == null || printingCostModel.getColorNumTwoMoreMoney() == null){
                                                IsOut = 1;
                                            }else{
                                                BigDecimal TmpMoneys = printingCostModel.getColorNumOneMoney();
                                                if(PrintNums.compareTo(new BigDecimal(printingCostModel.getColorNumOneIn().toString())) == 1){
                                                    BigDecimal TmpsNums = PrintNums.subtract(new BigDecimal(printingCostModel.getColorNumOneIn().toString()));
                                                    BigDecimal TmpsTwo = TmpsNums.divide(new BigDecimal(printingCostModel.getColorNumTwoMore().toString()),0,BigDecimal.ROUND_UP);
                                                    BigDecimal TmpsThree = TmpsTwo.multiply(printingCostModel.getColorNumTwoMoreMoney());
                                                    PrintMoney = TmpsThree;
                                                }
                                            }
                                       }else{
                                           PrintMoney = SpotMoney;
                                       }
                                    }
                                }
                                break;

                        }
                    }
                }
                if(IsOut == 1){
                    break;
                }
                //检测是否需要计算满面积
                if(printingCostModel.getIsBigSize() != null && printingCostModel.getIsBigSize().equals(new Byte("1")) && printingCostModel.getBigSizeOne() != null && printingCostModel.getBigColorTwo() != null && printingCostModel.getBigSizeThree() != null && printingCostModel.getBigSizeFour() != null && printingCostModel.getBigSizeFive() != null){
                    //计算拼版尺寸面积(平方毫米)
                    Integer[] PinSizeArr = calculateLogicService.getPinSize(bestPrintSizeModelData.getLastSizeList().get(0));
                    if(PinSizeArr != null || PinSizeArr.length == 2){
                        Integer PinArea = (Integer) (PinSizeArr[0].intValue() * PinSizeArr[1].intValue()); //拼版尺寸面积
                        BigDecimal DbArea = printingCostModel.getBigColorOne().multiply(printingCostModel.getBigColorTwo()); //大尺寸配置中的起始面积
                        BigDecimal DbAreaTwo = printingCostModel.getBigSizeThree().multiply(printingCostModel.getBigSizeFour()); //大尺寸设置中的 第二个面积
                        //如果拼版尺寸面积大于大尺寸起始面积
                        if(new BigDecimal(PinArea.toString()).compareTo(DbArea) == 1){
                            BigDecimal DbResArea = DbAreaTwo.subtract(DbArea); //大尺寸的面积差
                            BigDecimal UserResArea = new BigDecimal(PinArea.toString()).subtract(DbArea); //拼版与大尺寸起始面积差
                            BigDecimal UserPercentage = UserResArea.divide(DbResArea,2,BigDecimal.ROUND_UP); //保留两位小数(百分比)
                            BigDecimal DbPercentage = printingCostModel.getBigSizeFive().divide(new BigDecimal("100"),2,BigDecimal.ROUND_UP); //大尺寸中的百分比
                            BigDecimal LastPercentage = UserPercentage.multiply(DbPercentage); //最终的百分比
                            BigDecimal PercentageMoney = PrintMoney.multiply(LastPercentage); //大尺寸的百分比价格结果
                            PrintMoney = PrintMoney.add(PercentageMoney);
                        }
                    }
                }
                moneyList.add(PrintMoney);
                bestPrintSizeModelData.setLastMoney(moneyList);
                if(tmpNums == null){
                    tmpNums = bestPrintSizeModelData.getLastMoney().get(0).subtract(nearNum);
                    diffNum = tmpNums.abs();
                    LastBestPrintData = bestPrintSizeModelData;
                }
                BigDecimal bigDecimalNums = bestPrintSizeModelData.getLastMoney().get(0);
                BigDecimal forTmpNums = bigDecimalNums.subtract(nearNum);
                BigDecimal diffNumTemp = forTmpNums.abs();
                if(diffNumTemp.compareTo(diffNum) == -1)
                {
                    diffNum = diffNumTemp;
                    LastBestPrintData = bestPrintSizeModelData;
                }
                printDataLog.add(bestPrintSizeModelData);
            }
        }
        //淘汰此印刷机
        if(IsOut == 1){
            return new AsyncResult<TaskPaperSizeModel>(null);
        }
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println(LastBestPrintData.toString());
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        TaskPaperSizeModel LastBestTaskPaperSizeModel = new TaskPaperSizeModel();
        LastBestTaskPaperSizeModel.setBestMoney(LastBestPrintData.getMoneyList().get(0));
        LastBestTaskPaperSizeModel.setBestPinNum(LastBestPrintData.getNums());
        Integer[] SizeArrs = calculateLogicService.getPinSize(LastBestPrintData.getSizeList().get(0));
        LastBestTaskPaperSizeModel.setBestLength(SizeArrs[0]);
        LastBestTaskPaperSizeModel.setBestWidth(SizeArrs[1]);
        Integer[] LastSizeArrs = calculateLogicService.getPinSize(LastBestPrintData.getLastSizeList().get(0));
        LastBestTaskPaperSizeModel.setLastBestLength(LastSizeArrs[0]);
        LastBestTaskPaperSizeModel.setLastBestWidth(LastSizeArrs[1]);
        LastBestTaskPaperSizeModel.setSizeType(LastBestPrintData.getSizeTypeList().get(0));
        LastBestTaskPaperSizeModel.setId(LastBestPrintData.getIdList().get(0));
        LastBestTaskPaperSizeModel.setIndex(LastBestPrintData.getIndexList().get(0));
        LastBestTaskPaperSizeModel.setWidth(LastBestPrintData.getWidthList().get(0));
        LastBestTaskPaperSizeModel.setLength(LastBestPrintData.getLengthList().get(0));
        LastBestTaskPaperSizeModel.setPrintingCostModel(printingCostModel);
        LastBestTaskPaperSizeModel.setBestWastageNums(LastBestPrintData.getWastageNums());
        LastBestTaskPaperSizeModel.setLastMoney(LastBestPrintData.getLastMoney().get(0));
        LastBestTaskPaperSizeModel.setBestYuanCaiLiaoNums(LastBestPrintData.getYuanCaiLiaoNumsList().get(0));
        System.out.println("----------------------------------------------");
        System.out.println(LastBestTaskPaperSizeModel.toString());
        System.out.println("----------------------------------------------");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(printDataLog.toString());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        //逆向推算卷筒长度
        /*if(LastBestTaskPaperSizeModel.getSizeType().equals(new Byte("2"))){
            Integer MinBianNum = null;
            if(LastBestTaskPaperSizeModel.getLastBestWidth().intValue() < LastBestTaskPaperSizeModel.getLastBestLength().intValue()){
                MinBianNum = LastBestTaskPaperSizeModel.getLastBestWidth();
            }
            if(LastBestTaskPaperSizeModel.getLastBestLength().intValue() < LastBestTaskPaperSizeModel.getLastBestWidth().intValue()){
                MinBianNum = LastBestTaskPaperSizeModel.getLastBestLength();
            }
            if(LastBestTaskPaperSizeModel.getLastBestLength().intValue() == LastBestTaskPaperSizeModel.getLastBestWidth().intValue()){
                MinBianNum = LastBestTaskPaperSizeModel.getLastBestLength();
            }
            BigDecimal ResNums = new BigDecimal(MinBianNum.toString()).multiply(new BigDecimal(LastBestTaskPaperSizeModel.getBestYuanCaiLiaoNums().toString()));
            LastBestTaskPaperSizeModel.setLength(ResNums);
        }*/

        //验证尺寸是否是卷筒，如果是卷筒纸那么开始计算卷筒纸的长度
        if(LastBestTaskPaperSizeModel.getSizeType().equals(new Byte("2"))) {
            Integer YanPinMaxNumber = calculateLogicService.getYuanCaiLiaoNumber(new BigDecimal("1250"),LastBestTaskPaperSizeModel.getWidth(),LastBestTaskPaperSizeModel.getLastBestLength(), LastBestTaskPaperSizeModel.getLastBestWidth());
            if(YanPinMaxNumber > 0){
                LastBestTaskPaperSizeModel.setBestYuanCaiLiaoNums(YanPinMaxNumber);
            }
            BigDecimal length = calculateLogicService.getJuanTongLength(LastBestTaskPaperSizeModel.getWidth(), LastBestTaskPaperSizeModel.getLastBestLength(), LastBestTaskPaperSizeModel.getLastBestWidth(), LastBestTaskPaperSizeModel.getBestYuanCaiLiaoNums());
            if (length != null) {
                LastBestTaskPaperSizeModel.setLength(length);
            }
        }
       return new AsyncResult<TaskPaperSizeModel>(LastBestTaskPaperSizeModel);
    }


    /**
     * 计算专色的价格
     * @param printingCostModel
     * @param colorBo
     * @param printNumber
     * @return
     */
    public BigDecimal spotColorMoney(PrintingCostModel printingCostModel,ColorBo colorBo,BigDecimal printNumber){
        List<SpotColorBo> spotColorBos = Collections.synchronizedList(colorBo.getFirstSpotColorList()); //获取专色集合对象
        if(spotColorBos == null || spotColorBos.size() == 0){
            return null;
        }
        BigDecimal Money = new BigDecimal("0"); //总价格
        if(printingCostModel.getSpecialColorType() != null && printingCostModel.getSpecialColorType().equals(new Byte("2"))){
            //单独的专色配置参数来计算每个专色的价格
            List<PrintingSpotConfigModel> printingSpotConfigModels = Collections.synchronizedList(printingCostModel.getColorConfigs());
            if(printingSpotConfigModels == null || printingSpotConfigModels.size() == 0){
                return null;
            }
            for(int i = 0; i < printingSpotConfigModels.size(); i++){
                if(printingSpotConfigModels.get(i).getColorDetail() == null || printingSpotConfigModels.get(i).getColorDetail().getId() == null){
                    continue;
                }
                if(printingSpotConfigModels.get(i).getInMoney() == null || printingSpotConfigModels.get(i).getInNums() == null || printingSpotConfigModels.get(i).getMoreMoney() == null || printingSpotConfigModels.get(i).getMoreNums() == null){
                    continue;
                }
                Long SpotColorId = printingSpotConfigModels.get(i).getColorDetail().getId(); //专色ID
                for(int b = 0; b < spotColorBos.size(); b++){
                    //判定是否属于同个专色
                    if(SpotColorId.equals(spotColorBos.get(b).getSpotColorId()) && printingSpotConfigModels.get(i).getStatus().equals(new Byte("1"))){
                        //开始计算专色的价格
                        BigDecimal TmpMoney = printingSpotConfigModels.get(i).getInMoney(); //专色初始价格
                        if(printNumber.compareTo(new BigDecimal(printingSpotConfigModels.get(i).getInNums().toString())) == 1){
                           BigDecimal TmpNumber = printNumber.subtract(new BigDecimal(printingSpotConfigModels.get(i).getInNums().toString()));
                           BigDecimal TmpTwo = TmpNumber.divide(new BigDecimal(printingSpotConfigModels.get(i).getMoreNums().toString()),0,BigDecimal.ROUND_UP);
                           BigDecimal TmpThree = TmpTwo.multiply(printingSpotConfigModels.get(i).getMoreMoney());
                            TmpMoney = TmpMoney.add(TmpThree); //单个专色的价格
                        }
                        Money = Money.add(TmpMoney);
                    }
                }
            }

        }else if(printingCostModel.getSpecialColorType() != null && printingCostModel.getSpecialColorType().equals(new Byte("1"))){
            //按CMYK百分比计算
            if(printingCostModel.getSpecialColorOneRate() == null || printingCostModel.getCmykBootStrapMoney() == null || printingCostModel.getCmykExceedMoney() == null || printingCostModel.getCmykExceedNums() == null || printingCostModel.getCmykPrintNums() == null){
                return null;
            }
            BigDecimal TmpMoney = printingCostModel.getCmykBootStrapMoney(); //初始化一个CMYK开机价格
            if(printNumber.compareTo(new BigDecimal(printingCostModel.getCmykPrintNums().toString())) == 1){
                BigDecimal TmpNumber = printNumber.subtract(new BigDecimal(printingCostModel.getCmykPrintNums().toString()));
                BigDecimal TmpTwo = TmpNumber.divide(new BigDecimal(printingCostModel.getCmykExceedNums().toString()),0,BigDecimal.ROUND_UP);
                BigDecimal TmpThree = TmpTwo.multiply(printingCostModel.getExceedMoney());
                BigDecimal TmpFour = printingCostModel.getSpecialColorOneRate().divide(new BigDecimal("100")).multiply(TmpThree); //乘以概率
                TmpMoney = TmpMoney.add(TmpFour);
            }
            TmpMoney = TmpMoney.multiply(new BigDecimal(spotColorBos.size())); //乘以专色的个数
            Money = Money.add(TmpMoney);
        }
        return Money;
    }
}
