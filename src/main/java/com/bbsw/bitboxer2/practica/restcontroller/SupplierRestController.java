package com.bbsw.bitboxer2.practica.restcontroller;

import com.bbsw.bitboxer2.practica.dto.ItemDTO;
import com.bbsw.bitboxer2.practica.dto.SupplierDTO;
import com.bbsw.bitboxer2.practica.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierRestController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> findSuppliersWithPriceReductions(
        @RequestParam(name = "withPriceReduction", required = false) boolean withPriceReductions
    ) {
        if (!withPriceReductions) {
            return ResponseEntity.ok(supplierService.findAll());
        }
        return ResponseEntity.ok(supplierService.findSuppliersWithPriceReductions());
    }

    @GetMapping("/cheapestItem")
    public ResponseEntity<Map<Long, ItemDTO>> findCheapestItemPerSupplier() {
        return ResponseEntity.ok(supplierService.findCheapestItemPerSupplier());
    }

}
