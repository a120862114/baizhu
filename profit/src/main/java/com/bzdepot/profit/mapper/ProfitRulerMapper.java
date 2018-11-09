package com.bzdepot.profit.mapper;

import com.bzdepot.profit.bo.ProfitDataBo;
import com.bzdepot.profit.model.ProfitRuler;

import java.util.List;

public interface ProfitRulerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProfitRuler record);

    int insertSelective(ProfitRuler record);

    ProfitRuler selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProfitRuler record);

    int updateByPrimaryKeyWithBLOBs(ProfitRuler record);

    int updateByPrimaryKey(ProfitRuler record);

    List<ProfitDataBo> findDataByOfferId(Long offerid);

    ProfitRuler selectByOfferId(Long offerId);

    Long[] findIds(Long offerId);
}