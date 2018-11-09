package com.bzdepot.special.service;

import com.bzdepot.common.util.UserUtil;
import com.bzdepot.special.mapper.PaperSupplierMapper;
import com.bzdepot.special.model.PaperSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperSupplierService {

    @Autowired
    private PaperSupplierMapper paperSupplierMapper;

    /**
     * 更新纸张供应商
     * @param paperSupplier
     * @return
     */
    public int updatePaperSupplier(PaperSupplier paperSupplier){
        paperSupplier.setSellerId(UserUtil.getId());
        paperSupplier.setSupplierBs(paperSupplier.getSupplierName());
        if(paperSupplier.getId() == null){
            return paperSupplierMapper.insertSelective(paperSupplier);
        }else {
            return paperSupplierMapper.updateByPrimaryKeySelective(paperSupplier);
        }
    }

    /**
     * 查询单条纸张供应商数据
     * @param id
     * @return
     */
    public PaperSupplier findPaperSupplier(Long id){
        return paperSupplierMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除纸张供应商
     * @param id
     * @return
     */
    public int deletePaperSupplier(Long id){
        //检测此供应商是否属于自己
        Long sellerId = UserUtil.getId();
        PaperSupplier paperSupplier = this.findPaperSupplier(id);
        if(paperSupplier == null){
            return 0;
        }
        if(paperSupplier.getSellerId() != sellerId){
            return 0;
        }
        return paperSupplierMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取商家纸张供应商列表
     * @return
     */
    public List<PaperSupplier> listPaperSupplier(){
        return paperSupplierMapper.selectBySellerId(UserUtil.getId());
    }
}
