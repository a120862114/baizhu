package com.bzdepot.offer.vo;

import com.bzdepot.offer.model.OfferSon;

import java.math.BigDecimal;
import java.util.List;

public class SelectOfferVo {

    private Long group_id;

    private Long offer_id;

    private Long seller_id;

    private BigDecimal thickness;

    private BigDecimal weight;

    private BigDecimal smoney;

    private BigDecimal xmoney;

    private Byte types;

    private Long classId;

    private Long textureId;

    private List<SelectAttrVo> attrs;

    private OfferSon offerSonData;

    private BigDecimal profitRate;

    private BigDecimal profitMoney; //利润价格

    private BigDecimal expressMoney; //邮费价格

    private BigDecimal expressStartMoney; //邮费首重价格

    private BigDecimal InvoiceRate; //发票利率

    private BigDecimal InvoiceMoney; //发票林润金额

    private BigDecimal TotalPrice; //总价格

    public Byte getTypes() {
        return types;
    }

    public void setTypes(Byte types) {
        this.types = types;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getTextureId() {
        return textureId;
    }

    public void setTextureId(Long textureId) {
        this.textureId = textureId;
    }

    public Long getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(Long offer_id) {
        this.offer_id = offer_id;
    }

    public BigDecimal getThickness() {
        return thickness;
    }

    public void setThickness(BigDecimal thickness) {
        this.thickness = thickness;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getSmoney() {
        return smoney;
    }

    public void setSmoney(BigDecimal smoney) {
        this.smoney = smoney;
    }

    public BigDecimal getXmoney() {
        return xmoney;
    }

    public void setXmoney(BigDecimal xmoney) {
        this.xmoney = xmoney;
    }

    public List<SelectAttrVo> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<SelectAttrVo> attrs) {
        this.attrs = attrs;
    }

    public Long getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Long seller_id) {
        this.seller_id = seller_id;
    }

    public OfferSon getOfferSonData() {
        return offerSonData;
    }

    public void setOfferSonData(OfferSon offerSonData) {
        this.offerSonData = offerSonData;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public BigDecimal getProfitMoney() {
        return profitMoney;
    }

    public void setProfitMoney(BigDecimal profitMoney) {
        this.profitMoney = profitMoney;
    }

    public BigDecimal getExpressMoney() {
        return expressMoney;
    }

    public void setExpressMoney(BigDecimal expressMoney) {
        this.expressMoney = expressMoney;
    }

    public BigDecimal getExpressStartMoney() {
        return expressStartMoney;
    }

    public void setExpressStartMoney(BigDecimal expressStartMoney) {
        this.expressStartMoney = expressStartMoney;
    }

    public BigDecimal getInvoiceRate() {
        return InvoiceRate;
    }

    public void setInvoiceRate(BigDecimal invoiceRate) {
        InvoiceRate = invoiceRate;
    }

    public BigDecimal getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        TotalPrice = totalPrice;
    }

    public BigDecimal getInvoiceMoney() {
        return InvoiceMoney;
    }

    public void setInvoiceMoney(BigDecimal invoiceMoney) {
        InvoiceMoney = invoiceMoney;
    }

    @Override
    public String toString() {
        return "SelectOfferVo{" +
                "group_id=" + group_id +
                ", offer_id=" + offer_id +
                ", seller_id=" + seller_id +
                ", thickness=" + thickness +
                ", weight=" + weight +
                ", smoney=" + smoney +
                ", xmoney=" + xmoney +
                ", types=" + types +
                ", classId=" + classId +
                ", textureId=" + textureId +
                ", attrs=" + attrs +
                ", offerSonData=" + offerSonData +
                ", profitRate=" + profitRate +
                ", profitMoney=" + profitMoney +
                ", expressMoney=" + expressMoney +
                ", expressStartMoney=" + expressStartMoney +
                ", InvoiceRate=" + InvoiceRate +
                ", InvoiceMoney=" + InvoiceMoney +
                ", TotalPrice=" + TotalPrice +
                '}';
    }
}
