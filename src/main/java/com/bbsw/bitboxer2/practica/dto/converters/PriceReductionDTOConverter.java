package com.bbsw.bitboxer2.practica.dto.converters;

import com.bbsw.bitboxer2.practica.dto.PriceReductionDTO;
import com.bbsw.bitboxer2.practica.model.PriceReduction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

public class PriceReductionDTOConverter implements DtoConverter<PriceReduction, PriceReductionDTO> {

    @SneakyThrows
    @Override
    public PriceReduction convertFromDTO(PriceReductionDTO dto) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String dtoJson = objectMapper.writeValueAsString(dto);
        return objectMapper.readValue(dtoJson, PriceReduction.class);
    }

    @SneakyThrows
    @Override
    public PriceReductionDTO convertToDTO(PriceReduction pojo) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String pojoJson = objectMapper.writeValueAsString(pojo);
        return objectMapper.readValue(pojoJson, PriceReductionDTO.class);
    }

}
