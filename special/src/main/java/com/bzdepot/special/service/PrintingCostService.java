package com.bzdepot.special.service;

import com.bzdepot.common.util.UserUtil;
import com.bzdepot.special.bo.PrintingCostBo;
import com.bzdepot.special.mapper.PrintingCostMapper;
import com.bzdepot.special.mapper.PrintingSpotConfigMapper;
import com.bzdepot.special.model.PrintingCost;
import com.bzdepot.special.model.PrintingSpotConfig;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Service
public class PrintingCostService {

    private final static Logger loger = LoggerFactory.getLogger(PrintingCostService.class);

    @Autowired
    private PrintingCostMapper printingCostMapper;

    @Autowired
    private PrintingSpotConfigMapper printingSpotConfigMapper;

    /**
     * 更新印刷费配置
     * @param printingCostBo
     * @return
     */
    @Transactional
    public int updatePrintingCost(PrintingCostBo printingCostBo){
        int Status = 0;
        try {
            for(int i = 0; i < printingCostBo.getPostData().length; i++){
                PrintingCost printingCost = printingCostBo.getPostData()[i];
                if(UserUtil.getId() != null && printingCost.getSellerId() == null){
                    printingCost.setSellerId(UserUtil.getId());
                }
                printingCost.setCreateTime(new Date().getTime());
                printingCost.setUpdateTime(new Date().getTime());
                if(printingCost.getId() == null){
                    Status = printingCostMapper.insertSelective(printingCost);
                }else{
                    Status = printingCostMapper.updateByPrimaryKeySelective(printingCost);
                }
                if(Status == 0){
                    break;
                }
                if(printingCost.getColorConfigs() == null || printingCost.getColorConfigs().size() == 0){
                    continue;
                }
                for(int a = 0; a < printingCost.getColorConfigs().size(); a++){
                    PrintingSpotConfig printingSpotConfig = printingCost.getColorConfigs().get(a);
                    printingSpotConfig.setSellerId(printingCost.getSellerId());
                    printingSpotConfig.setPrintingCostId(printingCost.getId());
                    if(printingSpotConfig.getId() == null){
                        Status = printingSpotConfigMapper.insertSelective(printingSpotConfig);
                    }else {
                        Status = printingSpotConfigMapper.updateByPrimaryKeySelective(printingSpotConfig);
                    }
                    if(Status == 0){
                        break;
                    }
                }
                if(Status == 0){
                    break;
                }
            }
            if(Status == 0){
                throw new RuntimeException("更新印刷费配置失败，事务回滚!");
            }
        }catch (Exception e){
            loger.error("更新印刷费配置失败，事务回滚!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Status;
    }

    /**
     * 查询单条印刷费配置
     * @param id
     * @return
     */
    public PrintingCost findPrintingCost(Long id){
        return printingCostMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除单条印刷费配置
     * @param id
     * @return
     */
    public int deletePrintingCost(Long id){
        Long sellerId = UserUtil.getId();
        PrintingCost printingCost = this.findPrintingCost(id);
        if(printingCost == null){
            return 0;
        }
        if(printingCost.getSellerId() != sellerId){
            return 0;
        }
        return printingCostMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询印刷费配置分页列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo listPrintingCost(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<PrintingCost> PageData = printingCostMapper.selectBySellerId(UserUtil.getId());
        PageInfo pageInfo = new PageInfo(PageData);
        return pageInfo;
    }

}
