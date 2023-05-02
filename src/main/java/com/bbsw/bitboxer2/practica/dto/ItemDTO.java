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

    public boolean addPriceReduction(PriceReductionDTO priceReductionDTO) {
        boolean activePriceReduction = priceReductions.stream()
            .anyMatch(priceReduction ->
                priceReductionDTO.getStartDate().isBefore(priceReduction.getEndDate()) &&
                priceReductionDTO.getEndDate().isAfter(priceReduction.getStartDate())
            );
        if (!activePriceReduction) {
            return priceReductions.add(priceReductionDTO);
        }
        return false;
    }

    public boolean addSupplier(SupplierDTO supplierDTO) {
        boolean foundSupplier = suppliers.stream()
            .anyMatch(supplier -> supplier.getId().equals(supplierDTO.getId()));
        if (!foundSupplier) {
            return suppliers.add(supplierDTO);
        }
        return false;
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
