package com.bzdepot.special.service;

import com.bzdepot.common.util.UserUtil;
import com.bzdepot.special.mapper.MachineBrandMapper;
import com.bzdepot.special.model.MachineBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineBrandService {

    @Autowired
    private MachineBrandMapper machineBrandMapper;

    /**
     * 更新机器品牌
     * @param machineBrand
     * @return
     */
    public int updateMachineBrand(MachineBrand machineBrand){
        machineBrand.setSellerId(UserUtil.getId());
        machineBrand.setBrandBs(machineBrand.getBrandName());
        if(machineBrand.getId() == null){
            return machineBrandMapper.insertSelective(machineBrand);
        }else {
            return machineBrandMapper.updateByPrimaryKeySelective(machineBrand);
        }
    }

    /**
     * 查询单条机器品牌数据
     * @param id
     * @return
     */
    public MachineBrand findMachineBrand(Long id){
        return machineBrandMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除单条机器品牌
     * @param id
     * @return
     */
    public int deleteMachineBrand(Long id){
        Long sellerId = UserUtil.getId();
        MachineBrand machineBrand = this.findMachineBrand(id);
        if(machineBrand == null){
            return 0;
        }
        if(machineBrand.getSellerId() != sellerId){
            return 0;
        }
        return machineBrandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取机器品牌列表
     * @return
     */
    public List<MachineBrand> listMachineBrand(){
        return machineBrandMapper.selectBySellerId(UserUtil.getId());
    }
}
