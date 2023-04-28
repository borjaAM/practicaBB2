package com.bbsw.bitboxer2.practica.builder.dto;

import com.bbsw.bitboxer2.practica.dto.ItemDTO;
import com.bbsw.bitboxer2.practica.dto.PriceReductionDTO;

import java.time.LocalDate;

public class PriceReductionDTOBuilder {

    private Long id;
    private double reducedPrice;
    private LocalDate startDate;
    private LocalDate endDate;
    private ItemDTO item;

    private PriceReductionDTOBuilder() {
        super();
    }

    public static PriceReductionDTOBuilder aPriceReductionDTO() {
        return new PriceReductionDTOBuilder();
    }

    public PriceReductionDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PriceReductionDTOBuilder withReducedPrice(double reducedPrice) {
        this.reducedPrice = reducedPrice;
        return this;
    }

    public PriceReductionDTOBuilder withStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public PriceReductionDTOBuilder withEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public PriceReductionDTOBuilder withItem(ItemDTO item) {
        this.item = item;
        return this;
    }

    public PriceReductionDTO build() {
        PriceReductionDTO priceReductionDTO = new PriceReductionDTO();
        priceReductionDTO.setId(this.id);
        priceReductionDTO.setReducedPrice(this.reducedPrice);
        priceReductionDTO.setStartDate(this.startDate);
        priceReductionDTO.setEndDate(this.endDate);
        priceReductionDTO.setItem(this.item);
        return priceReductionDTO;
    }

}
