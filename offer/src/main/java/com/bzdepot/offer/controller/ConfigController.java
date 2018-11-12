package com.bzdepot.offer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.offer.model.*;
import com.bzdepot.offer.service.OfferService;
import com.bzdepot.offer.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 固定报价配置控制器
 *
 */
@RestController
@RequestMapping(value = "/config/set/")
public class ConfigController {

    @Autowired
    private OfferService offerService;

    /**
     * 添加报价配置信息
     * @param offer
     * @param result
     * @param request
     * @return
     */
    @PostMapping(value = "/add")
    public Object addOffer(@Valid @ModelAttribute Offer offer,BindingResult result, HttpServletRequest request){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(request.getParameter("online") == null){
            return JsonReturn.SetMsg(10010,"请天填写完整的报价配置信息!","");
        }
        if(request.getParameter("imgdata") == null){
            return JsonReturn.SetMsg(10010,"请上传产品图片!","");
        }
        List<OfferGroupVo> offerGroup =  JSON.parseObject(request.getParameter("online"),new TypeReference<List<OfferGroupVo>>(){});
        if(offerGroup.size() == 0){
            return JsonReturn.SetMsg(10010,"请填写完整的报价配置信息!","");
        }
        Boolean Ok = offerService.addOfferData(offer,request.getParameter("imgdata").toString(),offerGroup);
        if(Ok == true) {
            return JsonReturn.SetMsg(0, "添加报价配置成功!", "");
        }
        return JsonReturn.SetMsg(10011,"添加报价配置失败!","");
    }

    /**
     * 获取图片列表
     * @param findImgVo
     * @param result
     * @return
     */
    @PostMapping(value = "/img/list")
    public Object imgList(@Valid @ModelAttribute FindImgVo findImgVo, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }

       List<ProductImgVo> ImgList = offerService.getOfferImg(findImgVo.getSeller_id(),findImgVo.getClass_id(),findImgVo.getTexture_id());
        if(ImgList.size() > 0){
            return JsonReturn.SetMsg(0,"获取产品图片成功!",ImgList);
        }
        return JsonReturn.SetMsg(10011,"获取产品图片失败!","");
    }

    /**
     *  获取报价配置的厚度与重量
     * @param findImgVo
     * @param result
     * @return
     */
    @PostMapping(value = "/offer/data")
    public Object offerListForField(@Valid @ModelAttribute FindImgVo findImgVo,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        List<Offer> OfferData = offerService.getOfferListForField(findImgVo.getSeller_id(),findImgVo.getClass_id(),findImgVo.getTexture_id(),findImgVo.getTypes());
        System.out.println(OfferData);
        if(OfferData.size() > 0){
            return JsonReturn.SetMsg(0,"获取配置成功!",OfferData);
        }
        return JsonReturn.SetMsg(10011,"获取配置失败!","");
    }
    /**
     *  获取报价配置的厚度与重量
     * @param findImgVo
     * @param result
     * @return
     */
    @PostMapping(value = "/offer/data/all/type")
    public Object offerListForFieldAllType(@Valid @ModelAttribute FindImgVo findImgVo,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        List<Offer> OfferData = offerService.getOfferListForFieldAll(findImgVo.getSeller_id(),findImgVo.getClass_id(),findImgVo.getTexture_id());
        System.out.println(OfferData);
        if(OfferData.size() > 0){
            return JsonReturn.SetMsg(0,"获取配置成功!",OfferData);
        }
        return JsonReturn.SetMsg(10011,"获取配置失败!","");
    }
    /**
     *  获取报价配置组详情
     * @param offerGroupFindVo
     * @param result
     * @return
     */
    @PostMapping(value = "/group/list")
    public Object getGroupList(@Valid @ModelAttribute OfferGroupFindVo offerGroupFindVo, BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }

       List<OfferGroup> GroupData = offerService.getGroupListDetailAttr(offerGroupFindVo.getOffer_id());
        if(GroupData.size() > 0){
            return JsonReturn.SetMsg(0,"获取配置组详细数据成功!",GroupData);
        }
        return JsonReturn.SetMsg(10011,"获取配置组详细数据失败!","");
    }

    /**
     * 删除 整个一行的全部分组数据（包含子数据）
     * @param request
     * @return
     */
    @PostMapping(value = "/del/group")
    public Object delGroup(HttpServletRequest request){
        Long GroupId = null;
        try {
            String GroupStr = request.getParameter("group_id");
            if(GroupStr != null){
                GroupId = Long.parseLong(GroupStr);
            }
        }catch (Exception e){
            return JsonReturn.SetMsg(10010,"请传递正确的分组编号!","");
        }
        if(GroupId == null){
            return JsonReturn.SetMsg(10010,"分组编号不能为空!","");
        }
        int Ok = offerService.delGroupAndSonData(GroupId);
        if(Ok > 0){
            return JsonReturn.SetMsg(0,"删除分组及子数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除分组及子数据失败!","");
    }

    /**
     *  修改分组数据
     * @param offerGroup
     * @param result
     * @return
     */
    @PostMapping(value = "/edit/group")
    public Object editGroup(@Valid @ModelAttribute OfferGroup offerGroup,BindingResult result){
        if(result.hasErrors()){
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if(ErrorCode.equals("typeMismatch")){
                return JsonReturn.SetMsg(10010,result.getFieldError().getField()+"参数传递的类型错误!","");
            }
            return JsonReturn.SetMsg(10010,ErrorMsg,"");
        }
        if(offerGroup.getId() == null){
            return JsonReturn.SetMsg(10010,"分组编号不能为空!","");
        }
       int Ok = offerService.editGroup(offerGroup);
        if(Ok > 0){
            return JsonReturn.SetMsg(0,"修改分组数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"修改分组数据失败!","");
    }

    /**
     * 修改属性数据
     * @param editAttrVo
     * @param result
     * @return
     */
    @PostMapping(value = "/edit/attr")
    public Object editAttr(@Valid @ModelAttribute EditAttrVo editAttrVo,BindingResult result) {
        if (result.hasErrors()) {
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if (ErrorCode.equals("typeMismatch")) {
                return JsonReturn.SetMsg(10010, result.getFieldError().getField() + "参数传递的类型错误!", "");
            }
            return JsonReturn.SetMsg(10010, ErrorMsg, "");
        }
        if (editAttrVo.getIds().length == 0) {
            return JsonReturn.SetMsg(10010, "属性编号不能为空!", "");
        }
        int Ok = offerService.editAttr(editAttrVo);
        if (Ok > 0) {
            return JsonReturn.SetMsg(0, "修改属性数据成功!", "");
        }
        return JsonReturn.SetMsg(10011, "修改属性数据失败!", "");
    }

    /**
     * 修改数量数据
     * @param editDetailVo
     * @param result
     * @return
     */
    @PostMapping(value = "/edit/detail")
    public Object editDetail(@Valid @ModelAttribute EditDetailVo editDetailVo,BindingResult result){
        if (result.hasErrors()) {
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if (ErrorCode.equals("typeMismatch")) {
                return JsonReturn.SetMsg(10010, result.getFieldError().getField() + "参数传递的类型错误!", "");
            }
            return JsonReturn.SetMsg(10010, ErrorMsg, "");
        }
        if (editDetailVo.getIds().length == 0) {
            return JsonReturn.SetMsg(10010, "数量编号不能为空!", "");
        }
        int Ok = offerService.editDetail(editDetailVo);
        if(Ok > 0){
            return JsonReturn.SetMsg(0,"修改数量数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除数量数据失败","");
    }

    /**
     * 删除一列的属性数据
     * @param longIdsVo
     * @param result
     * @return
     */
    @PostMapping(value = "/del/attr")
    public Object delAttr(@Valid @ModelAttribute LongIdsVo longIdsVo,BindingResult result){
        if (result.hasErrors()) {
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if (ErrorCode.equals("typeMismatch")) {
                return JsonReturn.SetMsg(10010, result.getFieldError().getField() + "参数传递的类型错误!", "");
            }
            return JsonReturn.SetMsg(10010, ErrorMsg, "");
        }
        if(longIdsVo.getIds().length == 0){
            return JsonReturn.SetMsg(10010,"没需要删除的数据!","");
        }
        int Ok = offerService.delAttr(longIdsVo.getIds());
        if(Ok > 0){
            return JsonReturn.SetMsg(0,"删除属性数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除属性失败!","");
    }

    /**
     * 删除一列的数量价格数据
     * @param longIdsVo
     * @param result
     * @return
     */
    @PostMapping(value = "/del/detail")
    public Object delDetail(@Valid @ModelAttribute LongIdsVo longIdsVo,BindingResult result){
        if (result.hasErrors()) {
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if (ErrorCode.equals("typeMismatch")) {
                return JsonReturn.SetMsg(10010, result.getFieldError().getField() + "参数传递的类型错误!", "");
            }
            return JsonReturn.SetMsg(10010, ErrorMsg, "");
        }
        if(longIdsVo.getIds().length == 0){
            return JsonReturn.SetMsg(10010,"没需要删除的数据!","");
        }
        int Ok = offerService.delDetail(longIdsVo.getIds());
        if(Ok > 0){
            return JsonReturn.SetMsg(0,"删除数量价格数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除除数量价格失败!","");
    }

    /**
     * 修改主配置与产品图片数据
     * @param offer
     * @param request
     * @param result
     * @return
     */
    @PostMapping(value = "/edit/offerimg")
    public Object editOfferAndImg(@Valid @ModelAttribute Offer offer,BindingResult result,HttpServletRequest request){
        if (result.hasErrors()) {
            String ErrorMsg = result.getFieldError().getDefaultMessage();
            String ErrorCode = result.getFieldError().getCode();
            if (ErrorCode.equals("typeMismatch")) {
                return JsonReturn.SetMsg(10010, result.getFieldError().getField() + "参数传递的类型错误!", "");
            }
            return JsonReturn.SetMsg(10010, ErrorMsg, "");
        }

        Long ImgId = null;
        if(request.getParameter("image_id") != null){
            try {
                ImgId = Long.parseLong(request.getParameter("image_id"));
            }catch (NumberFormatException e){
                return JsonReturn.SetMsg(10010,"产品图片编号获取失败!","");
            }
        }
        if(request.getParameter("imgdata") == null){
            return JsonReturn.SetMsg(10010,"图片修改内容不能为空!","");
        }

        if(request.getParameter("online") == null){
            return JsonReturn.SetMsg(10010,"请天填写完整的报价配置信息!","");
        }
        List<OfferGroupVo> offerGroup =  JSON.parseObject(request.getParameter("online"),new TypeReference<List<OfferGroupVo>>(){});
        if(offerGroup.size() == 0){
            return JsonReturn.SetMsg(10010,"请填写完整的报价配置信息!","");
        }

        //获取报价配置子配置
        if(request.getParameter("sonData") == null){
            return JsonReturn.SetMsg(10010,"请填写报价配置子配置!","");
        }
        OfferSon offerSon = JSON.parseObject(request.getParameter("sonData"),new TypeReference<OfferSon>(){});
        if(offerSon == null){
            return JsonReturn.SetMsg(10010,"解析报价配置的子配置失败!","");
        }
        //获取报价配置快递子配置
        List<OfferSonExpress> offerSonExpresses = null;
        if(offer.getTypes().equals(new Byte("0"))){
            if(request.getParameter("sonExpressData") != null){
                offerSonExpresses =  JSON.parseObject(request.getParameter("sonExpressData"),new TypeReference<List<OfferSonExpress>>(){});
                if(offerSonExpresses.size() == 0){
                    return JsonReturn.SetMsg(10010,"解析报价配置的快递省份配置失败!","");
                }
            }
        }

        ProductImg productImg = new ProductImg();
        productImg.setId(ImgId);
        productImg.setImgIds(request.getParameter("imgdata"));
       int Ok = offerService.editOfferAndImg(offer,productImg,offerGroup,offerSon,offerSonExpresses);
        if(Ok > 0){
            return JsonReturn.SetMsg(0,"更新主配置与图片数据成功!","");
        }
        return JsonReturn.SetMsg(10011,"更新主配置与图片数据失败!","");
    }

    /**
     * 删除报价配置的子配置的快递配置API接口
     * @param id
     * @return
     */
    @GetMapping(value = "/del/son/express/{id}")
    public Object delSonExpress(@PathVariable("id") Long id){
        if(id == null){
            return JsonReturn.SetMsg(10010,"报价配置子配置的快递子配置编号不能为空!","");
        }
        if(offerService.deleteOfferSonExpress(id) > 0){
            return JsonReturn.SetMsg(0,"删除报价配置的子配置的快递配置成功!","");
        }
        return JsonReturn.SetMsg(10011,"删除报价配置的子配置的快递配置失败!","");
    }

    /**
     * 获取报价材质子配置
     * @param sellerId
     * @param classId
     * @param textureId
     * @return
     */
    @GetMapping(value = "/get/offer/son/{sellerId}/{classId}/{textureId}")
    public Object getSonDataOffer(@PathVariable("sellerId") Long sellerId,@PathVariable("classId") Long classId,@PathVariable("textureId") Long textureId){
        if(sellerId == null){
            return JsonReturn.SetMsg(10010,"商家编号不能为空!","");
        }
        if(classId == null){
            return JsonReturn.SetMsg(10010,"报价分类编号不能为空!","");
        }
        if(textureId == null){
            return JsonReturn.SetMsg(10010,"报价材质编号不能为空!","");
        }
        OfferSon offerSon = offerService.getOfferSonData(sellerId,classId,textureId);
        if(offerSon != null){
            return JsonReturn.SetMsg(0,"获取报价子配置成功!",offerSon);
        }
        return JsonReturn.SetMsg(10011,"获取报价子配置失败!","");
    }
}
