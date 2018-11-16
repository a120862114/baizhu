package com.bzdepot.special.mapper;

import com.bzdepot.special.bo.ProductWantGramBo;
import com.bzdepot.special.bo.ProductWantTextureBo;
import com.bzdepot.special.model.PaperCost;
import com.bzdepot.special.model.PaperCostWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaperCostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaperCostWithBLOBs record);

    int insertSelective(PaperCostWithBLOBs record);

    PaperCostWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaperCostWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PaperCostWithBLOBs record);

    int updateByPrimaryKey(PaperCost record);

    List<PaperCost> selectBySellerIdPageList(Long sellerId);

    List<ProductWantTextureBo> findPaperTextureNoRepeat(@Param("sellerId") Long sellerId,@Param("status") Byte status);

    List<ProductWantGramBo> findPaperGramNoRepeat(@Param("sellerId") Long sellerId,@Param("paperTid") Long paperTid);
}