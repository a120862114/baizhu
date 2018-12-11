package com.bzdepot.profit.service;

import com.bzdepot.profit.bo.ProfitUltimateBo;
import com.bzdepot.profit.bo.ProfitUltimateSonBo;
import com.bzdepot.profit.mapper.ProfitUltimateMapper;
import com.bzdepot.profit.model.ProfitUltimate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class ProfitUltimateService {

    private final static Logger loger = LoggerFactory.getLogger(ProfitUltimateService.class);

    @Autowired
    private ProfitUltimateMapper profitUltimateMapper;

    /**
     * 更新利润数据
     * @param profitUltimateBo
     * @return
     */
    @Transactional
    public int updateProfitUltimate(ProfitUltimateBo profitUltimateBo){
        int Code = 0;
        try {
            if(profitUltimateBo.getProfitDetail() == null || profitUltimateBo.getProfitDetail().size() == 0){
                return Code;
            }
            ProfitUltimate profitUltimate = new ProfitUltimate();
            profitUltimate.setSellerId(profitUltimateBo.getSellerId());
            profitUltimate.setClassId(profitUltimateBo.getClassId());
            profitUltimate.setTextureId(profitUltimateBo.getTextureId());
            profitUltimate.setGramId(profitUltimateBo.getGramId());
            List<ProfitUltimateSonBo> profitUltimateSonBos = profitUltimateBo.getProfitDetail();
            for(int i = 0; i < profitUltimateSonBos.size(); i++){
                profitUltimate.setTypes(profitUltimateSonBos.get(i).getTypes());
                profitUltimate.setStartValue(profitUltimateSonBos.get(i).getStartValue());
                profitUltimate.setEndValue(profitUltimateSonBos.get(i).getEndValue());
                profitUltimate.setLevelId(profitUltimateSonBos.get(i).getLevelId());
                profitUltimate.setProfitRate(profitUltimateSonBos.get(i).getProfitRate());
                if(profitUltimateSonBos.get(i).getId() == null){
                    Code = profitUltimateMapper.insertSelective(profitUltimate);
                }else {
                    profitUltimate.setId(profitUltimateSonBos.get(i).getId());
                    Code = profitUltimateMapper.updateByPrimaryKeySelective(profitUltimate);
                }
                if(Code == 0){
                    break;
                }
            }
            if(Code == 0){
                throw new RuntimeException("更新终极利润失败!");
            }
        }catch (Exception e){
            loger.error(e.toString());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Code;
    }

    /**
     * 查询单条的利润数据
     * @param id
     * @return
     */
    public ProfitUltimate selectOneForProfitUltimate(Long id){
        return profitUltimateMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除单条的利润数据
     * @param id
     * @return
     */
    public int deleteProfitUltimate(Long id){
        return profitUltimateMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取利润的列表
     * @param sellerId
     * @param classId
     * @param textureId
     * @param gramId
     * @return
     */
    public List<ProfitUltimate> selectListProfitUltimate(Long sellerId, Long classId, Long textureId, Long gramId){
        return profitUltimateMapper.selectListData(sellerId,classId,textureId,gramId);
    }
}
