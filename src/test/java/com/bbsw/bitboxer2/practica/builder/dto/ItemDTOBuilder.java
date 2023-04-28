package com.bbsw.bitboxer2.practica.builder.dto;

import com.bbsw.bitboxer2.practica.dto.ItemDTO;
import com.bbsw.bitboxer2.practica.dto.PriceReductionDTO;
import com.bbsw.bitboxer2.practica.dto.SupplierDTO;
import com.bbsw.bitboxer2.practica.dto.UserDTO;
import com.bbsw.bitboxer2.practica.enums.ItemStateEnum;

import java.time.LocalDate;
import java.util.Set;

public class ItemDTOBuilder {

    private Long itemCode;
    private String description;
    private double price;
    private ItemStateEnum itemState;
    private LocalDate creationDate;
    private UserDTO creator;
    private Set<PriceReductionDTO> priceReductions;
    private Set<SupplierDTO> suppliers;

    private ItemDTOBuilder() {
        super();
    }

    public static ItemDTOBuilder anItemDTO() {
        return new ItemDTOBuilder();
    }

    public ItemDTOBuilder withItemCode(Long itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    public ItemDTOBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ItemDTOBuilder withPrice(double price) {
        this.price = price;
        return this;
    }

    public ItemDTOBuilder withItemState(ItemStateEnum itemState) {
        this.itemState = itemState;
        return this;
    }

    public ItemDTOBuilder withCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public ItemDTOBuilder withCreator(UserDTO creator) {
        this.creator = creator;
        return this;
    }

    public ItemDTOBuilder withPriceReductions(Set<PriceReductionDTO> priceReductions) {
        this.priceReductions = priceReductions;
        return this;
    }

    public ItemDTOBuilder withSuppliers(Set<SupplierDTO> suppliers) {
        this.suppliers = suppliers;
        return this;
    }

    public ItemDTO build() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemCode(this.itemCode);
        itemDTO.setDescription(this.description);
        itemDTO.setPrice(this.price);
        itemDTO.setItemState(this.itemState);
        itemDTO.setCreationDate(this.creationDate);
        itemDTO.setCreator(this.creator);
        itemDTO.setPriceReductions(this.priceReductions);
        itemDTO.setSuppliers(this.suppliers);
        return itemDTO;
    }

}
