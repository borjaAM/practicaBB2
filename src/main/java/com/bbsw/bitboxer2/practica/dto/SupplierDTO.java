package com.bbsw.bitboxer2.practica.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id", scope = SupplierDTO.class)
public class SupplierDTO implements Serializable {

    private Long id;
    private String name;
    private String country;
    private Set<ItemDTO> items = new HashSet<>();

    @Override
    public String toString() {
        List<String> itemCodes = items.stream()
            .map(ItemDTO::getItemCode)
            .map(String::valueOf)
            .sorted()
            .collect(Collectors.toList());
        return "SupplierDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", items=" + itemCodes +
                '}';
    }
}
