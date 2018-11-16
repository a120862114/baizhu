package com.bzdepot.special.bo;

import java.math.BigDecimal;

public class ProductWantGramBo {

    private Long id;

    private BigDecimal gram;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getGram() {
        return gram;
    }

    public void setGram(BigDecimal gram) {
        this.gram = gram;
    }

    @Override
    public String toString() {
        return "ProductWantGramBo{" +
                "id=" + id +
                ", gram=" + gram +
                '}';
    }
}
