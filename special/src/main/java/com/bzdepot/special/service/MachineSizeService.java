package com.bzdepot.special.service;

import com.bzdepot.common.util.UserUtil;
import com.bzdepot.special.mapper.MachineSizeMapper;
import com.bzdepot.special.model.MachineSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineSizeService {

    @Autowired
    private MachineSizeMapper machineSizeMapper;

    /**
     * 更新机器大小配置
     * @param machineSize
     * @return
     */
    public int updateMachineSize(MachineSize machineSize){
        machineSize.setSellerId(UserUtil.getId());
        machineSize.setMachineBs(machineSize.getMachineName());
        if(machineSize.getId() == null){
            return machineSizeMapper.insertSelective(machineSize);
        }else {
            return machineSizeMapper.updateByPrimaryKeySelective(machineSize);
        }
    }

    /**
     * 查询单条机器大小配置
     * @param id
     * @return
     */
    public MachineSize findMachineSize(Long id){
        return machineSizeMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除单体机器大小配置
     * @param id
     * @return
     */
    public int deleteMachineSize(Long id){
        Long sellerId = UserUtil.getId();
        MachineSize machineSize = this.findMachineSize(id);
        if(machineSize == null){
            return 0;
        }
        if(machineSize.getSellerId() != sellerId){
            return 0;
        }
        return machineSizeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取机器大小配置列表
     * @return
     */
    public List<MachineSize> listMachineSize(){
        return machineSizeMapper.selectBySellerId(UserUtil.getId());
    }
}
