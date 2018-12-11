package com.bzdepot.special.service;

import com.bzdepot.special.mapper.SpotColorMapper;
import com.bzdepot.special.model.SpotColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "SpotColorData")
public class SpotColorService {

    @Autowired
    private SpotColorMapper spotColorMapper;

    /**
     * 获取颜色列表
     * @return
     */
    @Cacheable()
    public List<SpotColor> findSpotColorList(){
        return spotColorMapper.selectAllData();
    }
}
