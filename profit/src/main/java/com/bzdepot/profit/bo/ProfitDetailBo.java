package com.bzdepot.profit.bo;

import java.math.BigDecimal;

public class ProfitDetailBo {
    private Long id;

    private Long offer_id;

    private Byte types;

    private BigDecimal start_value;

    private BigDecimal end_value;

    private Long level_id;

    private BigDecimal profit_rate;

    private Byte ruler_type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(Long offer_id) {
        this.offer_id = offer_id;
    }

    public Byte getTypes() {
        return types;
    }

    public void setTypes(Byte types) {
        this.types = types;
    }

    public BigDecimal getStart_value() {
        return start_value;
    }

    public void setStart_value(BigDecimal start_value) {
        this.start_value = start_value;
    }

    public BigDecimal getEnd_value() {
        return end_value;
    }

    public void setEnd_value(BigDecimal end_value) {
        this.end_value = end_value;
    }

    public Long getLevel_id() {
        return level_id;
    }

    public void setLevel_id(Long level_id) {
        this.level_id = level_id;
    }

    public BigDecimal getProfit_rate() {
        return profit_rate;
    }

    public void setProfit_rate(BigDecimal profit_rate) {
        this.profit_rate = profit_rate;
    }

    public Byte getRuler_type() {
        return ruler_type;
    }

    public void setRuler_type(Byte ruler_type) {
        this.ruler_type = ruler_type;
    }
}
