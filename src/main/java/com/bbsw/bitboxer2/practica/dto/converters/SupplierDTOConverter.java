package com.bbsw.bitboxer2.practica.dto.converters;

import com.bbsw.bitboxer2.practica.dto.SupplierDTO;
import com.bbsw.bitboxer2.practica.model.Supplier;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

public class SupplierDTOConverter implements DtoConverter<Supplier, SupplierDTO> {

    @SneakyThrows
    @Override
    public Supplier convertFromDTO(SupplierDTO dto) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String dtoJson = objectMapper.writeValueAsString(dto);
        return objectMapper.readValue(dtoJson, Supplier.class);
    }

    @SneakyThrows
    @Override
    public SupplierDTO convertToDTO(Supplier pojo) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String pojoJson = objectMapper.writeValueAsString(pojo);
        return objectMapper.readValue(pojoJson, SupplierDTO.class);
    }

}
