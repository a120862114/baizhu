package com.bzdepot.transport.mapper;

import com.bzdepot.transport.model.BlockGroup;
import com.bzdepot.transport.model.BlockGroupData;
import com.bzdepot.transport.model.BlockGroupWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlockGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlockGroupWithBLOBs record);

    int insertSelective(BlockGroupWithBLOBs record);

    BlockGroupWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlockGroupWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(BlockGroupWithBLOBs record);

    int updateByPrimaryKey(BlockGroup record);

    int CountByBlockNode(@Param("sellerid") Long sellerid,@Param("companyid") Long companyid,@Param("regionid") Long regionid,@Param("blockid") Integer blockid);

    int updateBlockDatas(BlockGroupWithBLOBs record);

    List<BlockGroupData> findGroupAndPrice(BlockGroupData record);
}