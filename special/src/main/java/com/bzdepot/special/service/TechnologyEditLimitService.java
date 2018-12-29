package com.bzdepot.special.service;

import com.bzdepot.special.mapper.TechnologyEditLimitMapper;
import com.bzdepot.special.model.TechnologyEditLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "TechnologyEditLimit")
public class TechnologyEditLimitService {

    @Autowired
    private TechnologyEditLimitMapper technologyEditLimitMapper;

    /**
     * 批量添加数据
     * @return
     */
    @CacheEvict(allEntries = true)
    public int insertAllData(List<TechnologyEditLimit> technologyEditLimits, Long sellerId, Long tClassId, Long tAttrId){
        return technologyEditLimitMapper.insert(technologyEditLimits,sellerId,tClassId,tAttrId);
    }

    /**
     * 批量删除数据
     * @param sellerId
     * @param tClassId
     * @param tAttrId
     * @return
     */
    @CacheEvict(allEntries = true)
    public int deleteAllData(Long sellerId,Long tClassId,Long tAttrId){
        return technologyEditLimitMapper.deleteData(sellerId,tClassId,tAttrId);
    }

    /**
     * 批量查询数据
     * @param sellerId
     * @param tClassId
     * @param tAttrId
     * @return
     */
    @Cacheable()
    public List<TechnologyEditLimit> selectAllData(Long sellerId,Long tClassId,Long tAttrId){
        return technologyEditLimitMapper.selectData(sellerId,tClassId,tAttrId);
    }
}
