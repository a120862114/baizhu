package com.bzdepot.transport.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.transport.model.BlockGroupData;
import com.bzdepot.transport.model.BlockPrice;
import com.bzdepot.transport.model.RegionBlock;
import com.bzdepot.transport.model.RegionDefault;
import com.bzdepot.transport.service.BlockService;
import com.bzdepot.transport.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/block")
public class BlockController {
    private final static Logger loger = LoggerFactory.getLogger(BlockController.class);
    @Autowired
    private BlockService blockService;

    /**
     * 更新运费配置API
     * aram blockVo
     * @param result
     * @param request
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateBlock(@Valid @ModelAttribute BlockVo blockVo, BindingResult result, HttpServletRequest request){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                loger.error(result.getFieldError().getField()+"参数传递的类型错误!");
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }

        if(request.getParameter("city") == null){
            return JsonReturn.SetMsg(10010,"运费城市配置信息不能为空!","");
        }

        if(request.getParameter("price") == null){
            return JsonReturn.SetMsg(10010,"运费价格配置信息不能为空!","");
        }
        System.out.println(request.getParameter("city"));
        List<BlockCityVo> blockCityVos = null;
        try {
            blockCityVos =  JSON.parseObject(request.getParameter("city"),new TypeReference<List<BlockCityVo>>(){});
        }catch (Exception e){
            loger.error(e.getMessage()+"传递参数数据:"+request.getParameter("city"));
            return JsonReturn.SetMsg(10010,"城市数组数据格式解析失败!","");
        }

        if(blockCityVos.size() == 0){
            return JsonReturn.SetMsg(10010,"请填写完整的区块城市配置信息!","");
        }
        List<BlockPriceVo> blockPriceVos = null;
        try {
            blockPriceVos =  JSON.parseObject(request.getParameter("price"),new TypeReference<List<BlockPriceVo>>(){});
        }catch (Exception e){
            loger.error(e.getMessage()+"传递参数数据:"+request.getParameter("price"));
            return JsonReturn.SetMsg(10010,"价格配置数组数据格式解析失败!","");
        }

        if(blockPriceVos.size() == 0){
            return JsonReturn.SetMsg(10010,"请填写完整的区块价格配置信息!","");
        }

        int Ok = blockService.updateBlockData(blockVo,blockCityVos,blockPriceVos);

        if(Ok > 0){
            return JsonReturn.SetMsg(0,"更新运费配置信息成功!","");
        }

        return JsonReturn.SetMsg(10011,"更新运费配置信息失败!","");
    }

    /**
     * 删除价格配置数据API
     * @param id
     * @return
     */
    @GetMapping(value = "/del/price/{id}")
    public Object delPriceById(@PathVariable("id") Long id){
        int Ok = blockService.delBlockPriceById(id);
        if(Ok > 0){
            return JsonReturn.SetMsg(0,"删除价格配置数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除价格配置数据失败!","");
    }

    /**
     *
     * 获取运输配置数据
     * @param joinDataVo
     * @param result
     * @return
     */
    @PostMapping(value = "/select/all")
    public Object SelectAllDataForJoin(@Valid @ModelAttribute JoinDataVo joinDataVo, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                loger.error(result.getFieldError().getField()+"参数传递的类型错误!");
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }

        BlockGroupData blockGroupData = new BlockGroupData();
        blockGroupData.setSellerId(joinDataVo.getSellerId());
        blockGroupData.setCompanyId(joinDataVo.getCompanyId());
        blockGroupData.setRegionId(joinDataVo.getRegionId());
        List<BlockGroupData> ReData = blockService.findAllBySidCidRid(blockGroupData);
        if(ReData.size() > 0){
            return JsonReturn.SetMsg(0,"获取运输配置数据成功!",ReData);
        }
        return JsonReturn.SetMsg(10011,"获取运输配置数据失败!","");
    }

    @PostMapping(value = "/update/default")
    public Object updateDefaultRegionApi(@Valid @ModelAttribute RegionDefault regionDefault,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                loger.error(result.getFieldError().getField()+"参数传递的类型错误!");
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        int Ok = blockService.updateRegionDefault(regionDefault);
        if(Ok > 0){
            return JsonReturn.SetMsg(0,"设置默认区域成功!","");
        }
        return JsonReturn.SetMsg(10011,"设置默认区域失败!","");
    }

    /**
     * 获取快递下默认区域的城市数据
     * @param defaultCityFindVo
     * @param result
     * @return
     */
    @PostMapping(value = "/default/citys")
    public Object getDefaultCitys(@Valid @ModelAttribute DefaultCityFindVo defaultCityFindVo, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                loger.error(result.getFieldError().getField()+"参数传递的类型错误!");
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
       List<RegionBlock> ResData = blockService.getCompanyDefaultCity(defaultCityFindVo.getCompanyId(),defaultCityFindVo.getSellerId());
        if(ResData.size() > 0){
            return JsonReturn.SetMsg(0,"获取快递默认区域的城市数据成功!",ResData);
        }
        return JsonReturn.SetMsg(10011,"此商家暂未配置城市区数据!","");
    }

    /**
     * 获取快递城市价格数据
     * @param cityConfigVo
     * @param result
     * @return
     */
    @PostMapping(value = "/city/config")
    public Object findCityConfig(@Valid @ModelAttribute CityConfigVo cityConfigVo,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                loger.error(result.getFieldError().getField()+"参数传递的类型错误!");
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        System.out.println(cityConfigVo.toString());
        BlockPrice blockPrice = blockService.findDefaultCityConfig(cityConfigVo.getCompanyId(),cityConfigVo.getSellerId(),cityConfigVo.getCityId());

        if(blockPrice != null){
            System.out.println(blockPrice.toString());
            BigDecimal Money = new BigDecimal(0);
            //判断是否需要拆包
            if(cityConfigVo.getTotalWeight().compareTo(blockPrice.getWeightEnd().subtract(new BigDecimal(1))) == 1){
                System.out.println("需要拆包");
                BigDecimal[] Bags = blockService.Dismantling(blockPrice.getWeightStart(),blockPrice.getWeightEnd(),cityConfigVo.getTotalWeight());
                if(Bags != null){
                    Money = blockService.CalculatingPrice(Bags,blockPrice);
                }
            }else{
                System.out.println("不需要拆包");
                Money = blockService.CalculatingPriceNo(cityConfigVo.getTotalWeight(),blockPrice);
            }
            Map<String,Object> JsonData = new HashMap<String, Object>();
            JsonData.put("money",Money);
            JsonData.put("startMoney",blockPrice.getStartPrice());
            return JsonReturn.SetMsg(0,"获取快递城市价格配置数据成功!",JsonData);
        }
        return JsonReturn.SetMsg(10011,"获取快递城市价格配置数据失败!","");
    }

    /**
     * 更新发货状态API接口
     * @param rBlockId
     * @param isSuspend
     * @return
     */
    @GetMapping(value = "/update/citys/{rBlockId}/{isSuspend}")
    public Object updateCitysIsSuspend(@PathVariable("rBlockId") Long rBlockId,@PathVariable("isSuspend") Byte isSuspend){
        if(rBlockId == null){
            return JsonReturn.SetMsg(10010,"区域内城市的单组编号不能为空!","");
        }
        if(isSuspend == null){
            return JsonReturn.SetMsg(10010,"暂停状体不能为空!","");
        }
        if(blockService.updateIsSuspend(rBlockId,isSuspend) > 0){
            return JsonReturn.SetMsg(0,"更新暂停发货状态成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新暂停发货状态失败!","");
    }
}
