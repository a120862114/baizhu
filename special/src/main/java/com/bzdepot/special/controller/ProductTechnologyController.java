package com.bzdepot.special.controller;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.common.util.UserUtil;
import com.bzdepot.special.bo.ProductTechnologyBo;
import com.bzdepot.special.model.ProductTechnology;
import com.bzdepot.special.model.productTechnologySon;
import com.bzdepot.special.service.ProductTechnologyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/product/technology")
public class ProductTechnologyController {

    private final static Logger loger = LoggerFactory.getLogger(ProductTechnologyController.class);

    @Autowired
    private ProductTechnologyService productTechnologyService;

    /**
     * 更新产品配置的工艺相关接口API接口
     * @param productTechnologyBo
     * @param result
     * @return
     */
    @PostMapping(value = "/update")
    public Object updateProductTechnologyApi(@Valid @ModelAttribute ProductTechnologyBo productTechnologyBo, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(productTechnologyService.updateProductTechnologyAll(productTechnologyBo) > 0){
            return JsonReturn.SetMsg(0,"更新产品配置的工艺相关数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新产品配置的工艺相关数据失败!","");
    }

    /**
     * 设置工艺的相关数据API接口
     * @param productTechnology
     * @param result
     * @return
     */
    @PostMapping(value = "/set/data")
    public Object setDataProductTechnologyApi(@Valid @ModelAttribute ProductTechnology productTechnology,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(productTechnologyService.updateProductTechnology(productTechnology) > 0){
            return JsonReturn.SetMsg(0,"设置工艺相关数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"设置工艺相关数据失败!","");
    }

    /**
     * 删除产品配置的工艺相关数据API接口
     * @param sellerId
     * @param pconfigId
     * @param technologyId
     * @return
     */
    @GetMapping(value = "/del/{sellerId}/{pconfigId}/{technologyId}")
    public Object deleteProductTechnologyApi(@PathVariable("sellerId") Long sellerId,@PathVariable("pconfigId") Long pconfigId,@PathVariable("technologyId") Long technologyId){
        if(sellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号不能为空!","");
        }
        if(pconfigId == null){
            return JsonReturn.SetMsg(10010,"产品分类编号不能为空!","");
        }
        if(technologyId == null){
            return JsonReturn.SetMsg(10010,"工艺编号不能为空!","");
        }
        if(productTechnologyService.deleteProductTechnologyByMoreCondition(sellerId,pconfigId,technologyId) > 0){
            return JsonReturn.SetMsg(0,"删除产品配置的工艺相关数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除产品配置的工艺相关数据失败!","");
    }

    /**
     * 获取列表API
     * @param sellerId
     * @param pconfigId
     * @return
     */
    @GetMapping(value = "/list/{sellerId}/{pconfigId}")
    public Object listProductTechnologyApi(@PathVariable("sellerId") Long sellerId,@PathVariable("pconfigId") Long pconfigId){
        if(sellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号不能为空!","");
        }
        if(pconfigId == null){
            return JsonReturn.SetMsg(10010,"产品分类编号不能为空!","");
        }

        List<ProductTechnology> productTechnologies = productTechnologyService.findList(sellerId,pconfigId);

        if(productTechnologies != null && productTechnologies.size() > 0){
            return JsonReturn.SetMsg(0,"获取产品配置的工艺数据成功!",productTechnologies);
        }
        return JsonReturn.SetMsg(10011,"此产品分类下暂无工艺映射数据!","");
    }

    /**
     * 更新设置默认工艺选择API接口
     * @param productTechnology
     * @param result
     * @return
     */
    @PostMapping(value = "/set/default")
    public Object setDefaultAttrApi(@Valid @ModelAttribute ProductTechnology productTechnology,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        System.out.println(productTechnology.toString());
        if(productTechnologyService.updateProductTechnology(productTechnology) > 0){
            return JsonReturn.SetMsg(0,"设置默认工艺选择成功!","");
        }
        return JsonReturn.SetMsg(10011,"设置默认工艺选择失败!","");
    }

    /**
     * 获取工艺选择列表的API接口
     * @param sellerId
     * @param pconfigId
     * @param technologyId
     * @return
     */
    @GetMapping(value = "/attr/list/{sellerId}/{pconfigId}/{technologyId}")
    public Object getAttrListApi(@PathVariable("sellerId") Long sellerId,@PathVariable("pconfigId") Long pconfigId,@PathVariable("technologyId") Long technologyId){
        if(sellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号不能为空!","");
        }
        if(pconfigId == null){
            return JsonReturn.SetMsg(10010,"产品分类编号不能为空!","");
        }
        if(technologyId == null){
            return JsonReturn.SetMsg(10010,"工艺分类编号不能为空!","");
        }
        List<productTechnologySon> productTechnologySons = productTechnologyService.findAttrList(sellerId,pconfigId,technologyId);
        if(productTechnologySons != null && productTechnologySons.size() > 0){
            return JsonReturn.SetMsg(0,"获取工艺选择列表成功!",productTechnologySons);
        }
        return JsonReturn.SetMsg(10011,"暂无工艺选择数据!","");
    }

    /**
     * 设置工艺子属性的相关状态的API接口
     * @param productTechnologySonPostData
     * @param result
     * @return
     */
    @PostMapping(value = "/set/attr/lock/input")
    public Object setAttrLockOrInputApi(@Valid @ModelAttribute productTechnologySon productTechnologySonPostData,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            loger.error(ErrorCode+":"+ErrorMsg);
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(productTechnologySonPostData.getClassId() == null){
            return JsonReturn.SetMsg(10010,"产品分类编号不能为空!","");
        }
        if(productTechnologySonPostData.getSellerId() == null){
            productTechnologySonPostData.setSellerId(UserUtil.getId());
        }
        if(productTechnologySonPostData.getTechnologyId() == null){
            return JsonReturn.SetMsg(10010,"工艺分类编号不能为空!","");
        }
        if(productTechnologySonPostData.getAttrId() == null){
            return JsonReturn.SetMsg(10010,"工艺选择属性编号不能为空!","");
        }
        if(productTechnologySonPostData.getIsLock() == null && productTechnologySonPostData.getIsInput() == null){
            return JsonReturn.SetMsg(10010,"请至少设置一种状态，锁 或 输入框 状态!","");
        }
        if(productTechnologyService.setLockOrInputStatus(productTechnologySonPostData) > 0){
            return JsonReturn.SetMsg(0,"设置状态成功!","");
        }
        return JsonReturn.SetMsg(10011,"设置状态失败!","");
    }
}
