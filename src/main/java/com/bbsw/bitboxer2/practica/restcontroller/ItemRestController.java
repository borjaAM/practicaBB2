package com.bbsw.bitboxer2.practica.restcontroller;

import com.bbsw.bitboxer2.practica.dto.ItemDTO;
import com.bbsw.bitboxer2.practica.dto.PriceReductionDTO;
import com.bbsw.bitboxer2.practica.enums.ItemStateEnum;
import com.bbsw.bitboxer2.practica.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
        if (itemDTO.getDescription() == null) {
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
    public ResponseEntity<String> createPriceReduction(@PathVariable Long itemCode, @Validated @RequestBody PriceReductionDTO priceReductionDTO) {
        ItemDTO itemDTO = itemService.findByItemCode(itemCode);
        itemDTO.addPriceReduction(priceReductionDTO);
        ItemDTO itemDTOUpdated = itemService.updateItem(itemDTO);
        return ResponseEntity.ok(itemDTOUpdated.toString());
    }

    @PutMapping("/{itemCode}")
    public ResponseEntity<String> updateItem(@PathVariable Long itemCode, @Validated @RequestBody ItemDTO itemDTO) {
        if (itemDTO.getItemCode() == null) {
            return ResponseEntity.badRequest().body("The item code can't be empty");
        }
        if (itemDTO.getDescription() == null) {
            return ResponseEntity.badRequest().body("The item description can't be empty");
        }
        ItemDTO itemUpdated = itemService.updateItem(itemDTO);
        if (itemUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Item updated");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@PathVariable Long itemCode) {
        itemService.deleteItem(itemCode);
        return ResponseEntity.ok().body("Item deleted");
    }

}
