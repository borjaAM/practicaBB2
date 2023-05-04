package com.bbsw.bitboxer2.practica.service;

import com.bbsw.bitboxer2.practica.dto.ItemDTO;
import com.bbsw.bitboxer2.practica.dto.SupplierDTO;
import com.bbsw.bitboxer2.practica.dto.converters.SupplierDTOConverter;
import com.bbsw.bitboxer2.practica.model.Supplier;
import com.bbsw.bitboxer2.practica.repository.SupplierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SupplierService {

    private static final Logger logger = LoggerFactory.getLogger(SupplierService.class);

    private final SupplierDTOConverter supplierDTOConverter = new SupplierDTOConverter();

    @Autowired
    private SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<SupplierDTO> findAll() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream()
            .map(supplierDTOConverter::convertToDTO)
            .collect(Collectors.toList());
    }

    public SupplierDTO findById(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElse(null);
        if (supplier == null) {
            logger.info("Can't find supplier with id: {}", id);
            return null;
        }
        return supplierDTOConverter.convertToDTO(supplier);
    }

    public Long createSupplier(SupplierDTO supplierDTO) {
        Supplier supplierSaved = supplierRepository.save(supplierDTOConverter.convertFromDTO(supplierDTO));
        return supplierSaved.getId();
    }

    public List<SupplierDTO> findSuppliersWithPriceReductions() {
        List<Supplier> suppliersWithPriceReductions = supplierRepository.findSuppliersWithPriceReductions();
        return suppliersWithPriceReductions.stream()
            .map(supplierDTOConverter::convertToDTO)
            .collect(Collectors.toList());
    }

    public Map<Long, ItemDTO> findCheapestItemPerSupplier() {
//        List<Map<Long, Item>> cheapestItemPerSupplier = supplierRepository.findCheapestItemPerSupplier();
//        Map<Long, ItemDTO> result = new HashMap<>();
//        ItemDTOConverter itemDTOConverter = new ItemDTOConverter();
//        cheapestItemPerSupplier.forEach(item ->
//            result.putAll(item);
//        );
//        for (Map<Long, Item> map : cheapestItemPerSupplier) {
//            for (Map.Entry<Long, Item> entry : map.entrySet()) {
//                Item value = entry.getValue();
//                ItemDTO itemDTO = itemDTOConverter.convertToDTO(value);
//                result.put(entry.getKey(), itemDTO);
//            }
//        }
//        return result;
        return new HashMap<>();
    }
}
