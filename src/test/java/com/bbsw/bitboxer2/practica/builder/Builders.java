package com.bbsw.bitboxer2.practica.builder;

import com.bbsw.bitboxer2.practica.builder.dto.ItemDTOBuilder;
import com.bbsw.bitboxer2.practica.builder.dto.PriceReductionDTOBuilder;
import com.bbsw.bitboxer2.practica.builder.dto.SupplierDTOBuilder;
import com.bbsw.bitboxer2.practica.builder.dto.UserDTOBuilder;
import com.bbsw.bitboxer2.practica.builder.pojo.ItemBuilder;
import com.bbsw.bitboxer2.practica.builder.pojo.PriceReductionBuilder;
import com.bbsw.bitboxer2.practica.builder.pojo.SupplierBuilder;
import com.bbsw.bitboxer2.practica.builder.pojo.UserBuilder;
import com.bbsw.bitboxer2.practica.dto.ItemDTO;
import com.bbsw.bitboxer2.practica.dto.PriceReductionDTO;
import com.bbsw.bitboxer2.practica.dto.SupplierDTO;
import com.bbsw.bitboxer2.practica.dto.UserDTO;
import com.bbsw.bitboxer2.practica.enums.ItemStateEnum;
import com.bbsw.bitboxer2.practica.enums.UserRoleEnum;
import com.bbsw.bitboxer2.practica.model.Item;
import com.bbsw.bitboxer2.practica.model.PriceReduction;
import com.bbsw.bitboxer2.practica.model.Supplier;
import com.bbsw.bitboxer2.practica.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

public class Builders {

    private Builders() {}

    public static Item firstItem() {
        return ItemBuilder.anItem()
            .withId(1L)
            .withItemCode(1L)
            .withDescription("Item 1")
            .withPrice(10.0)
            .withItemState(ItemStateEnum.ACTIVE)
            .withCreationDate(LocalDate.of(2023, 4, 27))
            .withPriceReductions(new HashSet<>())
            .withSuppliers(new HashSet<>())
            .build();
    }

    public static Item secondItem() {
        return ItemBuilder.anItem()
            .withId(2L)
            .withItemCode(2L)
            .withDescription("Item 2")
            .withPrice(100.0)
            .withItemState(ItemStateEnum.DISCONTINUED)
            .withCreationDate(LocalDate.of(2021, 1, 1))
            .withPriceReductions(new HashSet<>())
            .withSuppliers(new HashSet<>())
            .withDeactivationReason("No reason")
            .build();
    }

    public static Item thirdItem() {
        return ItemBuilder.anItem()
            .withId(3L)
            .withItemCode(3L)
            .withDescription("Item 3")
            .withPrice(70.0)
            .withItemState(ItemStateEnum.ACTIVE)
            .withCreationDate(LocalDate.of(2022, 6, 15))
            .withPriceReductions(new HashSet<>())
            .withSuppliers(new HashSet<>())
            .build();
    }

    public static ItemDTO firstItemDTO() {
        return ItemDTOBuilder.anItemDTO()
            .withId(1L)
            .withItemCode(1L)
            .withDescription("Item 1")
            .withPrice(10.0)
            .withItemState(ItemStateEnum.ACTIVE)
            .withCreationDate(LocalDate.of(2023, 4, 27))
            .withPriceReductions(new HashSet<>())
            .withSuppliers(new HashSet<>())
            .build();
    }

    public static ItemDTO secondItemDTO() {
        return ItemDTOBuilder.anItemDTO()
            .withId(2L)
            .withItemCode(2L)
            .withDescription("Item 2")
            .withPrice(100.0)
            .withItemState(ItemStateEnum.DISCONTINUED)
            .withCreationDate(LocalDate.of(2021, 1, 1))
            .withPriceReductions(new HashSet<>())
            .withSuppliers(new HashSet<>())
            .withDeactivationReason("No reason")
            .build();
    }

    public static ItemDTO thirdItemDTO() {
        return ItemDTOBuilder.anItemDTO()
            .withId(3L)
            .withItemCode(3L)
            .withDescription("Item 3")
            .withPrice(70.0)
            .withItemState(ItemStateEnum.ACTIVE)
            .withCreationDate(LocalDate.of(2022, 6, 15))
            .withPriceReductions(new HashSet<>())
            .withSuppliers(new HashSet<>())
            .build();
    }

    public static User firstUser() {
        return UserBuilder.anUser()
            .withId(1L)
            .withUsername("User 1")
            .withPassword("password1")
            .withUserRole(UserRoleEnum.USER)
            .withItems(new ArrayList<>())
            .build();
    }

    public static User secondUser() {
        return UserBuilder.anUser()
            .withId(2L)
            .withUsername("Admin 1")
            .withPassword("password1")
            .withUserRole(UserRoleEnum.ADMIN)
            .withItems(new ArrayList<>())
            .build();
    }

    public static UserDTO firstUserDTO() {
        return UserDTOBuilder.anUser()
            .withId(1L)
            .withUsername("User 1")
            .withPassword("password1")
            .withUserRole(UserRoleEnum.USER)
            .withItems(new ArrayList<>())
            .build();
    }

    public static UserDTO secondUserDTO() {
        return UserDTOBuilder.anUser()
            .withId(2L)
            .withUsername("Admin 1")
            .withPassword("password1")
            .withUserRole(UserRoleEnum.ADMIN)
            .withItems(new ArrayList<>())
            .build();
    }

    public static Supplier firstSupplier() {
        return SupplierBuilder.aSupplier()
            .withId(1L)
            .withName("Supplier 1")
            .withCountry("Spain")
            .withItems(new HashSet<>())
            .build();
    }

    public static Supplier secondSupplier() {
        return SupplierBuilder.aSupplier()
            .withId(2L)
            .withName("Supplier 2")
            .withCountry("England")
            .withItems(new HashSet<>())
            .build();
    }

    public static SupplierDTO firstSupplierDTO() {
        return SupplierDTOBuilder.aSupplierDTO()
            .withId(1L)
            .withName("Supplier 1")
            .withCountry("Spain")
            .withItems(new HashSet<>())
            .build();
    }

    public static SupplierDTO secondSupplierDTO() {
        return SupplierDTOBuilder.aSupplierDTO()
            .withId(2L)
            .withName("Supplier 2")
            .withCountry("England")
            .withItems(new HashSet<>())
            .build();
    }

    public static PriceReduction firstPriceReduction() {
        return PriceReductionBuilder.aPriceReduction()
            .withId(1L)
            .withReducedPrice(5)
            .withStartDate(LocalDate.of(2023, 4, 28))
            .withEndDate(LocalDate.of(2023, 5, 8))
            .build();
    }

    public static PriceReduction secondPriceReduction() {
        return PriceReductionBuilder.aPriceReduction()
            .withId(2L)
            .withReducedPrice(15)
            .withStartDate(LocalDate.of(2022, 11, 30))
            .withEndDate(LocalDate.of(2023, 1, 1))
            .build();
    }

    public static PriceReduction thirdPriceReduction() {
        return PriceReductionBuilder.aPriceReduction()
            .withId(3L)
            .withReducedPrice(35)
            .withStartDate(LocalDate.of(2023, 1, 1))
            .withEndDate(LocalDate.of(2023, 12, 31))
            .build();
    }

    public static PriceReductionDTO firstPriceReductionDTO() {
        return PriceReductionDTOBuilder.aPriceReductionDTO()
            .withId(1L)
            .withReducedPrice(5)
            .withStartDate(LocalDate.of(2023, 4, 28))
            .withEndDate(LocalDate.of(2023, 5, 8))
            .build();
    }

    public static PriceReductionDTO secondPriceReductionDTO() {
        return PriceReductionDTOBuilder.aPriceReductionDTO()
            .withId(2L)
            .withReducedPrice(15)
            .withStartDate(LocalDate.of(2022, 11, 30))
            .withEndDate(LocalDate.of(2023, 1, 1))
            .build();
    }

    public static PriceReductionDTO thirdPriceReductionDTO() {
        return PriceReductionDTOBuilder.aPriceReductionDTO()
            .withId(3L)
            .withReducedPrice(35)
            .withStartDate(LocalDate.of(2023, 1, 1))
            .withEndDate(LocalDate.of(2023, 12, 31))
            .build();
    }

}
