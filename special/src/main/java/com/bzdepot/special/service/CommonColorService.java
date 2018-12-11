package com.bzdepot.special.service;

import com.bzdepot.special.mapper.CommonColorMapper;
import com.bzdepot.special.model.CommonColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "CommonColor")
public class CommonColorService {

    @Autowired
    private CommonColorMapper commonColorMapper;

    /**
     * 更新机器印刷公共的颜色配置
     * @param commonColor
     * @return
     */
    @CacheEvict(allEntries = true)
    public int updateCommonColor(CommonColor commonColor){
        int Code = 0;
        if(commonColor.getId() == null){
            Code = commonColorMapper.insertSelective(commonColor);
        }else{
            Code = commonColorMapper.updateByPrimaryKeySelective(commonColor);
        }
        return Code;
    }

    /**
     * 查询单条的机器印刷公共的颜色配置
     * @param id
     * @return
     */
    @Cacheable()
    public CommonColor findOneCommonColor(Long id){
        return commonColorMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询机器印刷公共颜色列表
     * @return
     */
    @Cacheable()
    public List<CommonColor> findListCommonColor(){
        return commonColorMapper.selectList();
    }

    /**
     * 删除单条的机器公共的颜色配置
     * @param id
     * @return
     */
    @CacheEvict(allEntries = true)
    public int deleteCommonColor(Long id){
        return commonColorMapper.deleteByPrimaryKey(id);
    }
}
