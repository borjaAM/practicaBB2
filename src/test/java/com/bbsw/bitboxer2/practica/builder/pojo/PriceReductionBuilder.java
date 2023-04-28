package com.bbsw.bitboxer2.practica.builder.pojo;

import com.bbsw.bitboxer2.practica.model.Item;
import com.bbsw.bitboxer2.practica.model.PriceReduction;

import java.time.LocalDate;

public class PriceReductionBuilder {

    private Long id;
    private double reducedPrice;
    private LocalDate startDate;
    private LocalDate endDate;
    private Item item;

    private PriceReductionBuilder() {
        super();
    }

    public static PriceReductionBuilder aPriceReduction() {
        return new PriceReductionBuilder();
    }

    public PriceReductionBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PriceReductionBuilder withReducedPrice(double reducedPrice) {
        this.reducedPrice = reducedPrice;
        return this;
    }

    public PriceReductionBuilder withStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public PriceReductionBuilder withEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public PriceReductionBuilder withItem(Item item) {
        this.item = item;
        return this;
    }

    public PriceReduction build() {
        PriceReduction priceReduction = new PriceReduction();
        priceReduction.setId(this.id);
        priceReduction.setReducedPrice(this.reducedPrice);
        priceReduction.setStartDate(this.startDate);
        priceReduction.setEndDate(this.endDate);
        priceReduction.setItem(this.item);
        return priceReduction;
    }

}
