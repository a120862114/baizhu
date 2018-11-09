package com.bzdepot.client.mapper;

import com.bzdepot.client.model.LevelModule;
import com.bzdepot.client.vo.ClientUserNameBo;

import java.util.List;

public interface LevelModuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LevelModule record);

    int insertSelective(LevelModule record);

    LevelModule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LevelModule record);

    int updateLevelIdByUserIdSelective(LevelModule record);

    int updateByPrimaryKey(LevelModule record);

    List<ClientUserNameBo> searchLevelUserPage(LevelModule record);

    Long countLevelUserNums(Long levelId);

    LevelModule findLevelIdByUserIdAndSellerId(LevelModule record);

    List<ClientUserNameBo> findMyParentUserData(LevelModule record);
}