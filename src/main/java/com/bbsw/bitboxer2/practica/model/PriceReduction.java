package com.bbsw.bitboxer2.practica.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PriceReduction {
    
    private double reducedPrice;
    private LocalDate startDate;
    private LocalDate endDate;

}
