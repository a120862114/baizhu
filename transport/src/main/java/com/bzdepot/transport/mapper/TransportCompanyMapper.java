package com.bzdepot.transport.mapper;

import com.bzdepot.transport.model.TransportCompany;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransportCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TransportCompany record);

    int insertSelective(TransportCompany record);

    TransportCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TransportCompany record);

    int updateByPrimaryKey(TransportCompany record);

    List<TransportCompany> selectByTypeId(@Param("typeId") Long typeId , @Param("sellerId") Long sellerId);

    List<TransportCompany> findHasCityData(TransportCompany record);

    List<TransportCompany> findSelfComany(Long sellerId);
}