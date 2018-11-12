package com.bzdepot.offer.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.common.util.UserUtil;
import com.bzdepot.offer.fegin.ExpressMoneyService;
import com.bzdepot.offer.fegin.ProfitConfigService;
import com.bzdepot.offer.mapper.*;
import com.bzdepot.offer.model.*;
import com.bzdepot.offer.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.*;

@Service
public class OfferService {
    private final static Logger loger = LoggerFactory.getLogger(OfferService.class);
    @Autowired
    private OfferMapper offerMapper;

    @Autowired
    private OfferGroupMapper offerGroupMapper;

    @Autowired
    private OfferAttrMapper offerAttrMapper;

    @Autowired
    private OfferDetailMapper offerDetailMapper;

    @Autowired
    private ProductImgMapper productImgMapper;

    @Autowired
    private ProfitConfigService profitConfigService;

    @Autowired
    private ExpressMoneyService expressMoneyService;

    @Autowired
    private OfferSonMapper offerSonMapper;

    @Autowired
    private OfferSonExpressMapper offerSonExpressMapper;

    /**
     * 获取报价子配置
     * @param sellerId
     * @param classId
     * @param textureId
     * @return
     */
    public OfferSon getOfferSonData(Long sellerId,Long classId,Long textureId){
        return offerSonMapper.selectByClassIdAndTextureIdAndSellerId(sellerId,classId,textureId);
    }

    /**
     * 此方法已经作废
     * @param offer
     * @param imgIds
     * @param offerGroupVos
     * @return
     */
    @Transactional
    public Boolean addOfferData(Offer offer, String imgIds, List<OfferGroupVo> offerGroupVos){
        int GroupStatus = 0;
        try {

            offer.setCreateTime(new Date().getTime());
            offer.setUpdateTime(new Date().getTime());
            Byte StatusValue = 1;
            offer.setStatus(StatusValue);
            GroupStatus = offerMapper.insertSelective(offer);
            if (GroupStatus < 1) {
                return false;
            }
            Long OfferId = offer.getId();
            Long SellerId = offer.getSellerId();

            //添加产品图片
            ProductImg imgdata = new ProductImg();
            imgdata.setClassId(offer.getClassId());
            imgdata.setImgIds(imgIds);
            imgdata.setSellerId(SellerId);
            imgdata.setTextureId(offer.getTextureId());
            GroupStatus = productImgMapper.insertSelective(imgdata);
            if (GroupStatus < 1) {
                throw new RuntimeException("添加失败!");
            }

            //添加分组信息
            if(offerGroupVos.size() == 0){
                throw new RuntimeException("分组数据为空!");
            }
            for (int i = 0; i < offerGroupVos.size(); i++) {
                OfferGroupVo group = offerGroupVos.get(i);
                OfferGroup groupData = new OfferGroup();
                groupData.setOfferId(OfferId);
                groupData.setSendSellerId(SellerId);
                groupData.setSmoney(group.getSmoney());
                groupData.setIsSend(group.getIsSend());
                groupData.setSendSellerId(group.getSendSellerId());
                //添加分组数据
                GroupStatus = offerGroupMapper.insertSelective(groupData);
                if (GroupStatus < 1) {
                    break;

                }
                Long GoupId = groupData.getId();
                if (GoupId == null) {
                    GroupStatus = 0;
                    break;
                }
                if(group.getAttrarray().size() == 0){
                    throw new RuntimeException("属性数据为空!");
                }
                //开始准备添加报价配置属性
                for (int a = 0; a < group.getAttrarray().size(); a++) {
                    OfferAttrVo attr = group.getAttrarray().get(a); //获取属性数组
                    OfferAttr attrData = new OfferAttr();
                    attrData.setOfferId(OfferId);
                    attrData.setOfferGroupId(GoupId);
                    attrData.setAttrKey(attr.getAttrKey());
                    attrData.setAttrValue(attr.getAttrValue());
                    //添加属性
                    GroupStatus = offerAttrMapper.insertSelective(attrData);
                    if (GroupStatus < 1) {
                        break;
                    }
                }
                if(group.getDetailarray().size() == 0){
                    throw new RuntimeException("数量数据为空!");
                }
                //开始准备添加价格数量配置
                for (int b = 0; b < group.getDetailarray().size(); b++) {
                    OfferDetailVo detail = group.getDetailarray().get(b); //获取价格配置信息
                    OfferDetail detailData = new OfferDetail();
                    detailData.setOfferId(OfferId);
                    detailData.setOfferGroupId(GoupId);
                    detailData.setNums(detail.getNums());
                    detailData.setXmoney(detail.getXmoney());
                    //添加数量与价格
                    GroupStatus = offerDetailMapper.insertSelective(detailData);
                    if (GroupStatus < 1) {
                        break;
                    }
                }

            }
            //如果有数据没添加成功那么开启异常
            if(GroupStatus < 1){
                throw new RuntimeException("添加失败!");
            }
        }catch (Exception e){
            GroupStatus = 0;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        if(GroupStatus > 0){
            return true;
        }

        return false;
    }

    /**
     *  获取配置的产品图片
     * @param SellerId
     * @param ClassId
     * @param TextureId
     * @return
     */
    public List<ProductImgVo> getOfferImg(Long SellerId, Long ClassId, Long TextureId){
        return  productImgMapper.getProductImgList(SellerId,ClassId,TextureId);
    }

    /**
     *  获取报价配置的厚度与重量
     * @param SellerId
     * @param ClassId
     * @param TextureId
     * @return
     */
    public List<Offer> getOfferListForField(Long SellerId, Long ClassId, Long TextureId,Byte types){
        return offerMapper.selectBySidCidTid(SellerId,ClassId,TextureId,types);
    }
    /**
     *  获取报价配置所有类型的厚度与重量
     * @param SellerId
     * @param ClassId
     * @param TextureId
     * @return
     */
    public List<Offer> getOfferListForFieldAll(Long SellerId, Long ClassId, Long TextureId){
        return offerMapper.selectBySidCidTidAll(SellerId,ClassId,TextureId);
    }
    /**
     *  通过关联查询获取配置组的详细数据
     * @param OfferId
     * @return
     */
    public List<OfferGroup> getGroupListDetailAttr(Long OfferId){
        return offerGroupMapper.selectJoinData(OfferId);
    }

    /**
     * 修改分组
     * @param offerGroup
     * @return
     */
    public int editGroup(OfferGroup offerGroup){
        return offerGroupMapper.updateByPrimaryKeySelective(offerGroup);
    }

    /**
     * 修改属性数据
     * @param editAttrVo
     * @return
     */
    public int editAttr(EditAttrVo editAttrVo){
        int Status = 0;
        try {
            Long[] Ids = editAttrVo.getIds();
            for(int i = 0; i < Ids.length; i++){
                OfferAttr offerAttr = new OfferAttr();
                offerAttr.setId(Ids[i]);
                offerAttr.setAttrValue(editAttrVo.getAttrValue());
                offerAttr.setAttrKey(editAttrVo.getAttrKey());
                Status = offerAttrMapper.updateByPrimaryKeySelective(offerAttr);
                if(Status == 0){
                    loger.error("修改属性数据失败!"+offerAttr.toString());
                    break;
                }
            }
            if(Status == 0){
                throw new RuntimeException("修改失败");
            }
        }catch (Exception e){
            Status = 0;
            loger.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Status;
    }

    /**
     * 修改数量数据
     * @param editDetailVo
     * @return
     */
    public int editDetail(EditDetailVo editDetailVo){
        int Status = 0;
        try {
            Long[] Ids = editDetailVo.getIds();
            for(int i = 0; i < Ids.length; i++){
                OfferDetail offerDetail = new OfferDetail();
                offerDetail.setId(Ids[i]);
                offerDetail.setNums(editDetailVo.getNums());
                offerDetail.setXmoney(editDetailVo.getXmoney());
                Status = offerDetailMapper.updateByPrimaryKeySelective(offerDetail);
                if(Status == 0){
                    loger.error("修改数量配置失败 :"+offerDetail.toString());
                    break;
                }
            }
            if(Status == 0){
                throw new RuntimeException("修改失败");
            }
        }catch (Exception e){
            Status = 0;
            loger.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Status;
    }

    /**
     * 删除分组正行的数据
     * @param GroupId
     * @return
     */
    @Transactional
    public int delGroupAndSonData(Long GroupId){
        int Status = 0;
        try{
            Status = offerGroupMapper.deleteByPrimaryKey(GroupId);
            if(Status < 1){
                throw new RuntimeException("删除offergroup失败");
            }
            Status = offerAttrMapper.delbyGroupId(GroupId);
            if(Status < 1){
                throw new RuntimeException("删除offerattr失败");
            }
            Status = offerDetailMapper.delByGroupId(GroupId);
            if(Status < 1){
                throw new RuntimeException("删除offerdetail失败");
            }
        }catch (Exception e){
            loger.error(e.getMessage());
            Status = 0;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Status;
    }

    /**
     * 删除整列的属性数据
     * @param AttrIds
     * @return
     */
    @Transactional
    public int delAttr(Long[] AttrIds){
        int Status = 0;
        try {
            for (int i = 0; i < AttrIds.length; i++){
                Status = offerAttrMapper.deleteByPrimaryKey(AttrIds[i]);
                if(Status == 0){
                    break;
                }
            }
            if(Status == 0){
                throw new RuntimeException("删除整列属性失败");
            }
        }catch (Exception e){
            loger.error(e.getMessage());
            Status = 0;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Status;
    }

    /**
     * 删除整列的数据价格数据
     * @param DetailIds
     * @return
     */
    @Transactional
    public int delDetail(Long[] DetailIds){
        int Status = 0;
        try {
            for (int i = 0; i < DetailIds.length; i++){
                Status = offerDetailMapper.deleteByPrimaryKey(DetailIds[i]);
                if(Status == 0){
                    break;
                }
            }
            if(Status == 0){
                throw new RuntimeException("删除整列价格失败");
            }
        }catch (Exception e){
            loger.error(e.getMessage());
            Status = 0;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Status;
    }

    /**
     * 修改报价主配置与产品图片
     * @param offer
     * @param productImg
     * @return
     */
    @Transactional
    public int editOfferAndImg(Offer offer,ProductImg productImg,List<OfferGroupVo> offerGroupVos,OfferSon offerSon,List<OfferSonExpress> offerSonExpresses){
        int GroupStatus = 0;
        try {

            if(offer.getId() == null){
                GroupStatus = offerMapper.insertSelective(offer);
            }else{
                GroupStatus = offerMapper.updateByPrimaryKeySelective(offer);
            }
            if(GroupStatus < 1){
                loger.error("更新主配置失败:"+offer.toString());
                throw new RuntimeException("修改失败");
            }
            Long OfferId = offer.getId();
            Long SellerId = offer.getSellerId();
            if(productImg.getId() == null){
                productImg.setClassId(offer.getClassId());
                productImg.setSellerId(SellerId);
                productImg.setTextureId(offer.getTextureId());
                productImg.setCreateTime(new Date().getTime());
                productImg.setUpdateTime(new Date().getTime());
                GroupStatus = productImgMapper.insertSelective(productImg);
            }else{
                productImg.setUpdateTime(new Date().getTime());
                GroupStatus = productImgMapper.updateByPrimaryKeySelective(productImg);
            }

            if(GroupStatus < 1){
                loger.error("更新产品图失败:"+productImg.toString());
                throw new RuntimeException("修改失败");
            }
            //更新子配置
            offerSon.setClassId(offer.getClassId());
            offerSon.setTextureId(offer.getTextureId());
            offerSon.setSellerId(offer.getSellerId());
            if(offerSon.getId() == null){
                GroupStatus = offerSonMapper.insertSelective(offerSon);
            }else{
                GroupStatus = offerSonMapper.updateByPrimaryKeySelective(offerSon);
            }
            if(GroupStatus < 1){
                loger.error("更新子配置失败");
                throw new RuntimeException("更新子配置失败");
            }
            if(offerSonExpresses != null) {
                //更新子配置的快递配置
                for (int a = 0; a < offerSonExpresses.size(); a++) {
                    offerSonExpresses.get(a).setOfferSonId(offerSon.getId());
                    if (offerSonExpresses.get(a).getId() == null) {
                        GroupStatus = offerSonExpressMapper.insertSelective(offerSonExpresses.get(a));
                    } else {
                        GroupStatus = offerSonExpressMapper.updateByPrimaryKeySelective(offerSonExpresses.get(a));
                    }
                    if (GroupStatus == 0) {
                        break;
                    }
                }
            }
            if(GroupStatus == 0){
                loger.error("更新子配置的快递配置失败");
                throw new RuntimeException("更新子配置的快递配置失败");
            }
            //添加分组信息
            if(offerGroupVos.size() == 0){
                throw new RuntimeException("分组数据为空!");
            }
            for (int i = 0; i < offerGroupVos.size(); i++) {
                OfferGroupVo group = offerGroupVos.get(i);
                OfferGroup groupData = new OfferGroup();
                groupData.setOfferId(OfferId);
                groupData.setSendSellerId(SellerId);
                groupData.setSmoney(group.getSmoney());
                groupData.setIsSend(group.getIsSend());
                groupData.setSendSellerId(group.getSendSellerId());
                //添加分组数据
                if(group.getId() == null){
                    GroupStatus = offerGroupMapper.insertSelective(groupData);
                }else{
                    groupData.setId(group.getId());
                    GroupStatus = offerGroupMapper.updateByPrimaryKeySelective(groupData);
                }

                if (GroupStatus < 1) {
                    loger.error("更新配置分组失败:"+groupData.toString());
                    break;

                }
                Long GoupId = groupData.getId();
                if (GoupId == null) {
                    GroupStatus = 0;
                    loger.error("配置分组ID为null");
                    break;
                }
                if(group.getAttrarray().size() == 0){
                    loger.error("属性数组长度为0");
                    throw new RuntimeException("属性数据为空!");
                }
                //开始准备添加报价配置属性
                for (int a = 0; a < group.getAttrarray().size(); a++) {
                    OfferAttrVo attr = group.getAttrarray().get(a); //获取属性数组
                    OfferAttr attrData = new OfferAttr();
                    attrData.setOfferId(OfferId);
                    attrData.setOfferGroupId(GoupId);
                    attrData.setAttrKey(attr.getAttrKey());
                    attrData.setAttrValue(attr.getAttrValue());
                    //添加属性
                    if(attr.getId() == null){
                        GroupStatus = offerAttrMapper.insertSelective(attrData);
                    }else{
                        attrData.setId(attr.getId());
                        GroupStatus = offerAttrMapper.updateByPrimaryKeySelective(attrData);
                    }

                    if (GroupStatus < 1) {
                        loger.error("属性attr配置更新失败:"+attrData.toString());
                        break;
                    }
                }
                if(group.getDetailarray().size() == 0){
                    loger.error("价格详情detail数组长度为0");
                    throw new RuntimeException("数量数据为空!");
                }
                //开始准备添加价格数量配置
                for (int b = 0; b < group.getDetailarray().size(); b++) {
                    OfferDetailVo detail = group.getDetailarray().get(b); //获取价格配置信息
                    OfferDetail detailData = new OfferDetail();
                    detailData.setOfferId(OfferId);
                    detailData.setOfferGroupId(GoupId);
                    detailData.setNums(detail.getNums());
                    detailData.setXmoney(detail.getXmoney());
                    //添加数量与价格
                    if(detail.getId() == null){
                        GroupStatus = offerDetailMapper.insertSelective(detailData);
                    }else{
                        detailData.setId(detail.getId());
                        GroupStatus = offerDetailMapper.updateByPrimaryKeySelective(detailData);
                    }

                    if (GroupStatus < 1) {
                        loger.error("更新价格配置失败:"+detailData.toString());
                        break;
                    }
                }

            }
            //如果有数据没添加成功那么开启异常
            if(GroupStatus < 1){
                throw new RuntimeException("添加失败!");
            }
        }catch (Exception e){
            loger.error(e.toString());
            loger.error(e.getMessage());
            GroupStatus = 0;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return GroupStatus;
    }

    /**
     * 查询价格
     * @param offerId
     * @param nums
     * @return
     */
    public SelectOfferVo findOfferMoney(Long offerId,Integer nums){
        return offerMapper.findMoney(offerId,nums);
    }

    /**
     * 获取常规与固定报价的配置数据
     * @param selectOfferMoneyVo
     * @return
     */
    public List<SelectOfferVo> findAllOfferAndSonDatas(SelectOfferMoneyVo selectOfferMoneyVo){
        List<SelectOfferVo> selectOfferVos =  offerMapper.findOfferAllData(selectOfferMoneyVo.getSellerId(),selectOfferMoneyVo.getClassId(),selectOfferMoneyVo.getTextureId(),selectOfferMoneyVo.getNums(),selectOfferMoneyVo.getThickness(),selectOfferMoneyVo.getWeight());

        if(selectOfferVos != null){
            return this.screeningData(selectOfferVos,selectOfferMoneyVo);
        }
        return null;
    }

    /**
     * 筛选出符合条件的数据
     * @param selectOfferVos
     * @param selectOfferMoneyVo
     * @return
     */
    public List<SelectOfferVo> screeningData(List<SelectOfferVo> selectOfferVos,SelectOfferMoneyVo selectOfferMoneyVo){

        List<SelectOfferVo> selectOfferVosData = new ArrayList();
        for(int i = 0; i < selectOfferVos.size(); i++){
            List<SelectAttrVo> selectAttrVos = selectOfferVos.get(i).getAttrs(); //赋值长宽高属性对象
            boolean IsTrue = false; //验证器，验证数据是否符合用户选择的长宽高
            BigDecimal TmpLongs = null; //临时验证的长
            BigDecimal TmpWidth = null; //临时验证的宽
            BigDecimal TmpHeight = null; //临时验证的高
            if(selectAttrVos == null){
                continue;
            }
            for(int s = 0; s < selectAttrVos.size(); s++){
                if(selectAttrVos.get(s).getAttr_key().equals("长")){
                    TmpLongs  = selectAttrVos.get(s).getAttr_value();
                }
                if(selectAttrVos.get(s).getAttr_key().equals("宽")){
                    TmpWidth = selectAttrVos.get(s).getAttr_value();
                }
                if(selectAttrVos.get(s).getAttr_key().equals("高")){
                    TmpHeight = selectAttrVos.get(s).getAttr_value();
                }
            }
            //检测数据属性的完整性
            if(TmpLongs == null || TmpWidth == null){
                continue;  //跳出本次循环
            }
            //开始验证数据是否符合然后进行运算
            if(selectOfferMoneyVo.getHeightData() == null){
                if(TmpLongs.compareTo(selectOfferMoneyVo.getLongsData()) == 0 && TmpWidth.compareTo(selectOfferMoneyVo.getWidthData()) == 0){
                    IsTrue = true;
                }
            }else {
                if(TmpHeight == null){
                    continue;  //跳出本次循环
                }
                if(TmpLongs.compareTo(selectOfferMoneyVo.getLongsData()) == 0 && TmpWidth.compareTo(selectOfferMoneyVo.getWidthData()) == 0 && TmpHeight.compareTo(selectOfferMoneyVo.getHeightData()) == 0){
                    IsTrue = true;
                }
            }
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(IsTrue);
            System.out.println(selectOfferVos.get(i));
            System.out.println(selectAttrVos);
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            //验证完成查看验证结果
            if(IsTrue == true){

                //快递费计算
                Map<String,BigDecimal> expressMoney = null;
                if(selectOfferMoneyVo.getIsY().equals(new Byte("1"))) {
                    expressMoney = this.getExpressMoney(selectOfferMoneyVo.getSellerId(), selectOfferMoneyVo.getComanyId(), selectOfferMoneyVo.getCityId(), selectOfferMoneyVo.getNums(), selectOfferMoneyVo.getLongsData(), selectOfferMoneyVo.getWidthData(), selectOfferMoneyVo.getWeight());
                }
                System.out.println(expressMoney);
                //获取发票
                Invoice invoice = null;
                if(selectOfferMoneyVo.getIsF().equals(new Byte("1"))){
                    invoice = this.getInvoice(selectOfferMoneyVo.getSellerId(),selectOfferMoneyVo.getComanyId(),selectOfferMoneyVo.getInTypes(),selectOfferMoneyVo.getLevelId());
                }
                System.out.println(invoice);
                System.out.println("================---------------=========================");
                System.out.println(UserUtil.getLevelId());
                BigDecimal ExpressRate = BigDecimal.valueOf(0);
                if(selectOfferVos.get(i).getOfferSonData() != null && selectOfferVos.get(i).getOfferSonData().getPostageProfit() != null){
                    ExpressRate = selectOfferVos.get(i).getOfferSonData().getPostageProfit(); //邮费最初的利润率
                }
                //常规报价
                if(selectOfferVos.get(i).getTypes().equals(new Byte("0"))){
                    BigDecimal rate = this.getProfitServer(selectOfferVos.get(i).getOffer_id(),selectOfferVos.get(i).getXmoney());

                    if(rate != null){
                        selectOfferVos.get(i).setProfitRate(rate);
                        BigDecimal LastRateZh = rate.divide(BigDecimal.valueOf(100)); //利润转换单位
                        selectOfferVos.get(i).setProfitMoney(LastRateZh.multiply(selectOfferVos.get(i).getXmoney())); //设置利润
                    }
                    //检测邮费的特殊利润配置
                    if(selectOfferVos.get(i).getOfferSonData() != null && selectOfferVos.get(i).getOfferSonData().getOfferSonExpressesData() != null) {
                        for (int e = 0; e < selectOfferVos.get(i).getOfferSonData().getOfferSonExpressesData().size(); e++) {
                            //看看当前查询的省区域是否有特殊的利润配置
                            if(selectOfferVos.get(i).getOfferSonData().getOfferSonExpressesData().get(e).getCityId() == selectOfferMoneyVo.getCityId()){
                                ExpressRate = selectOfferVos.get(i).getOfferSonData().getOfferSonExpressesData().get(e).getRate(); //重新设置邮费利润率
                            }
                        }
                    }
                    BigDecimal ExpressRateLast = ExpressRate.divide(BigDecimal.valueOf(100)); //邮费利润率转换百分小数
                    if(expressMoney != null){
                        BigDecimal ExpressProfitMoney = ExpressRateLast.multiply(expressMoney.get("money")); //邮费利润
                        selectOfferVos.get(i).setExpressMoney(ExpressProfitMoney.add(expressMoney.get("money"))); //设置最终邮费，邮费成本加邮费利润
                        selectOfferVos.get(i).setExpressStartMoney(expressMoney.get("startMoney")); //首重价格
                    }

                }else{

                    //固定报价
                    if(selectOfferVos.get(i).getOfferSonData() != null &&  selectOfferVos.get(i).getOfferSonData().getIsDesign() != null && selectOfferVos.get(i).getOfferSonData().getIsDesign().equals(new Byte("1"))){
                        //如果价格已经包含设计费,清空设计费
                        selectOfferVos.get(i).setSmoney(null);
                    }
                    //检测是否包邮
                    if(selectOfferVos.get(i).getOfferSonData() != null && selectOfferVos.get(i).getOfferSonData().getIsFreeShipping() != null &&selectOfferVos.get(i).getOfferSonData().getIsFreeShipping().equals(new Byte("1"))){
                        //包邮
                        String[] CityIdsStrArr = selectOfferVos.get(i).getOfferSonData().getExceptCityIds().split(",");
                        for(int b = 0; b < CityIdsStrArr.length; b++){
                            Long CityId = null;
                           try {
                               CityId = Long.parseLong(CityIdsStrArr[b]);
                           }catch (NumberFormatException e){
                                loger.error("城市ID转码失败");
                           }
                           //除外的城市计算邮费
                           if(CityId != null && CityId == selectOfferMoneyVo.getCityId()){
                               if(expressMoney != null){
                                   selectOfferVos.get(i).setExpressMoney(expressMoney.get("money")); //设置最终邮费，邮费成本加邮费利润

                               }
                           }
                        }
                    }else{
                        //不包邮
                        BigDecimal ExpressRateLast = ExpressRate.divide(BigDecimal.valueOf(100)); //邮费利润率转换百分小数
                        if(expressMoney != null){
                            BigDecimal ExpressProfitMoney = ExpressRateLast.multiply(expressMoney.get("money")); //邮费利润
                            selectOfferVos.get(i).setExpressMoney(ExpressProfitMoney.add(expressMoney.get("money"))); //设置最终邮费，邮费成本加邮费利润
                            selectOfferVos.get(i).setExpressStartMoney(expressMoney.get("startMoney")); //首重价格
                        }
                    }
                }
                //设置发票利率
                if(invoice != null){
                    selectOfferVos.get(i).setInvoiceRate(invoice.getTaxRate()); //设置发票利润率
                    //需要邮寄才能计算发票邮寄首重
                    if(selectOfferMoneyVo.getIsY().equals(new Byte("1"))) {
                        Map<String, BigDecimal> invoiceExpressMoney = this.getExpressMoney(selectOfferMoneyVo.getSellerId(), invoice.getComanyId(), selectOfferMoneyVo.getCityId(), selectOfferMoneyVo.getNums(), selectOfferMoneyVo.getLongsData(), selectOfferMoneyVo.getWidthData(), BigDecimal.valueOf(0));
                        if (invoiceExpressMoney != null) {
                            selectOfferVos.get(i).setExpressStartMoney(invoiceExpressMoney.get("startMoney")); //首重价格
                        }
                    }
                }
                selectOfferVos.get(i).setTotalPrice(selectOfferVos.get(i).getXmoney()); //成本价
                //加上设计费
                if(selectOfferMoneyVo.getIsS().equals(new Byte("1"))){
                    if(selectOfferVos.get(i).getSmoney() != null){
                        //加设计费
                        selectOfferVos.get(i).setTotalPrice(selectOfferVos.get(i).getTotalPrice().add(selectOfferVos.get(i).getSmoney()));
                    }
                }
                //是否要邮票
                if(selectOfferMoneyVo.getIsY().equals(new Byte("1"))){
                    if(selectOfferVos.get(i).getExpressMoney() != null){
                        //加邮费
                        selectOfferVos.get(i).setTotalPrice(selectOfferVos.get(i).getTotalPrice().add(selectOfferVos.get(i).getExpressMoney()));
                    }
                }
                //否是要发票
                if(selectOfferMoneyVo.getIsF().equals(new Byte("1"))){
                    if(selectOfferVos.get(i).getInvoiceRate() != null){
                        BigDecimal inRate =  selectOfferVos.get(i).getInvoiceRate().divide(BigDecimal.valueOf(100)); //发票利润率转换百分小数
                        BigDecimal ToFaMoney = inRate.multiply(selectOfferVos.get(i).getTotalPrice()); //发票利润金额
                        selectOfferVos.get(i).setInvoiceMoney(ToFaMoney); //设置发票金额
                        //加发票金额
                        selectOfferVos.get(i).setTotalPrice(selectOfferVos.get(i).getTotalPrice().add(ToFaMoney)); //加发票利润
                        //加发票首重
                        if(selectOfferVos.get(i).getExpressStartMoney() != null){
                            selectOfferVos.get(i).setTotalPrice(selectOfferVos.get(i).getTotalPrice().add(selectOfferVos.get(i).getExpressStartMoney())); //加发票首重
                        }
                    }
                }
                selectOfferVosData.add(selectOfferVos.get(i));
            }

        }

        return selectOfferVosData;
    }

    /**
     * 获取利润配置
     * @param offerId
     * @param OfferMoney
     * @return
     */
    public BigDecimal getProfitServer(Long offerId,BigDecimal OfferMoney){
        Object ProfitDataObj = profitConfigService.callFunc(offerId);
        Map<String,Object> ProfitData = JsonReturn.Parse(ProfitDataObj);
        Integer Status = (Integer) ProfitData.get("error_code");
        if(Status > 1){
            return null;
        }
        JSONObject Datas = (JSONObject) ProfitData.get("data");
        Map<String,Object> DatasRes = JsonReturn.Parse(Datas);
        JSONArray array = Datas.getJSONArray("profit"); //获取到利润配置数组
        int SelfIndex = -1; //自身所有的配置
        BigDecimal SelfRate = null;
        int AllIndex = -1; //其余全部的配置
        BigDecimal AllRate = null;
        if(array.size() == 0){
            return null;
        }
        for(int i = 0; i < array.size(); i++){
            JSONObject ProfitOne = (JSONObject) array.get(i);
            Integer Types = (Integer) ProfitOne.get("types");
            Integer DafaultType = (Integer) ProfitOne.get("dafault_type");
            //查询默认的类型
            if(Types == DafaultType){
                Long LevelId = Long.valueOf((Integer)ProfitOne.get("level_id"));

                BigDecimal ProfitMoneyStart =  BigDecimal.valueOf((Double) ProfitOne.get("start_value"));
                BigDecimal ProfitMoneyEnd = BigDecimal.valueOf((Double) ProfitOne.get("end_value"));
                BigDecimal ProfitRate = BigDecimal.valueOf((Double) ProfitOne.get("profit_rate"));

                if(LevelId == UserUtil.getLevelId()){
                    if(ProfitMoneyStart.compareTo(OfferMoney) == -1 && ProfitMoneyEnd.compareTo(OfferMoney) >= 0){
                        SelfIndex = i;
                        SelfRate = ProfitRate;
                    }
                }
                if(LevelId == -3L){
                    if(ProfitMoneyStart.compareTo(OfferMoney) == -1 && ProfitMoneyEnd.compareTo(OfferMoney) >= 0){
                        AllIndex = i;
                        AllRate = ProfitRate;
                    }
                }
            }
        }
        BigDecimal LastRate = null;
        if(SelfIndex != -1){
            LastRate = SelfRate;
        }else if(AllIndex != -1){
            LastRate = AllRate;
        }
        return LastRate;
    }
    /**
     * 获取利润配置
     * @param offerId
     * @param OfferMoney
     * @return
     */
    public BigDecimal getProfitConfig(Long offerId,BigDecimal OfferMoney,SelectOfferVo selectOfferVo,Integer nums,Long comanyId ,Long cityId,Byte FpTypes){

        Object ProfitDataObj = profitConfigService.callFunc(offerId);
        System.out.println(ProfitDataObj);
        Map<String,Object> ProfitData = JsonReturn.Parse(ProfitDataObj);
        Integer Status = (Integer) ProfitData.get("error_code");

        if(Status < 1){
            JSONObject Datas = (JSONObject) ProfitData.get("data");
            Map<String,Object> DatasRes = JsonReturn.Parse(Datas);
            System.out.println(DatasRes.get("constitute"));
            if(DatasRes.get("constitute") == null){
                return null;
            }
            JSONObject DatasConstituteJson = (JSONObject) DatasRes.get("constitute");

            Integer ConstituteType = (Integer) DatasConstituteJson.get("types"); //报价构成类型
            Map<String,Object> DatasConstituteRes = JsonReturn.Parse(DatasConstituteJson); //报价构成数据

            JSONArray array = Datas.getJSONArray("profit"); //获取到利润配置数组


            int SelfIndex = -1; //自身所有的配置
            BigDecimal SelfRate = null;
            int AllIndex = -1; //其余全部的配置
            BigDecimal AllRate = null;
            if(array.size() == 0){
                return null;
            }
            for(int i = 0; i < array.size(); i++){
                JSONObject ProfitOne = (JSONObject) array.get(i);

                Integer Types = (Integer) ProfitOne.get("types");
                Integer DafaultType = (Integer) ProfitOne.get("dafault_type");
                //查询默认的类型
                if(Types == DafaultType){
                    Long LevelId = Long.valueOf((Integer)ProfitOne.get("level_id"));

                    BigDecimal ProfitMoneyStart =  BigDecimal.valueOf((Double) ProfitOne.get("start_value"));
                    BigDecimal ProfitMoneyEnd = BigDecimal.valueOf((Double) ProfitOne.get("end_value"));
                    BigDecimal ProfitRate = BigDecimal.valueOf((Double) ProfitOne.get("profit_rate"));

                    if(LevelId == UserUtil.getLevelId()){
                        if(ProfitMoneyStart.compareTo(OfferMoney) == -1 && ProfitMoneyEnd.compareTo(OfferMoney) >= 0){
                            SelfIndex = i;
                            SelfRate = ProfitRate;
                        }
                    }
                    if(LevelId == -3L){
                        if(ProfitMoneyStart.compareTo(OfferMoney) == -1 && ProfitMoneyEnd.compareTo(OfferMoney) >= 0){
                            AllIndex = i;
                            AllRate = ProfitRate;
                        }
                    }
                }
            }

            BigDecimal LastRate = null;
            if(SelfIndex != -1){
                LastRate = SelfRate;
            }else if(AllIndex != -1){
                LastRate = AllRate;
            }else{
                ConstituteType = 2; //当没有利润配置时,全部相加
            }
            System.out.println("最后的利润----------------------");
            System.out.println(LastRate);
            //计算商品重量
            List<SelectAttrVo> selectAttrVos = selectOfferVo.getAttrs();
            BigDecimal Longs = null;
            BigDecimal Wight = null;
            for(int i = 0; i < selectAttrVos.size(); i++){
                if(selectAttrVos.get(i).getAttr_key().equals("长")){
                    Longs = selectAttrVos.get(i).getAttr_value();
                }
                if(selectAttrVos.get(i).getAttr_key().equals("宽")){
                    Wight = selectAttrVos.get(i).getAttr_value();
                }
            }
            //计算商品重量结束

            //开始计算价格
            BigDecimal LastMoney = BigDecimal.valueOf(0);
            Integer ConstituteIsS = (Integer) DatasConstituteJson.get("isS"); //是否加设计费
            Integer ConstituteIsY = (Integer) DatasConstituteJson.get("isY"); //是否加邮费
            Integer ConstituteIsF = (Integer) DatasConstituteJson.get("isF"); //是否加发票
            BigDecimal ConstituteSrate =  BigDecimal.valueOf((Double) DatasConstituteJson.get("srate")); //邮费利润

            BigDecimal LastRateZh = LastRate.divide(BigDecimal.valueOf(100)); //

            //计算快递费
            Map<String, BigDecimal> KuaiDi = null;
            if(Longs != null && Wight != null) {
                KuaiDi = this.getExpressMoney(selectOfferVo.getSeller_id(), comanyId, cityId, nums, Longs, Wight,selectOfferVo.getWeight());
            }
            System.out.println(KuaiDi);
            switch (ConstituteType){
                case 0:
                    LastMoney = selectOfferVo.getXmoney().multiply(LastRateZh); //成本价乘以利润获得利润
                    LastMoney = LastMoney.add(selectOfferVo.getXmoney()); //利润加成本价
                    if(ConstituteIsS == 1){
                        LastMoney = LastMoney.add(selectOfferVo.getSmoney()); //加上设计费
                    }
                    //计算快递费
                    if(ConstituteIsY == 1 && Longs != null && Wight != null){
                        if(KuaiDi != null) {
                            BigDecimal expressMoney = KuaiDi.get("money");
                            BigDecimal Ymoney = BigDecimal.valueOf(0);
                            if (expressMoney != null) {
                                BigDecimal Cyrate = ConstituteSrate.divide(BigDecimal.valueOf(100)); //转换百分小数
                                Ymoney = expressMoney.multiply(Cyrate);
                                Ymoney = Ymoney.add(expressMoney);
                            }
                            LastMoney = LastMoney.add(Ymoney); //加上邮费
                        }
                    }


                    break;
                case 1:
                    LastMoney = selectOfferVo.getXmoney();
                    if(ConstituteIsS == 1) {
                        LastMoney = LastMoney.add(selectOfferVo.getSmoney()); //成本价加设计费
                    }
                    //计算快递费
                    if(ConstituteIsY == 1 && Longs != null && Wight != null){
                        BigDecimal expressMoney = KuaiDi.get("money");
                        BigDecimal Ymoney = BigDecimal.valueOf(0);
                        if(expressMoney != null){
                            //BigDecimal Cyrate = ConstituteSrate.divide(BigDecimal.valueOf(100)); //转换百分小数
                            //Ymoney = expressMoney.multiply(Cyrate);
                            Ymoney = Ymoney.add(expressMoney); //邮费
                        }
                        LastMoney = LastMoney.add(Ymoney); //加上邮费
                    }
                    break;
                case 2:
                    LastMoney = selectOfferVo.getXmoney();
                    LastMoney = LastMoney.add(selectOfferVo.getSmoney());
                    break;
            }
            //计算发票
            if(ConstituteIsF == 1){
                Invoice invoice = this.getInvoice(selectOfferVo.getSeller_id(),comanyId,FpTypes,UserUtil.getLevelId());
                if(invoice != null){
                    BigDecimal TmpMoney = null;
                    BigDecimal InRate = invoice.getTaxRate().divide(BigDecimal.valueOf(100)); //转换百分比小数
                    TmpMoney = LastMoney.multiply(InRate);
                    LastMoney = LastMoney.add(TmpMoney);
                    if(invoice.getComanyId() != 0L){
                        if(KuaiDi != null) {
                            LastMoney = LastMoney.add(KuaiDi.get("startMoney")); //加首重
                        }
                    }

                }
            }
            return LastMoney;

        }


        return null;
    }

    /**
     * 获取发票配置信息
     * @param sellerId
     * @param comanyId
     * @param types
     * @param levelId
     * @return
     */
    public Invoice getInvoice(Long sellerId,Long comanyId,Byte types,Long levelId){
        Object Datas = profitConfigService.findDataByLevel(sellerId,comanyId,types,levelId);
        System.out.println(Datas);
        Map<String,Object> JsonData = JsonReturn.Parse(Datas);
        Integer Status = (Integer) JsonData.get("error_code");
        if(Status > 0){
            return null;
        }
        JSONObject DataRes = (JSONObject) JsonData.get("data");
        Invoice invoice = JSON.parseObject(DataRes.toString(),new TypeReference<Invoice>(){});
        return invoice;
    }


    /**
     * 计算快递费
     * @param sellerId
     * @param companyId
     * @param cityId
     * @return
     */
    public Map<String,BigDecimal> getExpressMoney(Long sellerId,Long companyId,Long cityId,Integer nums,BigDecimal Longs,BigDecimal Wight,BigDecimal SjWeight){
        BigDecimal commodityNums = BigDecimal.valueOf(nums);
        BigDecimal MianJi = Longs.multiply(Wight);
        BigDecimal MianJiA = MianJi.divide(BigDecimal.valueOf(1000000));
        BigDecimal ZhongLiang = MianJiA.multiply(commodityNums); //面积乘以每平方克重
        BigDecimal ZhMpf = ZhongLiang.multiply(SjWeight);
        BigDecimal Jin = BigDecimal.valueOf(1000);
        BigDecimal totalWeight = ZhMpf.divide(Jin); //换算后的公斤数

        Map<String,Object> Datas = new HashMap<String, Object>();
        Datas.put("sellerId",sellerId);
        Datas.put("companyId",companyId);
        Datas.put("cityId",cityId);
        Datas.put("totalWeight",totalWeight);

        Object Result = expressMoneyService.findCityConfig(Datas);
        System.out.println(Result);
        Map<String,Object> JsonData = JsonReturn.Parse(Result);
        Integer Status = (Integer) JsonData.get("error_code");

        if(Status < 1){
            JSONObject Datass = (JSONObject) JsonData.get("data");
            Map<String,Object> DatasRes = JsonReturn.Parse(Datass);
            BigDecimal Money =  BigDecimal.valueOf((Double) DatasRes.get("money"));
            BigDecimal StartMoney =  BigDecimal.valueOf((Double) DatasRes.get("startMoney"));
            Map<String,BigDecimal> returnData = new HashMap<String, BigDecimal>();
            returnData.put("money",Money);
            returnData.put("startMoney",StartMoney);
            System.out.println(returnData);
            return returnData;
        }
        return null;
    }

    /**
     * 删除单条的offerSonExpress数据
     * @param id
     * @return
     */
    public int deleteOfferSonExpress(Long id){
        return offerSonExpressMapper.deleteByPrimaryKey(id);
    }
}
