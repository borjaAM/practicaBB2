package com.bbsw.bitboxer2.practica.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id", scope = PriceReductionDTO.class)
public class PriceReductionDTO implements Serializable {

    private Long id;
    private double reducedPrice;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;
    private ItemDTO item;

    @Override
    public String toString() {
        return "PriceReductionDTO{" +
                "id=" + id +
                ", reducedPrice=" + reducedPrice +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", item=" + item.getItemCode() +
                '}';
    }
}
