package com.bbsw.bitboxer2.practica.dto;

import com.bbsw.bitboxer2.practica.enums.ItemStateEnum;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id", scope = ItemDTO.class)
public class ItemDTO implements Serializable {

    private Long id;
    private Long itemCode;
    private String description;
    private double price;
    private ItemStateEnum itemState;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate creationDate;
    private UserDTO creator;
    private Set<PriceReductionDTO> priceReductions = new HashSet<>();
    private Set<SupplierDTO> suppliers = new HashSet<>();

    public void addPriceReduction(PriceReductionDTO priceReductionDTO) {
//        if (priceReductions.contains(priceReductionDTO)) {
//            return;
//        }
        priceReductions.add(priceReductionDTO);
    }

    @Override
    public String toString() {
        List<String> priceReductionIds = priceReductions.stream()
            .map(PriceReductionDTO::getId)
            .map(String::valueOf)
            .sorted()
            .collect(Collectors.toList());
        List<String> supplierIds = suppliers.stream()
            .map(SupplierDTO::getId)
            .map(String::valueOf)
            .sorted()
            .collect(Collectors.toList());
        return "ItemDTO{" +
            "itemCode=" + itemCode +
            ", description='" + description + '\'' +
            ", price=" + price +
            ", itemState=" + itemState +
            ", creationDate=" + creationDate +
            ", creator=" + creator +
            ", priceReductions=" + priceReductionIds +
            ", suppliers=" + supplierIds +
            '}';
    }
}
