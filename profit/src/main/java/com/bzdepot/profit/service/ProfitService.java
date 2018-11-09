package com.bzdepot.profit.service;

import com.bzdepot.profit.bo.ProfitDataBo;
import com.bzdepot.profit.mapper.ProfitMapper;
import com.bzdepot.profit.mapper.ProfitRulerMapper;
import com.bzdepot.profit.model.Constitute;
import com.bzdepot.profit.model.Profit;
import com.bzdepot.profit.model.ProfitRuler;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Service
public class ProfitService {

    private final static Logger loger = LoggerFactory.getLogger(ProfitService.class);

    @Autowired
    private ProfitMapper profitMapper;

    @Autowired
    private ConstituteService constituteService;

    @Autowired
    private ProfitRulerMapper profitRulerMapper;

    public ProfitRuler findOne(Long id){
        return profitRulerMapper.selectByPrimaryKey(id);
    }

    public List<ProfitDataBo> callMysqlFunc(Long offerid){
        return profitRulerMapper.findDataByOfferId(offerid);
    }

    /**
     *  修改利润配置
     * @param profit
     * @return
     */
    @Transactional
    public int editProfit(List<Profit> profit, Byte ruleType, Byte defaultType, Constitute constitute){
        int Status = 0;
        try {
            for(int i = 0;i < profit.size();i++){
                Profit Datas = new Profit();

                Datas.setLevelId(profit.get(i).getLevelId());
                Datas.setOfferId(profit.get(i).getOfferId());
                Datas.setLevelId(profit.get(i).getLevelId());
                Datas.setProfitRate(profit.get(i).getProfitRate());
                Datas.setEndValue(profit.get(i).getEndValue());
                Datas.setStartValue(profit.get(i).getStartValue());
                Datas.setTypes(profit.get(i).getTypes());
                if(profit.get(i).getId() == null) {
                    Status = profitMapper.insertSelective(Datas);
                }else{
                    Datas.setId(profit.get(i).getId());
                    Status = profitMapper.updateByPrimaryKeySelective(Datas);
                }
                if(Status < 1){
                    loger.error("更新利润配置失败:"+Datas.toString());
                    break;
                }
            }

            if(Status == 0){
                throw new RuntimeException("更新利润失败");
            }

            Status = this.updateRule(profit.get(0).getOfferId(),ruleType,defaultType);
            if(Status == 0){
                throw new RuntimeException("更新利润规则失败");
            }

            //更新报价构成
            constitute.setOfferId(profit.get(0).getOfferId());
            Status = constituteService.update(constitute);
            if(Status == 0){
                throw new RuntimeException("更新报价构成失败");
            }

        }catch (Exception e){
            loger.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Status;
    }

    /**
     * 更新利润规则方法
     * @param offerId
     * @param rulerType
     * @param dafaultType
     * @return
     */
    public int updateRule(Long offerId,Byte rulerType,Byte dafaultType){
        ProfitRuler profitRuler = new ProfitRuler();
        profitRuler.setOfferId(offerId);
        profitRuler.setDafaultType(dafaultType);
        profitRuler.setRulerType(rulerType);
        Byte rtype = 2;
        if(rulerType == rtype){
            //查询出所有等于当前 offerId的利润规则进行替换规则
            Long[] Ids = profitRulerMapper.findIds(offerId);
            if(Ids.length > 0){
                String IdStr = StringUtils.join(Ids, ',');
                profitRuler.setRulerIds(IdStr);
            }
        }else{
            profitRuler.setRulerIds("");
        }
        //更新最后的创建时间
        profitRuler.setCreateTime(new Date().getTime());
        //判断当前操作是添加还是修改
        ProfitRuler profitRulerRes = profitRulerMapper.selectByOfferId(offerId);
        int Ok = 0;
        if(profitRulerRes == null){
            //添加
            Ok = profitRulerMapper.insertSelective(profitRuler);
        }else{
            //修改
            profitRuler.setId(profitRulerRes.getId());
            Ok = profitRulerMapper.updateByPrimaryKeySelective(profitRuler);
        }
        return Ok;
    }

    /**
     * 查找offerId的利润规则
     * @param offerId
     * @return
     */
    public List<ProfitDataBo> findProfitRule(Long offerId){
        return profitRulerMapper.findDataByOfferId(offerId);
    }
    /**
     * 删除利润数据
     * @param id
     * @return
     */
    public int delProfit(Long id){
        return profitMapper.deleteByPrimaryKey(id);
    }
}
