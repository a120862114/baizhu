package com.bzdepot.profit.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.common.util.UserUtil;
import com.bzdepot.profit.bo.InvoiceBo;
import com.bzdepot.profit.model.Invoice;
import com.bzdepot.profit.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/invoice")
public class InvoiceController {

    private final static Logger loger = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private InvoiceService invoiceService;

    /**
     * 更新发票接口
     * @param invoiceBo
     * @param result
     * @param request
     * @return
     */
    @PostMapping(value = "/update")
    public Object Update(@Valid @ModelAttribute InvoiceBo invoiceBo, BindingResult result, HttpServletRequest request){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");

            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
         System.out.println(invoiceBo.toString());
         System.out.println(invoiceBo.getConfig()[0].toString());
         if(invoiceBo.getConfig().length == 0){
            return JsonReturn.SetMsg(10010,"发票配置参数不能为空!","");
         }
        invoiceBo.setSellerId(UserUtil.getId());
        int Ok = invoiceService.update(invoiceBo);
        if(Ok > 0){
            return JsonReturn.SetMsg(0,"更新发票配置成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新发票配置失败!","");
    }

    /**
     * 获取当前商家的发票配置列表
     * @return
     */
    @GetMapping(value = "/list")
    public Object listData(){
       List<Invoice> invoices = invoiceService.findDataBySellerId(UserUtil.getId());
       if(invoices.size() > 0){
           return JsonReturn.SetMsg(0,"获取发票配置成功!",invoices);
       }
       return JsonReturn.SetMsg(10011,"获取发票配置失败！","");
    }

    /**
     * 删除发票数据接口
     * @param id
     * @return
     */
    @GetMapping(value = "/del/{id}")
    public Object delData(@PathVariable("id") Long id){
        if(id == null){
            return JsonReturn.SetMsg(10010,"编号不能为空!","");
        }
        int Ok = invoiceService.delOneData(id);
        if(Ok > 0){
            return JsonReturn.SetMsg(0,"删除发票配置成功！","");
        }
        return JsonReturn.SetMsg(10011,"删除发票配置失败!","");
    }

    /**
     * 获取指定商家的用户等级配置的发票信息
     * @param sellerid
     * @param comanyid
     * @param types
     * @param levelid
     * @return
     */
    @GetMapping(value = "/by/level/{sellerid}/{comanyid}/{types}/{levelid}")
    public Object findDataByLevel(@PathVariable("sellerid") Long sellerid,@PathVariable("comanyid") Long comanyid,@PathVariable("types") Byte types,@PathVariable("levelid") Long levelid){
        Invoice data = invoiceService.findInvoiceData(sellerid,comanyid,types,levelid);
        if(data != null){
            return JsonReturn.SetMsg(0,"获取商家发票设置信息成功!",data);
        }
        return JsonReturn.SetMsg(10011,"获取商家发票设置信息失败!","");
    }

    /**
     * 根据商家编号与快递编号获取发票配置信息列表的API接口
     * @param sellerId
     * @param companyId
     * @return
     */
    @GetMapping(value = "/find/check/{sellerId}/{companyId}")
    public Object findCheckInvoiceDataApi(@PathVariable("sellerId") Long sellerId,@PathVariable("companyId") Long companyId){
        if(sellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号不能为空!","");
        }
        if(companyId == null){
            return JsonReturn.SetMsg(10010,"快递编号不能为空!","");
        }
        List<Invoice> invoices = invoiceService.findInvoiceBySellerIdAndCompanyId(sellerId,companyId);
        if (invoices != null && invoices.size() > 0){
            return JsonReturn.SetMsg(0,"获取商家快递为准的发票配置信息成功!",invoices);
        }
        return JsonReturn.SetMsg(10011,"获取商家快递为准的发票配置信息失败!","");
    }
}
