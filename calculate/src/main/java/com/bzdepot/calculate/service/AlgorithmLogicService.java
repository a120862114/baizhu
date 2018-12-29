package com.bzdepot.calculate.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.bzdepot.calculate.bo.MinLengthBo;
import com.bzdepot.calculate.bo.PapercutNumber;
import com.bzdepot.calculate.bo.PrintSizeBo;
import com.bzdepot.calculate.feign.AlgorithmService;
import com.bzdepot.calculate.model.PrintSizeModel;
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
}
