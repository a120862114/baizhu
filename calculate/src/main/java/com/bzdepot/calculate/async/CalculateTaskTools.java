package com.bzdepot.calculate.async;

import com.bzdepot.calculate.bo.*;
import com.bzdepot.calculate.model.*;
import com.bzdepot.calculate.service.AlgorithmLogicService;
import com.bzdepot.calculate.service.CalculateLogicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
   // @Async("calculateExecutor")
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
        System.out.println("出血后的长:"+UserLength.toString());
        System.out.println("出血后的宽:"+UserWidth.toString());
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
           Future<TaskPaperSizeModel> future = calculatedPriceTaskTools.getBestPinPrice(value,printSizeModels,joinSelectBo,paperCostWithBLOBs.getLastUnitType(),paperCostWithBLOBs.getLastDun(),printingCostModel.getMinBite(),UserHemorrhage,printingCostModel,paperCostWithBLOBs.getLastMoney());

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
                System.out.println("原材料拼版数量:"+bestPrintSizeModelData.getYuanCaiLiaoNumsList().toString());
                //计算印刷费
                BigDecimal UserWantNums = new BigDecimal(joinSelectBo.getMakeNumber().toString());
                BigDecimal PinNums = new BigDecimal(bestPrintSizeModelData.getNums().toString());
                BigDecimal PrintNumsTmp =  UserWantNums.divide(PinNums,0,BigDecimal.ROUND_UP).divide(new BigDecimal(bestPrintSizeModelData.getYuanCaiLiaoNumsList().get(0).toString()),0,BigDecimal.ROUND_UP); //获取印刷的数量
                BigDecimal PrintNums =  UserWantNums.divide(PinNums,0,BigDecimal.ROUND_UP); //获取印刷的数量
                if(joinSelectBo.getColorData() != null && joinSelectBo.getColorData().getSided() != null && joinSelectBo.getColorData().getSided().equals(new Byte("2"))){
                    PrintNums = PrintNums.multiply(new BigDecimal("2"));
                }
                //计算印刷机放数
                BigDecimal YsSunHao = new BigDecimal("0"); //印刷损耗
                if(printingCostModel.getDischargeNumberInNums() != null && printingCostModel.getDischargeNumberIn() != null){
                    BigDecimal tmpPinNumss = null;
                    if(printingCostModel.getDischargeNumberMax() != null && printingCostModel.getDischargeNumberMaxNums() != null){
                        BigDecimal tmpFnumOne = PrintNumsTmp.subtract(new BigDecimal(printingCostModel.getDischargeNumberIn().toString()));
                        if(tmpFnumOne.compareTo(new BigDecimal(printingCostModel.getDischargeNumberIn().toString())) >= 0){
                            BigDecimal tmpFnumTwo = tmpFnumOne.divide(new BigDecimal(printingCostModel.getDischargeNumberMax().toString()),0,BigDecimal.ROUND_UP);
                            BigDecimal tmpFnumThree = tmpFnumTwo.multiply(new BigDecimal(printingCostModel.getDischargeNumberMaxNums().toString()));
                            tmpPinNumss = tmpFnumThree.add(new BigDecimal(printingCostModel.getDischargeNumberInNums().toString()));
                        }else{
                            tmpPinNumss = new BigDecimal(printingCostModel.getDischargeNumberInNums().toString());
                        }
                    }
                    if(tmpPinNumss != null){
                        YsSunHao = tmpPinNumss;
                        bestPrintSizeModelData.setPrintWastageNums(Integer.valueOf(YsSunHao.intValue()));
                    }
                }
                System.out.println("原材料纸张数量:"+PrintNumsTmp.toString());
                System.out.println("印刷损耗:"+YsSunHao.toString());
                System.out.println("印刷损耗SS:"+PrintNums.toString());
                //设置印刷纸张的耗损数量
                bestPrintSizeModelData.setWastageNums(Integer.valueOf(PrintNumsTmp.intValue()+YsSunHao.intValue()/bestPrintSizeModelData.getYuanCaiLiaoNumsList().get(0).intValue()));
                BigDecimal startPrintNums = new BigDecimal(printingCostModel.getPrintNums()); //获取开机的起印数量
                List<BigDecimal> moneyList = Collections.synchronizedList(new ArrayList<>());
                //计算印刷费
                BigDecimal PrintMoney = null;
                if(PrintNums.compareTo(startPrintNums) <= 0){
                    PrintMoney = printingCostModel.getBootStrapMoney();
                    //moneyList.add();
                }else{
                    BigDecimal Synums = PrintNums.subtract(startPrintNums); //剩余需要计算印刷费的数量
                    //获取每张多少钱
                    BigDecimal TmpOneMoney = printingCostModel.getExceedMoney().divide(new BigDecimal(printingCostModel.getExceedNums().toString()),2,BigDecimal.ROUND_UP);
                    //用每张多少钱乘以剩下的数量
                    BigDecimal Moneys = TmpOneMoney.multiply(Synums);
                    //BigDecimal Moneys = Synums.divide(new BigDecimal(printingCostModel.getExceedNums().toString()),0,BigDecimal.ROUND_UP).multiply(printingCostModel.getExceedMoney());
                    PrintMoney = printingCostModel.getBootStrapMoney().add(Moneys);
                    //moneyList.add();
                }
                System.out.println("印刷费哈哈哈哈哈:"+PrintMoney.toString());
                BigDecimal ColorMoney = new BigDecimal("0");
                //是否传递了颜色参数
                if(joinSelectBo.getColorData() != null){
                   ColorMoneyModel colorMoneyModel = this.colorFunc(1,PrintNums,PrintMoney,joinSelectBo.getColorData(),printingCostModel); //第一面的颜色费用
                   if(colorMoneyModel != null){
                       IsOut = colorMoneyModel.getIsOut();
                       ColorMoney =colorMoneyModel.getColorMoney().add(colorMoneyModel.getSpotColorMoney()); //颜色与专色价格
                   }
                    if(IsOut == 1){
                        break;
                    }
                   //如果是双面的颜色参数
                   if(joinSelectBo.getColorData().getSided() != null && joinSelectBo.getColorData().getSided().equals(new Byte("2"))){
                       ColorMoneyModel colorMoneyModelTwo = this.colorFunc(2,PrintNums,PrintMoney,joinSelectBo.getColorData(),printingCostModel); //第二面的颜色费用
                       if(colorMoneyModelTwo != null){
                           IsOut = colorMoneyModelTwo.getIsOut();
                       }
                       if(IsOut == 1){
                           break;
                       }
                       SpotColorTmpModel spotColorTmpModel = this.checkColor(joinSelectBo.getColorData()); //验证两个面是否互相包含
                        //互相不包含
                       if(spotColorTmpModel.getStatus() == 0){
                            ColorMoney = colorMoneyModel.getColorMoney().add(colorMoneyModel.getSpotColorMoney()).add(colorMoneyModelTwo.getColorMoney().add(colorMoneyModelTwo.getSpotColorMoney()));
                       }else {
                        //互相包含,检测是否对称
                           Integer[] PinSizeArrs = calculateLogicService.getPinSize(bestPrintSizeModelData.getLastSizeList().get(0));

                           if(PinSizeArrs != null && PinSizeArrs.length == 2 && this.checkSymmetric(bestPrintSizeModelData.getLengthList().get(0),bestPrintSizeModelData.getWidthList().get(0),PinSizeArrs[0],PinSizeArrs[1]) == true){
                               //对称
                               ColorMoneyModel colorMoneyModelS = null;
                               switch (spotColorTmpModel.getStatus()){
                                   case 1:
                                       colorMoneyModelS = this.colorFunc(1,PrintNums.multiply(new BigDecimal("2")),PrintMoney,joinSelectBo.getColorData(),printingCostModel);
                                       break;
                                   case 2:
                                       colorMoneyModelS = this.colorFunc(2,PrintNums.multiply(new BigDecimal("2")),PrintMoney,joinSelectBo.getColorData(),printingCostModel);
                                       break;
                               }
                               ColorMoney = colorMoneyModelS.getColorMoney().add(colorMoneyModelS.getSpotColorMoney());
                           }else{
                                //不对称
                               switch (spotColorTmpModel.getStatus()){
                                   case 1:
                                       ColorMoney = colorMoneyModel.getColorMoney().add(colorMoneyModel.getSpotColorMoney()).multiply(new BigDecimal("2"));
                                       break;
                                   case 2:
                                       ColorMoney = colorMoneyModelTwo.getColorMoney().add(colorMoneyModel.getSpotColorMoney()).multiply(new BigDecimal("2"));
                                       break;
                               }
                           }
                           //计算多出来的专色的价格
                           if(spotColorTmpModel.getSpotColorBo() != null && spotColorTmpModel.getSpotColorBo().size() > 0){
                               ColorBo colorBoTmp = joinSelectBo.getColorData();
                               if(colorBoTmp != null){
                                   if(spotColorTmpModel.getStatus() == 1){
                                       colorBoTmp.setFirstSpotColorList(spotColorTmpModel.getSpotColorBo());
                                   }else {
                                        colorBoTmp.setSecondSpotColorList(spotColorTmpModel.getSpotColorBo());
                                   }
                                   BigDecimal OutSpotColorMoney = this.spotColorMoney(spotColorTmpModel.getStatus(),printingCostModel,colorBoTmp,PrintNums);
                                   if(OutSpotColorMoney != null){
                                       ColorMoney = ColorMoney.add(OutSpotColorMoney); //加上多处来的专色的价格
                                   }
                               }
                           }
                       }
                   }
                }

                Integer[] PinSizeArr = calculateLogicService.getPinSize(bestPrintSizeModelData.getLastSizeList().get(0));
                //检测是否需要计算满面积
                BigDecimal BigSizeMoney = new BigDecimal("0");
                if(printingCostModel.getIsBigSize() != null && printingCostModel.getIsBigSize().equals(new Byte("1")) && printingCostModel.getBigSizeOne() != null && printingCostModel.getBigColorTwo() != null && printingCostModel.getBigSizeThree() != null && printingCostModel.getBigSizeFour() != null && printingCostModel.getBigSizeFive() != null){
                    //计算拼版尺寸面积(平方毫米)
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
                            //PrintMoney = PrintMoney.add(PercentageMoney);
                            BigSizeMoney = PercentageMoney;
                        }
                    }
                }
                //开始验证是否开启了大色块，如果开启大色块那么开始计算大色块加专色的价格
                BigDecimal BigColorMoney = new BigDecimal("0");
                if(printingCostModel.getIsBigColor() != null && printingCostModel.getIsBigColor().equals(new Byte("1")) && printingCostModel.getBigColorOne() != null && printingCostModel.getBigColorTwo() != null && printingCostModel.getBigColorThree() != null && printingCostModel.getBigColorFour() != null && printingCostModel.getBigColorFive() != null && joinSelectBo.getColorData() != null) {
                    List<SpotColorBo> spotColorBosFirst = Collections.synchronizedList(joinSelectBo.getColorData().getFirstSpotColorList()); //第一个专色集
                    List<SpotColorBo> spotColorBosSecond = Collections.synchronizedList(joinSelectBo.getColorData().getSecondSpotColorList()); //第二个专色集
                    //第一个专色列表
                    if(spotColorBosFirst != null && spotColorBosFirst.size() > 0){
                        for(int k = 0; k < spotColorBosFirst.size(); k++){
                            SpotColorBo spotColorBoFirst = spotColorBosFirst.get(k); //第一个专色列表中的单个专色数据对象
                            if(PinSizeArr != null || PinSizeArr.length == 2 && spotColorBoFirst.getIsOnBlock() != null && spotColorBoFirst.getIsOnBlock().equals(new Byte("1"))){
                                Integer PinArea = (Integer) (PinSizeArr[0].intValue() * PinSizeArr[1].intValue()); //拼版尺寸面积
                                BigDecimal DbColor = printingCostModel.getBigColorOne().multiply(printingCostModel.getBigColorTwo()); //大色块配置中的起始面积
                                BigDecimal DbColorTwo = printingCostModel.getBigColorThree().multiply(printingCostModel.getBigColorFour()); //大色块设置中的 第二个面积
                                //如果拼版尺寸面积大于大色块起始面积
                                if(new BigDecimal(PinArea.toString()).compareTo(DbColor) == 1){
                                    BigDecimal DbResColor = DbColorTwo.subtract(DbColor); //大色块的面积差
                                    BigDecimal UserResColor = new BigDecimal(PinArea.toString()).subtract(DbColor); //拼版与大色块起始面积差
                                    BigDecimal UserPercentage = UserResColor.divide(DbResColor,2,BigDecimal.ROUND_UP); //保留两位小数(百分比)
                                    BigDecimal DbPercentage = printingCostModel.getBigSizeFive().divide(new BigDecimal("100"),2,BigDecimal.ROUND_UP); //大色块中的百分比
                                    BigDecimal LastPercentage = UserPercentage.multiply(DbPercentage); //最终的百分比
                                    BigDecimal PercentageMoney = PrintMoney.multiply(LastPercentage); //大尺寸的百分比价格结果
                                    BigColorMoney = BigColorMoney.add(PercentageMoney);
                                }
                            }
                        }
                    }
                    //第二个专色列表
                    if(spotColorBosSecond != null && spotColorBosSecond.size() > 0){
                        for(int g = 0; g < spotColorBosSecond.size(); g++){
                            SpotColorBo spotColorBoSecond = spotColorBosSecond.get(g); //第二个专色列表中的单个专色数据对象
                            if(PinSizeArr != null || PinSizeArr.length == 2 && spotColorBoSecond.getIsOnBlock() != null && spotColorBoSecond.getIsOnBlock().equals(new Byte("1"))){
                                Integer PinArea = (Integer) (PinSizeArr[0].intValue() * PinSizeArr[1].intValue()); //拼版尺寸面积
                                BigDecimal DbColor = printingCostModel.getBigColorOne().multiply(printingCostModel.getBigColorTwo()); //大色块配置中的起始面积
                                BigDecimal DbColorTwo = printingCostModel.getBigColorThree().multiply(printingCostModel.getBigColorFour()); //大色块设置中的 第二个面积
                                //如果拼版尺寸面积大于大色块起始面积
                                if(new BigDecimal(PinArea.toString()).compareTo(DbColor) == 1){
                                    BigDecimal DbResColor = DbColorTwo.subtract(DbColor); //大色块的面积差
                                    BigDecimal UserResColor = new BigDecimal(PinArea.toString()).subtract(DbColor); //拼版与大色块起始面积差
                                    BigDecimal UserPercentage = UserResColor.divide(DbResColor,2,BigDecimal.ROUND_UP); //保留两位小数(百分比)
                                    BigDecimal DbPercentage = printingCostModel.getBigSizeFive().divide(new BigDecimal("100"),2,BigDecimal.ROUND_UP); //大色块中的百分比
                                    BigDecimal LastPercentage = UserPercentage.multiply(DbPercentage); //最终的百分比
                                    BigDecimal PercentageMoney = PrintMoney.multiply(LastPercentage); //大尺寸的百分比价格结果
                                    BigColorMoney = BigColorMoney.add(PercentageMoney);
                                }
                            }
                        }
                    }
                }
                System.out.println("啦啦啦啦1 "+PrintMoney.toString());
                System.out.println("啦啦啦啦2 "+ColorMoney.toString());
                System.out.println("啦啦啦啦3 "+BigSizeMoney.toString());
                System.out.println("啦啦啦啦3 "+BigColorMoney.toString());
                moneyList.add(PrintMoney.add(ColorMoney.add(BigSizeMoney.add(BigColorMoney))));
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
        LastBestTaskPaperSizeModel.setBestPrintWastageNums(LastBestPrintData.getPrintWastageNums());
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
            Integer YanPinMaxNumber = 0;
            ValuesPrintSizeModel valuesPrintSizeModel = calculateLogicService.getJuanTongLengthAndPinNum(LastBestTaskPaperSizeModel.getWidth(),null,LastBestTaskPaperSizeModel.getLastBestLength(), LastBestTaskPaperSizeModel.getLastBestWidth());
            if(valuesPrintSizeModel != null){
                YanPinMaxNumber = valuesPrintSizeModel.getNum();
                Integer[] JuanTongSizeArr = calculateLogicService.getPinSize(valuesPrintSizeModel.getSize());
                if(LastBestTaskPaperSizeModel.getWidth().compareTo(new BigDecimal(JuanTongSizeArr[0].toString())) != 0){
                    LastBestTaskPaperSizeModel.setLength(new BigDecimal(JuanTongSizeArr[0].toString()));
                }
                if(LastBestTaskPaperSizeModel.getWidth().compareTo(new BigDecimal(JuanTongSizeArr[1].toString())) != 0){
                    LastBestTaskPaperSizeModel.setLength(new BigDecimal(JuanTongSizeArr[1].toString()));
                }
            }
            if(YanPinMaxNumber > 0){
                LastBestTaskPaperSizeModel.setBestYuanCaiLiaoNums(YanPinMaxNumber);
            }
        }
        Integer Ynums = calculateLogicService.getYuanCaiLiaoNumber(LastBestTaskPaperSizeModel.getLength(),LastBestTaskPaperSizeModel.getWidth(),LastBestTaskPaperSizeModel.getLastBestLength(),LastBestTaskPaperSizeModel.getLastBestWidth());
        LastBestTaskPaperSizeModel.setBestYuanCaiLiaoNums(Ynums);
        return new AsyncResult<TaskPaperSizeModel>(LastBestTaskPaperSizeModel);
    }


    /**
     * 计算专色的价格
     * @param Types 第几个面 1 第一个面 2 第二个面
     * @param printingCostModel 印刷机数据对象
     * @param colorBo 颜色请对象参数
     * @param printNumber 印刷原材料数量
     * @return
     */
    private BigDecimal spotColorMoney(int Types,PrintingCostModel printingCostModel,ColorBo colorBo,BigDecimal printNumber){
        List<SpotColorBo> spotColorBos = null;
        if(Types == 1){
            spotColorBos = Collections.synchronizedList(colorBo.getFirstSpotColorList()); //获取专色集合对象
        }else{
            spotColorBos = Collections.synchronizedList(colorBo.getSecondSpotColorList()); //获取专色集合对象
        }
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
                //开始计算专色的价格
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

    /**
     * 计算颜色的费用
     * @param Types 面的类型 1 第一个面 2 第二个面
     * @param PrintNums 印刷的材料数量
     * @param PrintMoney 印刷的价格
     * @param colorBo 颜色请求对象
     * @param printingCostModel 印刷机对象数据
     * @return
     */
    private ColorMoneyModel colorFunc(int Types,BigDecimal PrintNums,BigDecimal PrintMoney,ColorBo colorBo,PrintingCostModel printingCostModel){
        BigDecimal ColorMoney = new BigDecimal("0"); //颜色的价格
        BigDecimal SpotColorMoney = new BigDecimal("0"); //专色的价格
        int IsOut = 0; //是否淘汰
        Integer ColorType = null;
        if(Types == 1){
            if(colorBo.getFirstColorType() == null){
                colorBo.setFirstColorType(3);
            }
            ColorType = colorBo.getFirstColorType();
        }else{
            if(colorBo.getSecondColorType() == null){
                colorBo.setSecondColorType(3);
            }
            ColorType = colorBo.getSecondColorType();
        }
        //开始根据印刷机颜色与用户选择颜色计算
        switch (ColorType){
            case 1:
                //普通单色

                break;
            case 2:
                //普通双色
                if(printingCostModel.getRobotColor() != null && printingCostModel.getRobotColor().getColorNumber() != null && printingCostModel.getRobotColor().getColorNumber() < 2){
                    ColorMoney = PrintMoney;
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
                        ColorMoney = TmpsPrintMoney;
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
                    ColorMoney = TmpsPrintMoney;
                    //颜色专色计算方式
                    BigDecimal SpotMoney = this.spotColorMoney(Types,printingCostModel,colorBo,PrintNums);
                    if(SpotMoney == null){
                        IsOut = 1;
                    }else {
                        //ColorMoney = ColorMoney.add(SpotMoney);
                        SpotColorMoney = SpotMoney;
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
                    BigDecimal SpotMoney = this.spotColorMoney(Types,printingCostModel,colorBo,PrintNums);
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
                            SpotColorMoney = SpotMoney;
                        }
                    }
                }
                break;

        }
        ColorMoneyModel colorMoneyModel = new ColorMoneyModel();
        colorMoneyModel.setColorMoney(ColorMoney);
        colorMoneyModel.setSpotColorMoney(SpotColorMoney);
        colorMoneyModel.setIsOut(IsOut);
        return colorMoneyModel;
    }

    /**
     * 此方法主要为颜色比对
     * 颜色比对，如果为双面的时候，验证两面的颜色是否可以做到包含
     * 如果不存在包含，则按照两个面各自计算然后将总价格加到一起
     * @param colorBo 颜色请求对象
     * @return boolean 是否包含 true 是 false 否
     */
    private SpotColorTmpModel checkColor(ColorBo colorBo){

        List<SpotColorBo> spotColorBoTmp = null;

        SpotColorTmpModel spotColorTmpModel = new SpotColorTmpModel();

        //如果只选择了一个颜色类型，那么认为双面一样
        if(colorBo.getSecondColorType() == null){
            spotColorTmpModel.setStatus(1);
            return spotColorTmpModel;
        }
        //如果两面的颜色类型一致，那么双面一样
        if(colorBo.getSecondColorType() != 4 && colorBo.getSecondColorType() != 5 && colorBo.getFirstColorType() == colorBo.getSecondColorType()){
            spotColorTmpModel.setStatus(1);
            return spotColorTmpModel;
        }
        //双面不一样
        if((colorBo.getFirstColorType() == 5 || colorBo.getSecondColorType() == 5) && (colorBo.getFirstColorType() < 4 || colorBo.getSecondColorType() < 4) && colorBo.getFirstColorType() != colorBo.getSecondColorType()){
            spotColorTmpModel.setStatus(0);
            return spotColorTmpModel;
        }
        if(colorBo.getFirstColorType() != 4 && colorBo.getFirstColorType() != 5 && colorBo.getSecondColorType() != 4 && colorBo.getSecondColorType() != 5){
            spotColorTmpModel.setStatus(1);
            return spotColorTmpModel;
        }
        //验证专色是否一致
        if((colorBo.getFirstColorType() == 4 || colorBo.getFirstColorType() == 5) && (colorBo.getSecondColorType() == 4 || colorBo.getSecondColorType() == 5)){
           List<SpotColorBo> spotColorBosOne = Collections.synchronizedList(colorBo.getFirstSpotColorList()); //获取专色集合对象
           List<SpotColorBo> spotColorBosTwo = Collections.synchronizedList(colorBo.getSecondSpotColorList()); //获取专色集合对象
            if(spotColorBosOne != null && spotColorBosTwo != null && spotColorBosOne.size() > 0 && spotColorBosTwo.size() > 0){
                int minSize = 0;
                int Sider = 0;
                List<SpotColorBo> MaxList = null;
                if(spotColorBosOne.size() > spotColorBosTwo.size()){
                    minSize = spotColorBosTwo.size();
                    Sider = 2;
                    MaxList = Collections.synchronizedList(spotColorBosOne);
                }else{
                    minSize = spotColorBosOne.size();
                    Sider = 1;
                    MaxList = Collections.synchronizedList(spotColorBosTwo);
                }
                List<SpotColorBo> list = Collections.synchronizedList(new ArrayList<SpotColorBo>());
                for(int a = 0; a < spotColorBosOne.size(); a++){
                    for (int b = 0; b < spotColorBosTwo.size(); b++){
                        if(spotColorBosOne.get(a).getSpotColorId().equals(spotColorBosTwo.get(b).getSpotColorId())){
                            list.add(spotColorBosOne.get(a));
                        }
                    }
                }
                //找出多处来的专色
                for (int c = 0; c < MaxList.size(); c++){
                    int s = 0;
                    for(int d = 0; d < list.size(); d++){
                        if(MaxList.get(c).getSpotColorId().equals(list.get(d).getSpotColorId())){
                            s = 1;
                        }
                    }
                    if(s == 0){
                        spotColorBoTmp.add(MaxList.get(c));
                    }
                }
                if(spotColorBoTmp != null && spotColorBoTmp.size() > 0){
                    spotColorTmpModel.setSpotColorBo(spotColorBoTmp);
                }
                if(list.size() >= minSize){
                    spotColorTmpModel.setStatus(Sider);
                    return spotColorTmpModel;
                }
            }
        }
        spotColorTmpModel.setStatus(0);
        return spotColorTmpModel;
    }

    /**
     * 验证是否对称
     * @return true 是 false 否
     */
    private boolean checkSymmetric(BigDecimal clength,BigDecimal cwidth,Integer length,Integer width){
        Integer Sclength = 0;
        Integer Scwidth = 0;
        try {
            Sclength = (Integer) clength.intValue();
            Scwidth = (Integer) cwidth.intValue();
        }catch (Exception e){
            loger.error("计算对称时的原材料类型转换："+e.toString());
            e.printStackTrace();
        }
        PapercutNumber papercutNumber = new PapercutNumber();
        papercutNumber.setClength(Sclength);
        papercutNumber.setCwidth(Scwidth);
        papercutNumber.setLength(length);
        papercutNumber.setWidth(width);
        Integer Types = algorithmLogicService.getCalcSymmetryLogic(papercutNumber);
        if(Types.intValue() > 0){
            return true;
        }else {
            return false;
        }
    }


}
