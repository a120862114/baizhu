package com.bzdepot.special.service;

import com.bzdepot.common.util.UserUtil;
import com.bzdepot.special.mapper.PaperBrandMapper;
import com.bzdepot.special.model.PaperBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperBrandService {

    @Autowired
    private PaperBrandMapper paperBrandMapper;

    /**
     * 更新纸张品牌数据
     * @param paperBrand
     * @return
     */
    public int UpdatePaperBrand(PaperBrand paperBrand){
        paperBrand.setSellerId(UserUtil.getId());
        paperBrand.setpBs(paperBrand.getPnames());
        if(paperBrand.getId() == null){
            return paperBrandMapper.insertSelective(paperBrand);
        }else {
            return paperBrandMapper.updateByPrimaryKeySelective(paperBrand);
        }
    }

    /**
     * 根据主键ID删除单个纸张品牌数据
     * @param brandId
     * @return
     */
    public int deletePaperBrand(Long brandId){
        //验证删除的当前数据是否数据此商家
        PaperBrand paperBrand = this.findPaperBrand(brandId);
        if(paperBrand == null){
            return 0;
        }
        if(paperBrand.getSellerId() != UserUtil.getId()){
            return 0;
        }
        return paperBrandMapper.deleteByPrimaryKey(brandId);
    }

    /**
     * 查询当前商家的纸张品牌数据
     * @return
     */
    public List<PaperBrand> listPaperBrand(){
        Long sellerId = UserUtil.getId();
        return paperBrandMapper.selectBySellerId(sellerId);
    }

    /**
     * 根据主键ID查询单条纸张品牌数据
     * @param id
     * @return
     */
    public PaperBrand findPaperBrand(Long id){
        return paperBrandMapper.selectByPrimaryKey(id);
    }
}
