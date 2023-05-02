package com.bbsw.bitboxer2.practica.restcontroller;

import com.bbsw.bitboxer2.practica.dto.ItemDTO;
import com.bbsw.bitboxer2.practica.dto.PriceReductionDTO;
import com.bbsw.bitboxer2.practica.dto.SupplierDTO;
import com.bbsw.bitboxer2.practica.enums.ItemStateEnum;
import com.bbsw.bitboxer2.practica.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.hibernate.internal.util.StringHelper.isBlank;

@RestController
@RequestMapping("/api/items")
public class ItemRestController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getItems(@RequestParam(name = "state", required = false) ItemStateEnum itemState) {
        if (itemState == null) {
            return ResponseEntity.ok(itemService.findAll());
        }
        return ResponseEntity.ok(itemService.findByState(itemState));
    }

    @GetMapping("/{itemCode}")
    public ResponseEntity<ItemDTO> getItemByItemCode(@PathVariable Long itemCode) {
        ItemDTO itemDTO = itemService.findById(itemCode);
        if (itemDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itemDTO);
    }

    @PostMapping
    public ResponseEntity<String> createItem(@Validated @RequestBody ItemDTO itemDTO) {
        itemDTO.setItemState(ItemStateEnum.ACTIVE);
        itemDTO.setCreationDate(LocalDate.now());
        if (itemDTO.getItemCode() == null) {
            return ResponseEntity.badRequest().body("The item code can't be empty");
        }
        if (isBlank(itemDTO.getDescription())) {
            return ResponseEntity.badRequest().body("The item description can't be empty");
        }
        Long itemCreatedId = itemService.createItem(itemDTO);
        if (itemCreatedId == null) {
            return ResponseEntity.badRequest().body("An item with that item code already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(String.format("Item created with id: %s", itemCreatedId));
    }

    @PostMapping("/{itemCode}/priceReductions")
    public ResponseEntity<String> createPriceReduction(
        @PathVariable Long itemCode, @Validated @RequestBody PriceReductionDTO priceReductionDTO
    ) {
        ItemDTO itemDTO = itemService.findByItemCode(itemCode);
        if (itemDTO == null) {
            return ResponseEntity.notFound().build();
        }
        priceReductionDTO.setItem(itemDTO);
        if (itemService.addPriceReduction(itemCode, priceReductionDTO) == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("There is an active price reduction associated to this item");
        }
        ItemDTO itemDTOUpdated = itemService.updateItem(itemDTO);
        return ResponseEntity.ok(itemDTOUpdated.toString());
    }

    @PostMapping("/{itemCode}/suppliers")
    public ResponseEntity<String> associateSupplierToItem(
        @PathVariable Long itemCode, @Validated @RequestBody SupplierDTO supplierDTO
    ) {
        ItemDTO itemDTO = itemService.findByItemCode(itemCode);
        if (itemDTO == null) {
            return ResponseEntity.notFound().build();
        }
        if (itemService.associateNewSupplier(itemCode, supplierDTO) == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Supplier is already associated to this item");
        }
        return ResponseEntity.ok(String.format("Supplier associated to item (%s)", itemCode));
    }

    @PutMapping("/{itemCode}")
    public ResponseEntity<String> updateItem(@Validated @RequestBody ItemDTO itemDTO) {
        if (itemDTO.getItemCode() == null) {
            return ResponseEntity.badRequest().body("The item code can't be empty");
        }
        if (isBlank(itemDTO.getDescription())) {
            return ResponseEntity.badRequest().body("The item description can't be empty");
        }
        ItemDTO itemUpdated = itemService.updateItem(itemDTO);
        if (itemUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Item updated: " + itemUpdated);
    }

    @PutMapping("/{itemCode}/deactivation")
    public ResponseEntity<String> deactivateItem(@PathVariable Long itemCode, @Validated @RequestBody ItemDTO itemDTO) {
        if (itemDTO.getItemCode() == null) {
            return ResponseEntity.badRequest().body("The item code can't be empty");
        }
        itemDTO.setId(itemCode);
        if (itemService.deactivateItem(itemDTO) == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Deactivation reason is empty or user not exists");
        }
        return ResponseEntity.ok("Item has been successfully deactivated");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteItem(@PathVariable Long itemCode) {
        itemService.deleteItem(itemCode);
        return ResponseEntity.ok().body("Item deleted");
    }

}
