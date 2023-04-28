package com.bbsw.bitboxer2.practica.builder.dto;

import com.bbsw.bitboxer2.practica.dto.ItemDTO;
import com.bbsw.bitboxer2.practica.dto.SupplierDTO;

import java.util.Set;

public class SupplierDTOBuilder {

    private Long id;
    private String name;
    private String country;
    private Set<ItemDTO> items;

    private SupplierDTOBuilder() {
        super();
    }

    public static SupplierDTOBuilder aSupplierDTO() {
        return new SupplierDTOBuilder();
    }

    public SupplierDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SupplierDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SupplierDTOBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public SupplierDTOBuilder withItems(Set<ItemDTO> items) {
        this.items = items;
        return this;
    }

    public SupplierDTO build() {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setId(this.id);
        supplierDTO.setName(this.name);
        supplierDTO.setCountry(this.country);
        supplierDTO.setItems(this.items);
        return supplierDTO;
    }

}
