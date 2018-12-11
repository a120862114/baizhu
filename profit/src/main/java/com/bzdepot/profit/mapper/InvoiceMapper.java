package com.bzdepot.profit.mapper;

import com.bzdepot.profit.model.Invoice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvoiceMapper {
    int deleteByPrimaryKey(@Param("id") Long id,@Param("sellerId") Long sellerId);

    int insert(Invoice record);

    int insertSelective(Invoice record);

    Invoice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Invoice record);

    int updateByPrimaryKey(Invoice record);

    List<Invoice> selectBySellerId(Long sellerId);

    Invoice findInvoiceByLevelIdFuncm(@Param("sellerId") Long sellerId,@Param("comanyId") Long comanyId,@Param("types") Byte types,@Param("levelId") Long levelId);

    List<Invoice> selectBySellerIdAndComanyId(@Param("sellerId") Long sellerId,@Param("comanyId") Long comanyId);
}