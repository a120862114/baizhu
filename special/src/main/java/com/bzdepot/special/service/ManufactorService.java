package com.bzdepot.special.service;

import com.bzdepot.common.util.UserUtil;
import com.bzdepot.special.mapper.ManufactorMapper;
import com.bzdepot.special.model.Manufactor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufactorService {

    @Autowired
    private ManufactorMapper manufactorMapper;

    /**
     * 更新机器所属厂家
     * @param manufactor
     * @return
     */
    public int updateManufactor(Manufactor manufactor){
        manufactor.setSellerId(UserUtil.getId());
        manufactor.setBs(manufactor.getNames());
        if(manufactor.getId() == null){
            return manufactorMapper.insertSelective(manufactor);
        }else {
            return manufactorMapper.updateByPrimaryKeySelective(manufactor);
        }
    }

    /**
     * 获取单条机器所属厂家数据
     * @param id
     * @return
     */
    public Manufactor findManufactor(Long id){
        return manufactorMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除单条机器厂家数据
     * @param id
     * @return
     */
    public int deleteManufactor(Long id){
        Long sellerId = UserUtil.getId();
        Manufactor manufactor = this.findManufactor(id);
        if(manufactor == null){
            return 0;
        }
        if(manufactor.getSellerId() != sellerId){
            return 0;
        }
        return manufactorMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取机器厂家数据列表
     * @return
     */
    public List<Manufactor> listManufactor(){
        return manufactorMapper.selectBySellerId(UserUtil.getId());
    }
}
