package com.bzdepot.offer.mapper;

import com.bzdepot.offer.model.ProfitDefault;

public interface ProfitDefaultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProfitDefault record);

    int insertSelective(ProfitDefault record);

    ProfitDefault selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProfitDefault record);

    int updateByPrimaryKey(ProfitDefault record);

    ProfitDefault findOfferDefaultSet(Long offerId);

}