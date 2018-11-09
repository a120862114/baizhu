package com.bzdepot.offer.service;

import com.bzdepot.offer.mapper.ProfitDefaultMapper;
import com.bzdepot.offer.mapper.ProfitMapper;
import com.bzdepot.offer.model.Profit;
import com.bzdepot.offer.model.ProfitDefault;
import com.bzdepot.offer.vo.ProfitAttrVo;
import com.bzdepot.offer.vo.ProfitVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class ProfitService {
    private final static Logger loger = LoggerFactory.getLogger(ProfitService.class);
    @Autowired
    private ProfitMapper profitMapper;
    @Autowired
    private ProfitDefaultMapper profitDefaultMapper;

    /*
    *   添加利润配置方法
    * */
    @Transactional
    public int addProfit(ProfitVo profitvo,List<ProfitAttrVo> profitAttrVo){
        int Status = 0;
        try {
            for (int i = 0; i < profitAttrVo.size(); i++) {
                Profit Datas = new Profit();
                Datas.setOfferId(profitvo.getOfferId());
                Datas.setTypes(profitvo.getTypes());
                Datas.setParentOfferId(profitvo.getParentOfferId());
                Datas.setStartValue(profitAttrVo.get(i).getStartValue());
                Datas.setStartValue(profitAttrVo.get(i).getStartValue());
                Datas.setEndValue(profitAttrVo.get(i).getEndValue());
                Datas.setProfitRate(profitAttrVo.get(i).getProfitRate());
                Datas.setLevelId(profitAttrVo.get(i).getLevelId());

                if(profitAttrVo.get(i).getId() == null){
                    Status = profitMapper.insertSelective(Datas);
                }else{
                    Datas.setId(profitAttrVo.get(i).getId());
                    Status = profitMapper.updateByPrimaryKeySelective(Datas);
                }

                if (Status < 1) {
                    Status = 0;
                    loger.error("添加利润失败:"+Datas.toString());
                    throw new RuntimeException("添加利润配置失败!");
                }
            }
        }catch (Exception e){
            loger.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Status;
    }

    /**
     *  修改利润配置
     * @param profit
     * @return
     */
    @Transactional
    public int editProfit(List<Profit> profit){
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
                Datas.setParentOfferId(profit.get(i).getParentOfferId());
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
                throw new RuntimeException("修改失败");
            }
        }catch (Exception e){
            loger.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Status;
    }

    /**
     * 删除利润数据
     * @param id
     * @return
     */
    public int delProfit(Long id){
        return profitMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取利润配置列表
     */
    public List<Profit> getProfitList(Long OfferId){
        return profitMapper.findOfferIdList(OfferId);
    }

    /**
     * 更新默认配置
     */
    public Long updateProfitDefault(ProfitDefault profitDefault){
        int Ok = 0;
        Long Id = null;
        if(profitDefault.getId() == null){
            Ok = profitDefaultMapper.insertSelective(profitDefault);
            if(Ok > 0){
                Id = profitDefault.getId();
            }
        }else {
            Ok = profitDefaultMapper.updateByPrimaryKeySelective(profitDefault);
            if(Ok > 0){
                Id = profitDefault.getId();
            }
        }
        return Id;
    }

    /**
     * 查询默认配置
     */
    public ProfitDefault findDefaultProfitType(Long offerId){
        return profitDefaultMapper.findOfferDefaultSet(offerId);
    }
}
