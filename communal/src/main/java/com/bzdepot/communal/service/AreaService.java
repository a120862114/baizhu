package com.bzdepot.communal.service;

import com.bzdepot.communal.mapper.AreaMapper;
import com.bzdepot.communal.model.Area;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableCaching
@CacheConfig(cacheNames="AreaCache")
public class AreaService {

    private final static Logger loger = LoggerFactory.getLogger(AreaService.class);

    @Autowired
    private AreaMapper areaMapper;

    @Cacheable(value="arealist",key="T(String).valueOf(#ParentId)")
    public List<Area> selectCityParentList(Integer ParentId){
        loger.info("直接获取的数据没有执行缓存");
        return areaMapper.findCityParent(ParentId);
    }
}
