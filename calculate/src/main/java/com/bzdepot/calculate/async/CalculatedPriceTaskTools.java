package com.bzdepot.calculate.async;

import com.bzdepot.calculate.bo.JoinSelectBo;
import com.bzdepot.calculate.bo.MinLengthBo;
import com.bzdepot.calculate.bo.PapercutNumber;
import com.bzdepot.calculate.model.BestPrintSizeModel;
import com.bzdepot.calculate.model.PrintSizeModel;
import com.bzdepot.calculate.model.TaskPaperSizeModel;
import com.bzdepot.calculate.service.AlgorithmLogicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 计算价格
 */
@Component
public class CalculatedPriceTaskTools {

    private final static Logger loger = LoggerFactory.getLogger(CalculatedPriceTaskTools.class);

   /* @Autowired
    private CalculateLogicService calculateLogicService;*/

    @Autowired
    private AlgorithmLogicService algorithmLogicService;

    /**
     *  计算出每个拼版的价格，并筛选出最便宜的价格
     * @param taskPaperSizeModel 原料纸张的尺寸对象
     * @param printSizeModels 拼版结果的列表数据
     * @param joinSelectBo 用户传递的请求参数
     * @param unitPriceType 价格单位 0 吨 1 张
     * @param unitPrice 单价
     * @return
     */
    @Async("calculateExecutor")
    public Future<TaskPaperSizeModel> getBestPinPrice(TaskPaperSizeModel taskPaperSizeModel, List<PrintSizeModel> printSizeModels, JoinSelectBo joinSelectBo, Byte unitPriceType, BigDecimal unitPrice,BigDecimal minBiteNums,Integer UserHemorrhage){

        Integer UsergetGaugeNums = 0;
        if(joinSelectBo.getGaugeNums() != null){
            try {
                UsergetGaugeNums = (Integer) joinSelectBo.getGaugeNums().intValue();
            }catch (NumberFormatException e){
                e.printStackTrace();
                loger.error("转换规线类型失败"+e.toString());
            }
        }
        Integer ServerGetMinBite = 0; //从配置中获得的最小咬口
        if(minBiteNums != null){
            try {
                ServerGetMinBite = (Integer) minBiteNums.intValue();
            }catch (NumberFormatException e){
                e.printStackTrace();
                loger.error("转换配置中的最小咬口类型失败："+e.toString());
            }
        }
        Integer UserGetMinBite = 0; //用户设置的可借咬口
        if(joinSelectBo.getBiteNums() != null){
            try {
                UserGetMinBite = (Integer) joinSelectBo.getBiteNums().intValue();
            }catch (NumberFormatException e){
                e.printStackTrace();
                loger.error("解析用户可借咬口类型:"+e.toString());
            }
        }
        for(int i = 0; i < printSizeModels.size(); i++){
            PrintSizeModel printSizeModel = printSizeModels.get(i);
           /* System.out.println("输出拼版的尺寸数据");
            System.out.println(printSizeModel.toString());*/
            //如果尺寸数据为空，则跳出此次运算
            if(printSizeModel.getList() == null || printSizeModel.getList().size() == 0){
                continue;
            }
            List<BigDecimal> MoneyList = Collections.synchronizedList(new ArrayList<BigDecimal>()); //价格列表
            List<String> SizeList = Collections.synchronizedList(printSizeModel.getList()); //获取拼版尺寸列表数据
            List<Integer> yuanCaiLiaoNumsList = Collections.synchronizedList(new ArrayList<>()); //原材料数量
            List<String> lastList = Collections.synchronizedList(new ArrayList<>()); //计算后的尺寸
            for(int a = 0; a < SizeList.size(); a++){
                String pinSize = SizeList.get(a);
                Integer[] pinSizeArr = this.getPinSize(pinSize); //解析拼版的尺寸单位
                //如转换失败则跳出本次循环
                if(pinSizeArr == null){
                    loger.error("将拼版尺寸字符串解析为整型失败!");
                    continue;
                }
                //加上规线的尺寸
                if(UsergetGaugeNums != 0){
                    pinSizeArr[0] = pinSizeArr[0] + UsergetGaugeNums*2;
                    pinSizeArr[1] = pinSizeArr[1] + UsergetGaugeNums*2;
                }
                if(joinSelectBo.getBiteNums() != null){
                    //咬口计算
                    if(ServerGetMinBite - UserGetMinBite > 0){
                        ServerGetMinBite = ServerGetMinBite - UserGetMinBite;
                    }else{
                        ServerGetMinBite = 0;
                    }
                    System.out.println("咬口前================================");
                    System.out.println(pinSizeArr[1]);
                    System.out.println("咬口前================================");
                    pinSizeArr[1] = pinSizeArr[1] - UserHemorrhage - UsergetGaugeNums - ServerGetMinBite; //计算咬口公式
                    System.out.println("咬口后================================");
                    System.out.println(pinSizeArr[1]);
                    System.out.println("咬口后================================");
                }else{
                    pinSizeArr[1] = pinSizeArr[1] + ServerGetMinBite;
                }

                //计算原材料尺寸与拼版之间能拼的最大数量
                if(taskPaperSizeModel.getSizeType().equals(new Byte("2"))){
                    taskPaperSizeModel.setLength(new BigDecimal("1250"));
                }
                Integer YanPinMaxNumber = this.getYuanCaiLiaoNumber(taskPaperSizeModel.getLength(),taskPaperSizeModel.getWidth(),pinSizeArr[0],pinSizeArr[1]);
                if(YanPinMaxNumber == 0){
                    loger.error("获取原材料尺寸与拼版尺寸之间能拼的最大数量失败或只能拼0个!");
                    continue;
                }
                yuanCaiLiaoNumsList.add(YanPinMaxNumber);//添加到原材料列表

                BigDecimal LastUnitPrice = null; //初始化一个最终单价变量
                //开始获取单价
                if(unitPriceType.equals(new Byte("1"))){
                    LastUnitPrice = unitPrice; //直接等于数据库中的单位,在单位为张的情况下
                }else{
                    //验证尺寸是否是卷筒，如果是卷筒纸那么开始计算卷筒纸的长度
                    if(taskPaperSizeModel.getSizeType().equals(new Byte("2"))){
                        BigDecimal length = this.getJuanTongLength(taskPaperSizeModel.getWidth(),pinSizeArr[0],pinSizeArr[1],YanPinMaxNumber);
                        if(length != null){
                            taskPaperSizeModel.setLength(length);
                        }else {
                            loger.error("获取卷筒纸长度失败!");
                            continue; //跳出此次循环
                        }
                    }
                    //开始将吨单价转换成张单机
                    LastUnitPrice = this.getUnitPrice(taskPaperSizeModel.getLength(),taskPaperSizeModel.getWidth(),joinSelectBo.getGramNums(),unitPrice);
                    if(LastUnitPrice == null){
                        loger.error("将吨价转换为张价计算失败!");
                        continue;
                    }
                }

                //计算原材料数量
                BigDecimal YuanCaiLiaoNumber = new BigDecimal(joinSelectBo.getMakeNumber().toString()).divide(new BigDecimal(YanPinMaxNumber.toString()),2).divide(new BigDecimal(printSizeModel.getNum().toString()),2);
                BigDecimal LastMoney = LastUnitPrice.multiply(YuanCaiLiaoNumber); //最终价格
                MoneyList.add(LastMoney);
                //System.out.println("原材料数量："+YuanCaiLiaoNumber.toString()+" 等于 用户所属数量："+joinSelectBo.getMakeNumber().toString()+" 除以 原尺寸与拼版尺寸的最大拼值："+YanPinMaxNumber.toString()+" 除以 拼版尺寸的最大拼版数量："+printSizeModel.getNum().toString());
                //System.out.println("最终价："+LastMoney.toString()+" 等于 张的单价："+LastUnitPrice.toString()+" 乘以 原材料数量："+YuanCaiLiaoNumber.toString());
                lastList.add(pinSizeArr[0].toString()+"*"+pinSizeArr[1].toString());
            }
            printSizeModels.get(i).setMoney(MoneyList);
            printSizeModels.get(i).setYunCaiLiaoNums(yuanCaiLiaoNumsList);
            printSizeModels.get(i).setLastList(lastList);
            //printSizeModel.setMoney(MoneyList);

            System.out.println("查看最终数据接口开始");
            System.out.println(printSizeModel.toString());
            System.out.println("查看最终数据接口结束");
        }
        //获取每种拼版数量里面最便宜价格的尺寸数据
       List<PrintSizeModel> bestSizeResult = Collections.synchronizedList(new ArrayList<PrintSizeModel>());
       for(int s = 0; s < printSizeModels.size(); s++){
          PrintSizeModel TmpSizeData = this.getBestSizeData(printSizeModels.get(s));
          if(TmpSizeData != null){
              bestSizeResult.add(TmpSizeData);
          }
       }
       System.out.println("第一步筛选最好的尺寸"+bestSizeResult.toString()+taskPaperSizeModel.toString());
       taskPaperSizeModel.setSizeList(bestSizeResult);
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        System.out.println(taskPaperSizeModel.toString());
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
       return new AsyncResult<TaskPaperSizeModel>(taskPaperSizeModel);
    }
    /**
     * 计算原纸张配置的纸张尺寸与拼版计算出的尺寸之间最多能拼的数量
     * @param length 原材料纸的长
     * @param width  原材料值的宽
     * @param pinlength 拼版的长
     * @param pinWidth 拼版的宽
     * @return  返回最多能拼的数量
     */
    public Integer getYuanCaiLiaoNumber(BigDecimal length,BigDecimal width,Integer pinlength,Integer pinWidth){
        Integer YuanLength = 0;
        Integer YuanWidth = 0;
        try {
            YuanLength = (Integer) length.intValue();
            YuanWidth = (Integer) width.intValue();
        }catch (Exception e){
            e.printStackTrace();
            loger.error(e.toString());
        }
        if(YuanLength == 0 || YuanWidth == 0){
            return 0;
        }
        PapercutNumber papercutNumber = new PapercutNumber();
        papercutNumber.setClength(YuanLength);
        papercutNumber.setCwidth(YuanWidth);
        papercutNumber.setLength(pinlength);
        papercutNumber.setWidth(pinWidth);
        Integer MaxPapercutNumber = algorithmLogicService.getPapercutNumberLogic(papercutNumber);
        System.out.println("计算原材料拼版数量："+papercutNumber.toString());
        System.out.println("计算原材料拼版数量结果："+MaxPapercutNumber.toString());
        if(MaxPapercutNumber > 0){
            return MaxPapercutNumber;
        }
        return 0;
    }

    /**
     * 计算原材料单价（换算成每张多少钱）
     * @param length  长
     * @param width   宽
     * @param gramNum  克重
     * @param DunPrice  吨价
     * @return  单价
     */
    public BigDecimal getUnitPrice(BigDecimal length,BigDecimal width,BigDecimal gramNum,BigDecimal DunPrice){
        if(width == null || gramNum == null || DunPrice == null){
            return null;
        }
        //计算长乘高的结果
        BigDecimal Mianji = length.multiply(width);
        //面积乘吨价
        BigDecimal MianjiPrice = Mianji.multiply(DunPrice);
        //总吨价乘克重
        BigDecimal ZongGram = MianjiPrice.multiply(gramNum);
        //除12个零
        BigDecimal Result = ZongGram.divide(new BigDecimal("1000000000000"));
        return Result;
    }

    /**
     * 获取卷筒纸的长度
     * @param width 卷筒宽度
     * @param PinNums 拼版数量
     * @return 长度
     */
    public BigDecimal getJuanTongLength(BigDecimal width,Integer pinlength,Integer pinWidth,Integer PinNums){
        if(width == null || pinlength == null || pinWidth == null || PinNums == null || PinNums == 0 || pinlength == 0 || pinWidth == 0){
            return null;
        }
        Integer JuanWidth = 0;
        try {
            JuanWidth = (Integer) width.intValue();
        }catch (Exception e){
            e.printStackTrace();
            loger.error(e.toString());
        }
        if(JuanWidth == 0){
            return null;
        }
        MinLengthBo minLengthBo = new MinLengthBo();
        minLengthBo.setCwidth(JuanWidth);
        minLengthBo.setLength(pinlength);
        minLengthBo.setWidth(pinWidth);
        minLengthBo.setNum(PinNums);
        Integer MinLengthNumber = algorithmLogicService.getMinLengthLogic(minLengthBo);
        if(MinLengthNumber != 0){
            return  new BigDecimal(MinLengthNumber.toString());
        }
        return null;
    }
    /**
     * 解析拼版的尺寸并转换为Intger数组
     * @param sizeStr
     * @return
     */
    public Integer[] getPinSize(String sizeStr){
        if(sizeStr == null){
            return null;
        }
        Integer[] SizeNumberArr = new Integer[2];
        String[] PinPaperSizeArr = sizeStr.split("\\*");
        if(PinPaperSizeArr.length < 2){
            return null;
        }
        try {
            SizeNumberArr[0] = Integer.valueOf(PinPaperSizeArr[0]);
            SizeNumberArr[1] = Integer.valueOf(PinPaperSizeArr[1]);
        }catch (NumberFormatException e){
            e.printStackTrace();
            loger.error(e.toString());
            SizeNumberArr = null;
        }
        return SizeNumberArr;
    }

    /**
     * 获取最好的尺寸数据
     * @param printSizeModel
     * @return
     */
    public PrintSizeModel getBestSizeData(PrintSizeModel printSizeModel){
        if(printSizeModel.getList() == null || printSizeModel.getList().size() == 0){
            return null;
        }
        if(printSizeModel.getMoney() == null || printSizeModel.getMoney().size() == 0){
            return null;
        }
        List<BigDecimal> list = Collections.synchronizedList(printSizeModel.getMoney());
        // 接近的数字
        BigDecimal nearNum = new BigDecimal("0");
        // 差值实始化
        BigDecimal tmpNums = list.get(0).subtract(nearNum);
        BigDecimal diffNum = tmpNums.abs();
        // 最终结果
        int Index = 0;
        BigDecimal result = list.get(0);
        for(int i = 0; i < list.size(); i++){
            BigDecimal bigDecimalNums = list.get(i);
            BigDecimal forTmpNums = bigDecimalNums.subtract(nearNum);
            BigDecimal diffNumTemp = forTmpNums.abs();
            if(diffNumTemp.compareTo(diffNum) == -1)
            {
                diffNum = diffNumTemp;
                result = bigDecimalNums;
                Index = i;
            }
        }
        PrintSizeModel bestResultSiz = new PrintSizeModel();
        bestResultSiz.setNum(printSizeModel.getNum()); //设置拼版尺寸
        List<BigDecimal> moneyList = Collections.synchronizedList(new ArrayList<BigDecimal>());
        moneyList.add(printSizeModel.getMoney().get(Index));
        bestResultSiz.setMoney(moneyList);
        List<String> sizeList = Collections.synchronizedList(new ArrayList<String>());
        sizeList.add(printSizeModel.getList().get(Index));
        bestResultSiz.setList(sizeList);
        List<Integer> YuanCaiLiaoNumsList = Collections.synchronizedList(new ArrayList<>());
        YuanCaiLiaoNumsList.add(printSizeModel.getYunCaiLiaoNums().get(Index));
        bestResultSiz.setYunCaiLiaoNums(YuanCaiLiaoNumsList);
        List<String> lastList = Collections.synchronizedList(new ArrayList<>());
        lastList.add(printSizeModel.getLastList().get(Index));
        bestResultSiz.setLastList(lastList);
        System.out.println("筛选哈哈哈哈哈之前"+printSizeModel.toString());
        System.out.println("筛选哈哈哈哈哈"+bestResultSiz.toString());
        //将最便宜价格的尺寸价格返回
        return bestResultSiz;
    }

    /**
     * 获取所有原材料纸张每种拼版数量中最合适的拼版价格
     * @param bestPrintSizeModel
     * @return
     */
    public BestPrintSizeModel getBestPrintSize(BestPrintSizeModel bestPrintSizeModel){
        if(bestPrintSizeModel.getMoneyList() == null || bestPrintSizeModel.getMoneyList().size() == 0){
            return null;
        }
        if(bestPrintSizeModel.getSizeList() == null || bestPrintSizeModel.getSizeList().size() == 0){
            return null;
        }
        List<BigDecimal> list = Collections.synchronizedList(bestPrintSizeModel.getMoneyList());
        // 接近的数字
        BigDecimal nearNum = new BigDecimal("0");
        // 差值实始化
        BigDecimal tmpNums = list.get(0).subtract(nearNum);
        BigDecimal diffNum = tmpNums.abs();
        // 最终结果
        int Index = 0;
        BigDecimal result = list.get(0);
        //获取最好的价格
        for(int i = 0; i < list.size(); i++){
            BigDecimal bigDecimalNums = list.get(i);
            BigDecimal forTmpNums = bigDecimalNums.subtract(nearNum);
            BigDecimal diffNumTemp = forTmpNums.abs();
            if(diffNumTemp.compareTo(diffNum) == -1)
            {
                diffNum = diffNumTemp;
                result = bigDecimalNums;
                Index = i;
            }
        }
        //重组最好拼版数量中的拼版尺寸
        BestPrintSizeModel BestResult = new BestPrintSizeModel();
        BestResult.setNums(bestPrintSizeModel.getNums());
        List<String> sizeList = Collections.synchronizedList(new ArrayList<>());
        sizeList.add(bestPrintSizeModel.getSizeList().get(Index));
        BestResult.setSizeList(sizeList);
        List<BigDecimal> moneyList = Collections.synchronizedList(new ArrayList<>());
        moneyList.add(bestPrintSizeModel.getMoneyList().get(Index));
        BestResult.setMoneyList(moneyList);
        List<Byte> sizeTypeList = Collections.synchronizedList(new ArrayList<>());
        sizeTypeList.add(bestPrintSizeModel.getSizeTypeList().get(Index));
        BestResult.setSizeTypeList(sizeTypeList);
        List<Long> idList = Collections.synchronizedList(new ArrayList<>());
        idList.add(bestPrintSizeModel.getIdList().get(Index));
        BestResult.setIdList(idList);
        List<Integer> indexList = Collections.synchronizedList(new ArrayList<>());
        indexList.add(bestPrintSizeModel.getIndexList().get(Index));
        BestResult.setIndexList(indexList);
        List<BigDecimal> widthList = Collections.synchronizedList(new ArrayList<>());
        widthList.add(bestPrintSizeModel.getWidthList().get(Index));
        BestResult.setWidthList(widthList);
        List<BigDecimal> lengthList = Collections.synchronizedList(new ArrayList<>());
        lengthList.add(bestPrintSizeModel.getLengthList().get(Index));
        BestResult.setLengthList(lengthList);
        List<Integer> YuanCaiLiaoNumsList = Collections.synchronizedList(new ArrayList<>());
        YuanCaiLiaoNumsList.add(bestPrintSizeModel.getYuanCaiLiaoNumsList().get(Index));
        BestResult.setYuanCaiLiaoNumsList(YuanCaiLiaoNumsList);
        List<String> lastList = Collections.synchronizedList(new ArrayList<>());
        lastList.add(bestPrintSizeModel.getLastSizeList().get(Index));
        BestResult.setLastSizeList(lastList);

        return BestResult;
    }
}
