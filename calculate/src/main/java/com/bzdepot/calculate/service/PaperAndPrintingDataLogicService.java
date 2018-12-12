package com.bzdepot.calculate.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.bzdepot.calculate.bo.JoinSelectBo;
import com.bzdepot.calculate.feign.PaperAndPrintingDataService;
import com.bzdepot.calculate.model.PaperCostWithBLOBs;
import com.bzdepot.common.message.JsonReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaperAndPrintingDataLogicService {

    private final static Logger loger = LoggerFactory.getLogger(PaperAndPrintingDataLogicService.class);

    @Autowired
    private PaperAndPrintingDataService paperAndPrintingDataService;

    /**
     * 获取纸张配置与符合条件的印刷机数据
     * @param joinSelectBo
     * @return
     */
    public List<PaperCostWithBLOBs> getPaperAndPrintConfigData(JoinSelectBo joinSelectBo){
        //构造提交的表单数据
        Map<String,Object> PostData = new HashMap<String, Object>();
        PostData.put("sellerId",joinSelectBo.getSellerId());
        PostData.put("textureId",joinSelectBo.getTextureId());
        PostData.put("gramNums",joinSelectBo.getGramNums());
        PostData.put("printingColorId",joinSelectBo.getPrintingColorId());
        PostData.put("Longs",joinSelectBo.getLongs());
        PostData.put("Width",joinSelectBo.getWidth());
        //提交数据
        Object ResultData = paperAndPrintingDataService.getPaperConfigJoinPrintingData(PostData);
        Map<String,Object> ResultDatas;
        try {
            ResultDatas = JsonReturn.Parse(ResultData);
        }catch (Exception e){
            loger.error(e.toString());
            ResultDatas = null;
        }
        if(ResultDatas == null){
            return null;
        }
        Integer errorCode = 10011;
        try {
            errorCode = (Integer) ResultDatas.get("error_code");
        }catch (Exception e){
            errorCode = 10011;
            loger.error(e.toString());
        }
        if(errorCode != 0){
            return null;
        }
        List<PaperCostWithBLOBs> paperCostModels; //需要解析的结果集
        try {
            JSONArray PaperDatas = (JSONArray) ResultDatas.get("data");
            paperCostModels = JSON.parseObject(PaperDatas.toString(),new TypeReference<List<PaperCostWithBLOBs> >(){});
        }catch (Exception e){
            loger.error(e.toString());
            paperCostModels = null;
        }
        return paperCostModels;
    }
}
