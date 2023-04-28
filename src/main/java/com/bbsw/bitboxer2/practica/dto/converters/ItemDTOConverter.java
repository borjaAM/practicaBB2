package com.bbsw.bitboxer2.practica.dto.converters;

import com.bbsw.bitboxer2.practica.dto.ItemDTO;
import com.bbsw.bitboxer2.practica.model.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

public class ItemDTOConverter implements DtoConverter<Item, ItemDTO> {

    @SneakyThrows
    @Override
    public Item convertFromDTO(ItemDTO dto) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String dtoJson = objectMapper.writeValueAsString(dto);
        return objectMapper.readValue(dtoJson, Item.class);
    }

    @SneakyThrows
    @Override
    public ItemDTO convertToDTO(Item pojo) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String pojoJson = objectMapper.writeValueAsString(pojo);
        return objectMapper.readValue(pojoJson, ItemDTO.class);
    }

}
