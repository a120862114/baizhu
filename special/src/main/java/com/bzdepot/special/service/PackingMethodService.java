package com.bzdepot.special.service;

import com.bzdepot.common.util.UserUtil;
import com.bzdepot.special.mapper.PackingMethodMapper;
import com.bzdepot.special.model.PackingMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackingMethodService {

    @Autowired
    private PackingMethodMapper packingMethodMapper;

    /**
     * 更新包装方式或登记
     * @param packingMethod
     * @return
     */
    public int updatePackingMethod(PackingMethod packingMethod){
        packingMethod.setPackingBs(packingMethod.getPackingName());
        packingMethod.setSellerId(UserUtil.getId());
        if(packingMethod.getId() == null){
            return packingMethodMapper.insertSelective(packingMethod);
        }else{
            return packingMethodMapper.updateByPrimaryKeySelective(packingMethod);
        }
    }

    /**
     * 查询单条的包装方式或登记
     * @param id
     * @return
     */
    public PackingMethod findPackingMethod(Long id){
        return packingMethodMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除单条的包装方式或登记
     * @param id
     * @return
     */
    public int deletePackingMethod(Long id){
        //检测此条包装方式是否属于当前商家
        Long sellerId = UserUtil.getId();
        PackingMethod packingMethod = this.findPackingMethod(id);
        if(packingMethod == null){
            return 0;
        }
        if(sellerId != packingMethod.getSellerId()){
            return 0;
        }
        return packingMethodMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取包装方式列表数据
     * @return
     */
    public List<PackingMethod> listPackingMethod(){
        Long sellerId = UserUtil.getId();
        return packingMethodMapper.selectBySellerId(sellerId);
    }
}
