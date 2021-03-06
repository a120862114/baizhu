package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.special.bo.JoinSelectBo;
import com.bzdepot.special.bo.PaperCostBo;
import com.bzdepot.special.bo.ProductWantGramBo;
import com.bzdepot.special.bo.ProductWantTextureBo;
import com.bzdepot.special.model.PaperCostWithBLOBs;
import com.bzdepot.special.service.PaperCostService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/paper/config")
public class PaperCostController {

    private final static Logger loger = LoggerFactory.getLogger(PaperCostController.class);

    @Autowired
    private PaperCostService paperCostService;

    /**
     * 更新纸张配置API接口
     * @param paperCosts
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updatePaperCost(@Valid @ModelAttribute PaperCostBo paperCosts, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        System.out.println(paperCosts.toString());
        if(paperCostService.updatePaperCost(paperCosts) > 0){
            return JsonReturn.SetMsg(0,"更新纸张配置成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新纸张配置失败!","");
    }

    /**
     * 删除纸张费配置API接口
     * @param costId
     * @return
     */
    @GetMapping(value = "/del/{costId}")
    public Object delPaperCost(@PathVariable("costId") Long costId){
        if(costId == null){
            return JsonReturn.SetMsg(10010,"纸张费配置编号不能为空!","");
        }
        if(paperCostService.deletePaperCost(costId) > 0){
            return JsonReturn.SetMsg(0,"删除纸张费配置成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除纸张费配置失败!","");
    }

    /**
     * 获取纸张费配置列表API接口
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping(value = "/list/{pageSize}/{pageNum}")
    public Object listPageCost(@PathVariable("pageSize") int pageSize,@PathVariable("pageNum") int pageNum){
        if (pageSize == 0){
            pageSize = 0;
        }
        if(pageNum == 0){
            pageNum = 1;
        }
       PageInfo pageInfo =  paperCostService.listPagerCost(pageSize,pageNum);
       if(pageInfo != null && pageInfo.getTotal() > 0L){
            return JsonReturn.SetMsg(0,"获取纸张费配置列表成功!",pageInfo);
       }
       return JsonReturn.SetMsg(10011,"获取纸张费配置列表失败!","");
    }

    /**
     * 获取配置过的材质数据API接口
     * @param sellerId
     * @param Status
     * @return
     */
    @GetMapping(value = "/product/texture/{sellerId}/{Status}")
    public Object findProductTextureApi(@PathVariable("sellerId") Long sellerId,@PathVariable("Status") Byte Status){
        if(sellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号不能为空！","");
        }
        if(Status == null){
            return JsonReturn.SetMsg(10010,"状态不能为空!","");
        }
        List<ProductWantTextureBo> productWantTextureBos = paperCostService.findProductTextureData(sellerId,Status);
        if(productWantTextureBos != null && productWantTextureBos.size() > 0){
            return JsonReturn.SetMsg(0,"获取材质数据成功!",productWantTextureBos);
        }
        return JsonReturn.SetMsg(10011,"暂没有材质数据!","");
    }

    /**
     * 获取产品设置所需的材质厚度数据的API接口
     * @param sellerId
     * @param paperTid
     * @return
     */
    @GetMapping(value = "/product/gram/{sellerId}/{paperTid}")
    public Object findProductGramApi(@PathVariable("sellerId") Long sellerId,@PathVariable("paperTid") Long paperTid){
        if(sellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号不能为空!","");
        }
        if(paperTid == null){
            return JsonReturn.SetMsg(10010,"材质编号不能为空!","");
        }
        List<ProductWantGramBo> productWantGramBos = paperCostService.findProductGramData(sellerId,paperTid);
        if(productWantGramBos != null && productWantGramBos.size() > 0){
            return JsonReturn.SetMsg(0,"获取材质的厚度数据成功!",productWantGramBos);
        }
        return JsonReturn.SetMsg(10011,"此材质暂无配置的厚度数据!","");
    }

    /**
     * 联合查询符合条件的纸张与印刷机API接口
     * @param joinSelectBo
     * @param result
     * @return
     */
    @PostMapping(value = "/join/paper/printing")
    public Object joinPaperPrintingApi(@Valid @ModelAttribute JoinSelectBo joinSelectBo,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        List<PaperCostWithBLOBs> paperCosts = paperCostService.findPaperAndPrintingJoinData(joinSelectBo.getSellerId(),joinSelectBo.getTextureId(),joinSelectBo.getGramNums(),joinSelectBo.getPrintingColorId(),joinSelectBo.getLongs(),joinSelectBo.getWidth());
        if(paperCosts != null && paperCosts.size() > 0){
            return JsonReturn.SetMsg(0,"获取纸张配置与印刷机配置成功!",paperCosts);
        }
        return JsonReturn.SetMsg(10011,"暂无符合的纸张配置与印刷机配置数据!","");
    }
}
