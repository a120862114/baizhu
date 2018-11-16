package com.bzdepot.special.service;

import com.bzdepot.common.util.UserUtil;
import com.bzdepot.special.bo.PaperCostBo;
import com.bzdepot.special.bo.ProductWantGramBo;
import com.bzdepot.special.bo.ProductWantTextureBo;
import com.bzdepot.special.mapper.PaperCostMapper;
import com.bzdepot.special.model.PaperCost;
import com.bzdepot.special.model.PaperCostWithBLOBs;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Service
@CacheConfig(cacheNames = "PaperCost")
public class PaperCostService {

    private final static Logger loger = LoggerFactory.getLogger(PaperCostService.class);

    @Autowired
    private PaperCostMapper paperCostMapper;

    /**
     * 更新纸张费配置,开启事务模式
     * @param paperCosts
     * @return
     */
    @CacheEvict(allEntries = true)
    @Transactional
    public int updatePaperCost(PaperCostBo paperCosts){
        int Status = 0;
        try {
            for(int i = 0; i < paperCosts.getPostData().length; i++){
                PaperCostWithBLOBs paperCostWithBLOBs = paperCosts.getPostData()[i];
                System.out.println(paperCostWithBLOBs.toString());
                if(UserUtil.getId() != null){
                    paperCostWithBLOBs.setSellerId(UserUtil.getId());
                }
                paperCostWithBLOBs.setCreateTime(new Date().getTime());
                paperCostWithBLOBs.setUpdateTime(new Date().getTime());
                if(paperCostWithBLOBs.getId() == null){
                    Status = paperCostMapper.insertSelective(paperCostWithBLOBs);
                }else {
                    Status = paperCostMapper.updateByPrimaryKeySelective(paperCostWithBLOBs);
                }
                if(Status == 0){
                    break;
                }
            }
            if(Status == 0){
                throw new RuntimeException("更新纸张费配置失败!");
            }
        }catch (Exception e){
            loger.error("更新纸张费配置失败，事务回滚！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Status;
    }

    /**
     * 获取单条纸张费配置
     * @param id
     * @return
     */
    @Cacheable()
    public PaperCostWithBLOBs findPaperCost(Long id){
        return paperCostMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除单条纸张费配置
     * @param id
     * @return
     */
    @CacheEvict(allEntries = true)
    public int deletePaperCost(Long id){
        Long sellerId = UserUtil.getId();
        PaperCostWithBLOBs paperCostWithBLOBs = this.findPaperCost(id);
        if(paperCostWithBLOBs == null){
            return 0;
        }
        if(paperCostWithBLOBs.getSellerId() != sellerId){
            return 0;
        }
        return paperCostMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取纸张费配置分页列表
     * @param pageNum 当前页数
     * @param pageSize 每页条数
     * @return
     */
    @Cacheable()
    public PageInfo listPagerCost(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<PaperCost> PageData = paperCostMapper.selectBySellerIdPageList(UserUtil.getId());
        PageInfo pageInfo = new PageInfo(PageData);
        return pageInfo;
    }

    /**
     * 查询产品配置所需的材质列表
     * @param sellerId
     * @param Status
     * @return
     */
    @Cacheable()
    public List<ProductWantTextureBo> findProductTextureData(Long sellerId,Byte Status){
        return paperCostMapper.findPaperTextureNoRepeat(sellerId,Status);
    }

    /**
     * 查询产品配置所需的厚度并去重
     * @param sellerId
     * @param paperTid
     * @return
     */
    @Cacheable()
    public List<ProductWantGramBo> findProductGramData(Long sellerId,Long paperTid){
        return paperCostMapper.findPaperGramNoRepeat(sellerId,paperTid);
    }
}
