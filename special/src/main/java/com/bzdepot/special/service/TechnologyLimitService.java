package com.bzdepot.special.service;

import com.bzdepot.special.mapper.TechnologyLimitMapper;
import com.bzdepot.special.model.TechnologyLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "TechnologyLimit")
public class TechnologyLimitService {

    @Autowired
    private TechnologyLimitMapper technologyLimitMapper;

    /**
     * 批量添加数据
     * @return
     */
    @CacheEvict(allEntries = true)
    public int insertAllData(List<TechnologyLimit> technologyLimit,Long sellerId,Long tClassId,Long tAttrId){
        return technologyLimitMapper.insert(technologyLimit,sellerId,tClassId,tAttrId);
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
        return technologyLimitMapper.deleteData(sellerId,tClassId,tAttrId);
    }

    /**
     * 批量查询数据
     * @param sellerId
     * @param tClassId
     * @param tAttrId
     * @return
     */
    @Cacheable()
    public List<TechnologyLimit> selectAllData(Long sellerId,Long tClassId,Long tAttrId){
        return technologyLimitMapper.selectData(sellerId,tClassId,tAttrId);
    }
}
