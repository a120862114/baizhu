package com.bzdepot.transport.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.common.util.UserUtil;
import com.bzdepot.transport.fegin.InvoiceService;
import com.bzdepot.transport.model.BlockGroup;
import com.bzdepot.transport.model.ComanyDefault;
import com.bzdepot.transport.model.TransportCompany;
import com.bzdepot.transport.model.TransportRegion;
import com.bzdepot.transport.service.BlockService;
import com.bzdepot.transport.service.ComanyDefaultService;
import com.bzdepot.transport.service.TransportCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/company")
public class TransportCompanyController {

    @Autowired
    private TransportCompanyService transportCompanyService;

    @Autowired
    private ComanyDefaultService comanyDefaultService;

    @Autowired
    private BlockService blockService;

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping(value = "/list/{typeid}/{sellerId}")
    public Object getCompanyByTypeId(@PathVariable("typeid") Long typeid,@PathVariable("sellerId") Long sellerId){
        List<TransportCompany> ResultData = transportCompanyService.getList(typeid);
        if(ResultData.size() > 0){
            ComanyDefault comanyDefault = comanyDefaultService.findDefault(sellerId);
            Map<String,Object> Datas = new HashMap<String, Object>();
            Datas.put("list",ResultData);
            Datas.put("default",comanyDefault);
            return JsonReturn.SetMsg(0,"获取运送公司成功!",Datas);
        }
        return JsonReturn.SetMsg(10011,"获取失败!","");
    }

    @GetMapping(value = "/block/{companyid}/{sellerid}")
    public Object getBlockList(@PathVariable("companyid") Long companyid,@PathVariable("sellerid") Long sellerid){
        List<TransportRegion> ReData = transportCompanyService.getBlockListDataByCid(sellerid,companyid);
        if(ReData.size() > 0){
            return JsonReturn.SetMsg(0,"获取运送区块成功!",ReData);
        }
        return JsonReturn.SetMsg(10011,"获取运送区块公司失败!","");
    }

    /**
     * 获取商家已配置的运输公司列表
     * @param typeid
     * @return
     */
    @GetMapping(value = "/hasdata/company/{typeid}/{sellerid}")
    public Object getHasDataCompany(@PathVariable("typeid") Long typeid,@PathVariable("sellerid") Long sellerid){
        if(typeid == null){
            return JsonReturn.SetMsg(10010,"运输公司类型不能为空!","");
        }
        if(sellerid == null){
            return JsonReturn.SetMsg(10010,"商家编号不能为空!","");
        }
        List<TransportCompany> ResList = transportCompanyService.getHasCitysForCompany(typeid,sellerid);
        if(ResList != null && ResList.size() > 0){
           ComanyDefault comanyDefault = comanyDefaultService.findDefault(sellerid);
           Map<String,Object> JsonData = new HashMap<String, Object>();
           JsonData.put("companyData",ResList);
           if(comanyDefault != null){
               JsonData.put("defaultData",comanyDefault);
           }
            return JsonReturn.SetMsg(0,"获取运输公司数据成功!",JsonData);
        }
        return JsonReturn.SetMsg(10011,"获取运输公司数据失败!","");
    }

    /**
     * 更新运输公司API
     * @param transportCompany
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateCompanyApi(@Valid @ModelAttribute TransportCompany transportCompany, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }

        int Ok = transportCompanyService.updateCompany(transportCompany);
        if(Ok > 0){
            return JsonReturn.SetMsg(0,"更新运输公司数据成功！","");
        }
        return JsonReturn.SetMsg(10011,"更新运输公司数据失败!","");
    }

    /**
     * 获取当前商家添加的运输公司数据
     * @return
     */
    @PostMapping(value = "/self/list")
    public Object selfList(){
        List<TransportCompany> transportCompanies = transportCompanyService.findSelfComany();
        if(transportCompanies.size() > 0){
            return JsonReturn.SetMsg(0,"获取运输公司数据成功!",transportCompanies);
        }
        return JsonReturn.SetMsg(10011,"获取运输公司数据失败!","");
    }

    /**
     * 更新默认快递
     * @param comanyId
     * @return
     */
    @GetMapping(value = "/default/set/{comanyId}")
    public Object setDefaultComany(@PathVariable("comanyId") Long comanyId){
        if(comanyId == null){
            return JsonReturn.SetMsg(10010,"运输公司编号不能为空!","");
        }
        ComanyDefault comanyDefault = new ComanyDefault();
        comanyDefault.setComanyId(comanyId);
        comanyDefault.setSellerId(UserUtil.getId());
        int Ok = comanyDefaultService.update(comanyDefault);
        if(Ok > 0){
            return JsonReturn.SetMsg(0,"更新默认运输公司成功!",comanyDefault.getId());
        }
        return JsonReturn.SetMsg(10011,"更新默认运输公司失败","");
    }

    /**
     * 删除闲置的快递数据的API接口
     * @param companyId
     * @return
     */
    @GetMapping(value = "/del/comany/{companyId}")
    public Object deleteCompanyDataApi(@PathVariable("companyId") Long companyId){
        if(companyId == null){
            return JsonReturn.SetMsg(10010,"快递的编号不能为空!","");
        }
        TransportCompany transportCompany = transportCompanyService.findOneComanyById(companyId);
        if(transportCompany == null){
            return JsonReturn.SetMsg(10010,"当前您要删除的快递数据不存在,或已经被删除!","");
        }
        List<BlockGroup> blockGroups = blockService.findComanyForCitysData(transportCompany.getSellerId(),transportCompany.getId());
        if(blockGroups != null && blockGroups.size() > 0){
            return JsonReturn.SetMsg(10010,"不能删除已经存在配置的快递数据!","");
        }
        Object resultDataJson = invoiceService.findCheckInvoiceDataApi(transportCompany.getSellerId(),transportCompany.getId());
        if(resultDataJson != null){
            Map<String,Object> resultData = JsonReturn.Parse(resultDataJson);
            if(resultData != null){
                try {
                    Integer ErrorCode = (Integer) resultData.get("error_code");
                    if(ErrorCode == 0){
                        return JsonReturn.SetMsg(10010,"此快递包含发票配置信息，暂不能删除!","");
                    }
                }catch (Exception e){
                    return JsonReturn.SetMsg(-1,"验证发票配置信息是异常!","");
                }
            }
        }
        if(transportCompanyService.deleteCompanyData(companyId) > 0){
            return JsonReturn.SetMsg(0,"删除快递成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除快递失败!","");
    }
}
