package com.bzdepot.profit.service;

import com.bzdepot.common.util.UserUtil;
import com.bzdepot.profit.bo.InvoiceBo;
import com.bzdepot.profit.mapper.InvoiceMapper;
import com.bzdepot.profit.model.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

@Service
public class InvoiceService {

    private final static Logger loger = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    private InvoiceMapper invoiceMapper;

    /**
     * 更新发票方法
     * @param invoiceBo
     * @return
     */
    @Transactional
    public int update(InvoiceBo invoiceBo){
       int Ok = 0;
       try {
           for(int i = 0; i < invoiceBo.getConfig().length; i++){
                Invoice invoice = invoiceBo.getConfig()[i];
                invoice.setSellerId(invoiceBo.getSellerId());
                if(invoice.getId() == null){
                   invoice.setCreateTime(new Date().getTime());
                   invoice.setUpdateTime(new Date().getTime());
                   Ok = invoiceMapper.insertSelective(invoice);
                }else{
                   invoice.setUpdateTime(new Date().getTime());
                   Ok = invoiceMapper.updateByPrimaryKeySelective(invoice);
                }
                if(Ok == 0){
                    break;
                }
           }
           if(Ok == 0){
               throw new RuntimeException("更新发票配置失败!");
           }

       }catch (Exception e){
           loger.error(e.getMessage());
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
       }

       return Ok;
    }

    /**
     * 获取商家的发票配置
     * @param sellerId
     * @return
     */
    public List<Invoice> findDataBySellerId(Long sellerId){
        return invoiceMapper.selectBySellerId(sellerId);
    }

    /**
     * 删除单条的发票配置
     * @param id
     * @return
     */
    public int delOneData(Long id){
        return invoiceMapper.deleteByPrimaryKey(id,UserUtil.getId());
    }

    /**
     * 获取指定商家的用户组等级的发票配置信息
     * @param sellerId
     * @param comanyId
     * @param types
     * @param levelId
     * @return
     */
    public Invoice findInvoiceData(Long sellerId,Long comanyId,Byte types,Long levelId){
        System.out.println(sellerId);
        System.out.println(comanyId);
        System.out.println(types);
        System.out.println(levelId);
       return invoiceMapper.findInvoiceByLevelIdFuncm(sellerId,comanyId,types,levelId);
    }

    /**
     * 查询当前商家和快递编号是否有发票配置数据列表
     * @param sellerId
     * @param companyId
     * @return
     */
    public List<Invoice> findInvoiceBySellerIdAndCompanyId(Long sellerId,Long companyId){
        return invoiceMapper.selectBySellerIdAndComanyId(sellerId,companyId);
    }
}
