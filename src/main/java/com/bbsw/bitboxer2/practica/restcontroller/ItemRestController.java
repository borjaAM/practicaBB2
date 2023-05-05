package com.bbsw.bitboxer2.practica.restcontroller;

import com.bbsw.bitboxer2.practica.dto.ItemDTO;
import com.bbsw.bitboxer2.practica.dto.PriceReductionDTO;
import com.bbsw.bitboxer2.practica.dto.SupplierDTO;
import com.bbsw.bitboxer2.practica.enums.ItemStateEnum;
import com.bbsw.bitboxer2.practica.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        ItemDTO itemDTO = itemService.findByItemCode(itemCode);
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
            .body(String.format("%s", itemCreatedId));
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
        ItemDTO itemUpdated = itemService.addPriceReduction(itemCode, priceReductionDTO);
        if (itemUpdated == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("There is an active price reduction associated to this item");
        }
        return ResponseEntity.ok(itemUpdated.toString());
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

    @PutMapping
    public ResponseEntity<String> updateItem(@Validated @RequestBody ItemDTO itemDTO) {
        if (itemDTO.getItemCode() == null) {
            return ResponseEntity.badRequest().body("The item code can't be empty");
        }
        if (isBlank(itemDTO.getDescription())) {
            return ResponseEntity.badRequest().body("The item description can't be empty");
        }
        if (!ItemStateEnum.ACTIVE.equals(itemDTO.getItemState())) {
            return ResponseEntity.badRequest().body("Can't update item because is not active");
        }
        ItemDTO itemUpdated = itemService.updateItem(itemDTO);
        if (itemUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Item updated: " + itemUpdated);
    }

    @PutMapping("/deactivated")
    public ResponseEntity<String> deactivateItem(@Validated @RequestBody ItemDTO itemDTO) {
        ItemDTO itemDTOById = itemService.findById(itemDTO.getId());
        if (itemDTOById == null) {
            return ResponseEntity.notFound().build();
        }
        if (ItemStateEnum.DISCONTINUED.equals(itemDTOById.getItemState())) {
            return ResponseEntity.badRequest().body("Item is already deactivated");
        }
        if (itemService.deactivateItem(itemDTO) == 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Item not deactivated");
        }
        return ResponseEntity.ok("Item has been successfully deactivated");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        if (itemService.findById(id) == null) {
            return ResponseEntity.badRequest().build();
        }
        itemService.deleteItem(id);
        return ResponseEntity.ok().body("Item deleted");
    }

}
