package com.bzdepot.special.service;

import com.bzdepot.common.util.UserUtil;
import com.bzdepot.special.mapper.MachineColorMapper;
import com.bzdepot.special.model.MachineColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineColorService {

    @Autowired
    private MachineColorMapper machineColorMapper;

    /**
     * 更新机器颜色
     * @param machineColor
     * @return
     */
    public int updateColor(MachineColor machineColor){
        machineColor.setSellerId(UserUtil.getId());
        machineColor.setColorBs(machineColor.getColorName());
        if(machineColor.getId() == null){
            return machineColorMapper.insertSelective(machineColor);
        }else {
            return machineColorMapper.updateByPrimaryKeySelective(machineColor);
        }
    }

    /**
     * 获取机器颜色单条数据
     * @param id
     * @return
     */
    public MachineColor findMachineColor(Long id){
        return machineColorMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除机器颜色单条数据
     * @param id
     * @return
     */
    public int deleteMachineColor(Long id){
        Long sellerId = UserUtil.getId();
        MachineColor machineColor = this.findMachineColor(id);
        if(machineColor == null){
            return 0;
        }
        if(machineColor.getSellerId() != sellerId){
            return 0;
        }
        return machineColorMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取机器颜色列表数据
     * @return
     */
    public List<MachineColor> listMachineColor(){
        return machineColorMapper.selectBySellerId(UserUtil.getId());
    }
}
