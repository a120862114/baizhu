package com.bzdepot.calculate.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.bzdepot.calculate.bo.MinLengthBo;
import com.bzdepot.calculate.bo.PapercutNumber;
import com.bzdepot.calculate.bo.PrintSizeBo;
import com.bzdepot.calculate.bo.ValuesPrintSizeBo;
import com.bzdepot.calculate.feign.AlgorithmService;
import com.bzdepot.calculate.model.PrintSizeModel;
import com.bzdepot.calculate.model.ValuesPrintSizeModel;
import com.bzdepot.common.message.JsonReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AlgorithmLogicService {

    private final static Logger loger = LoggerFactory.getLogger(AlgorithmLogicService.class);

    @Autowired
    private AlgorithmService algorithmService;

    /**
     * 获取拼版数量的方法
     * @param papercutNumber
     * @return
     */
    public Integer getPapercutNumberLogic(PapercutNumber papercutNumber){
        Integer Number = 0;
        Object datas = algorithmService.getPapercutNumber(papercutNumber);
        System.out.println("获取拼版数量的方法---请求 参数："+papercutNumber.toString());
        System.out.println("获取拼版数量的方法："+datas.toString());
        Map<String,Object> ResultDatas;
        try {
            ResultDatas = JsonReturn.Parse(datas);
        }catch (Exception e){
            loger.error(e.toString());
            ResultDatas = null;
            e.printStackTrace();
        }
        if(ResultDatas == null || ResultDatas.size() != 2){
           return -1;
        }
        String Code = "1";
        try {
            Code = (String) ResultDatas.get("code");
        }catch (Exception e){
            loger.error(e.toString());
            Code = "1";
            e.printStackTrace();
        }
        if(Code.equals("0") == false){
            return -1;
        }
        Map<String,Object> NumsMap;
        try {
            NumsMap = JsonReturn.Parse(ResultDatas.get("data"));
        }catch (Exception e){
            loger.error(e.toString());
            NumsMap = null;
            e.printStackTrace();
        }
        if(NumsMap == null){
            return -1;
        }
        try {
            Number = (Integer) NumsMap.get("maxnum");
        }catch (Exception e){
            loger.error(e.toString());
            Number = -1;
            e.printStackTrace();
        }
        return Number;
    }

    /**
     * 获取指定最小尺寸与最大尺寸间所有自定义尺寸的拼版尺寸
     * @param printSizeBo
     * @return
     */
    public List<PrintSizeModel> getPrintSizeLogic(PrintSizeBo printSizeBo){
        Object Datas = algorithmService.getPrintSize(printSizeBo);
        System.out.println("获取指定最小尺寸与最大尺寸间所有自定义尺寸的拼版尺寸----请求参数："+printSizeBo.toString());
        System.out.println("获取指定最小尺寸与最大尺寸间所有自定义尺寸的拼版尺寸："+Datas.toString());

        Map<String,Object> ResultDatas;
        try {
            ResultDatas = JsonReturn.Parse(Datas);
        }catch (Exception e){
            ResultDatas = null;
            loger.error(e.toString());
            e.printStackTrace();
        }
        if(ResultDatas == null || ResultDatas.size() != 2){
            return null;
        }
        String Code = "1";
        try {
            Code = (String) ResultDatas.get("code");
        }catch (Exception e){
            Code = "1";
            loger.error(e.toString());
            e.printStackTrace();
        }
        if(Code.equals("0") == false){
            return null;
        }
        List<PrintSizeModel> printSizeModels;
        try {
            JSONArray DataRes = (JSONArray) ResultDatas.get("data");
            printSizeModels = JSON.parseObject(DataRes.toString(),new TypeReference<List<PrintSizeModel> >(){});
        }catch (Exception e){
            printSizeModels = null;
            loger.error(e.toString());
            e.printStackTrace();
        }
        return printSizeModels;
    }

    /**
     * 无限长纸张计算最小长度
     * @param minLengthBo
     * @return
     */
    public Integer getMinLengthLogic(MinLengthBo minLengthBo){
        minLengthBo.setLimited(true);
        Object DataResult = algorithmService.getMinLength(minLengthBo);
        System.out.println("无限长纸张计算最小长度请求参数 ：" + minLengthBo.toString());
        System.out.println("无限长纸张计算最小长度："+DataResult.toString());
        Map<String,Object> ResultDatas;
        try {
            ResultDatas = JsonReturn.Parse(DataResult);
        }catch (Exception e){
            ResultDatas = null;
            e.printStackTrace();
            loger.error(e.toString());
        }
        if(ResultDatas == null || ResultDatas.size() != 2){
            return 0;
        }
        String Code = "1";
        try {
            Code = (String) ResultDatas.get("code");
        }catch (Exception e){
            Code = "1";
            e.printStackTrace();
            loger.error(e.toString());
        }
        if(Code.equals("0") == false){
            return 0;
        }
        Map<String,Object> MinLengthMap;
        Integer minLength = 0;
        try {
            MinLengthMap = JsonReturn.Parse(ResultDatas.get("data"));
        }catch (Exception e){
            e.printStackTrace();
            loger.error("无限长纸张计算最小长度--异常："+e.toString());
            MinLengthMap = null;
        }
        if(MinLengthMap == null){
            return 0;
        }
        try {
            minLength = (Integer) MinLengthMap.get("minlength");
        }catch (Exception e){
            e.printStackTrace();
            loger.error("无限长纸张计算最小长度--类型转换--异常："+e.toString());
        }
        return minLength;
    }

    /**
     * 计算性价比最高尺寸
     * @param valuesPrintSizeBo
     * @return
     */
    public ValuesPrintSizeModel getValuesPrintSizeLogic(ValuesPrintSizeBo valuesPrintSizeBo){
        Object Datas = algorithmService.getValuesPrintSize(valuesPrintSizeBo);
        System.out.println("计算性价比最高尺寸:"+Datas.toString());
        System.out.println("计算性价比最高尺寸参数:"+valuesPrintSizeBo.toString());
        String size = null;
        Integer num = null;
        try {
            Map<String,Object> DatasJson = JsonReturn.Parse(Datas);
            Map<String,Object> dataRes = JsonReturn.Parse(DatasJson.get("data"));
            num = (Integer) dataRes.get("num");
            size = (String) dataRes.get("size");
        }catch (Exception e){
            e.printStackTrace();
            loger.error(e.toString());
        }

        if(size == null || num == null){
            return null;
        }
        ValuesPrintSizeModel valuesPrintSizeModel = new ValuesPrintSizeModel();
        valuesPrintSizeModel.setNum(num);
        valuesPrintSizeModel.setSize(size);
        return valuesPrintSizeModel;
    }

    /**
     * 计算是否对称
     * @param papercutNumber
     * @return
     */
    public Integer getCalcSymmetryLogic(PapercutNumber papercutNumber){
        Object DataResultObject = algorithmService.getCalcSymmetry(papercutNumber);
        loger.debug("是否对称请求参数:"+papercutNumber.toString());
        loger.debug("是否对称结果:"+DataResultObject.toString());
        Map<String,Object> DataResultMap = JsonReturn.Parse(DataResultObject);
        Integer Code = 1;
        Map<String,Object> ReturnDataMap = null;
        try {
            Code = (Integer) DataResultMap.get("code");
            ReturnDataMap = JsonReturn.Parse(DataResultMap.get("data"));
        }catch (Exception e){
            e.printStackTrace();
            loger.error("计算对称接口调用失败:"+e.toString());
        }
        if(Code > 0){
            return -1;
        }
        Integer Types = -1;
        try {
            Types = (Integer) DataResultMap.get("type");
        }catch (Exception e){
            e.printStackTrace();
            loger.error("获取对称结果类型解析失败："+e.toString());
        }
        return Types;
    }
}
