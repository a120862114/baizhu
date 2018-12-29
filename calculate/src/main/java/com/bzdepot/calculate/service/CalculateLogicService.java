package com.bzdepot.calculate.service;

import com.bzdepot.calculate.bo.MinLengthBo;
import com.bzdepot.calculate.bo.PapercutNumber;
import com.bzdepot.calculate.model.BestPrintSizeModel;
import com.bzdepot.calculate.model.PrintSizeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 逻辑服务
 */
@Service
public class CalculateLogicService {

    private final static Logger loger = LoggerFactory.getLogger(CalculateLogicService.class);

    @Autowired
    private AlgorithmLogicService algorithmLogicService;

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
