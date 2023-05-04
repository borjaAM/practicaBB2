package com.bbsw.bitboxer2.practica.restcontroller;

import com.bbsw.bitboxer2.practica.builder.Builders;
import com.bbsw.bitboxer2.practica.dto.ItemDTO;
import com.bbsw.bitboxer2.practica.dto.PriceReductionDTO;
import com.bbsw.bitboxer2.practica.dto.SupplierDTO;
import com.bbsw.bitboxer2.practica.dto.UserDTO;
import com.bbsw.bitboxer2.practica.enums.ItemStateEnum;
import com.bbsw.bitboxer2.practica.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemRestController.class)
class ItemRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private ItemService itemService;

    private ItemDTO itemDTO1;
    private ItemDTO itemDTO2;
    private ItemDTO itemDTO3;

    private List<ItemDTO> itemsDTO;

    @BeforeEach
    void setUp() {
        itemDTO1 = Builders.firstItemDTO();
        itemDTO1.setCreator(Builders.firstUserDTO());

        itemDTO2 = Builders.secondItemDTO();
        UserDTO userDTO2 = Builders.secondUserDTO();
        userDTO2.setItemsDeactivated(List.of(itemDTO2));
        itemDTO2.setCreator(userDTO2);
        itemDTO2.setDeactivationUser(userDTO2);

        itemDTO3 = Builders.thirdItemDTO();
        itemDTO3.setCreator(Builders.firstUserDTO());

        itemsDTO = List.of(itemDTO1, itemDTO2, itemDTO3);
    }

    @Test
    void getItemsWithOutItemStateRequestParam() throws Exception {
        when(itemService.findAll()).thenReturn(itemsDTO);
        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/items")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(3)))
        .andExpect(jsonPath("$[0].itemCode", Matchers.is(1)))
        .andExpect(jsonPath("$[1].itemCode", Matchers.is(2)))
        .andExpect(jsonPath("$[2].itemCode", Matchers.is(3)));
    }

    @Test
    void getItemsWithItemStateRequestParam() throws Exception {
        when(itemService.findByState(ItemStateEnum.ACTIVE)).thenReturn(List.of(itemDTO1, itemDTO3));
        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/items?state=ACTIVE")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(2)))
        .andExpect(jsonPath("$[0].itemCode", Matchers.is(Math.toIntExact(itemDTO1.getItemCode()))))
        .andExpect(jsonPath("$[1].itemCode", Matchers.is(Math.toIntExact(itemDTO3.getItemCode()))));

        when(itemService.findByState(ItemStateEnum.DISCONTINUED)).thenReturn(List.of(itemDTO2));
        mockMvc.perform(MockMvcRequestBuilders
            .get("/api/items?state=DISCONTINUED")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(1)))
        .andExpect(jsonPath("$[0].itemCode", Matchers.is(Math.toIntExact(itemDTO2.getItemCode()))));
    }

    @Test
    void getItemByItemCode() throws Exception {
        when(itemService.findByItemCode(itemDTO1.getItemCode())).thenReturn(itemDTO1);
        mockMvc.perform(MockMvcRequestBuilders
            .get(String.format("/api/items/%s", itemDTO1.getItemCode()))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", Matchers.is(Math.toIntExact(itemDTO1.getId()))))
        .andExpect(jsonPath("$.itemCode", Matchers.is(Math.toIntExact(itemDTO1.getItemCode()))))
        .andExpect(jsonPath("$.description", Matchers.is(itemDTO1.getDescription())))
        .andExpect(jsonPath("$.price", Matchers.is(itemDTO1.getPrice())))
        .andExpect(jsonPath("$.itemState", Matchers.is(itemDTO1.getItemState().name())))
        .andExpect(jsonPath("$.creationDate", Matchers.is(itemDTO1.getCreationDate().toString())))
        .andExpect(jsonPath("$.creator.id", Matchers.is(Math.toIntExact(itemDTO1.getCreator().getId()))))
        .andExpect(jsonPath("$.priceReductions", Matchers.hasSize(itemDTO1.getPriceReductions().size())))
        .andExpect(jsonPath("$.suppliers", Matchers.hasSize(itemDTO1.getSuppliers().size())))
        .andExpect(jsonPath("$.deactivationReason", Matchers.is(itemDTO1.getDeactivationReason())))
        .andExpect(jsonPath("$.deactivationUser", Matchers.is(itemDTO1.getDeactivationUser())));
    }

    @Test
    void getItemByItemCodeNotFound() throws Exception {
        when(itemService.findByItemCode(itemDTO1.getItemCode())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders
            .get(String.format("/api/items/%s", itemDTO1.getItemCode()))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }

    @Test
    void createNotExistingItem() throws Exception {
        itemDTO1.setId(null);
        when(itemService.createItem(itemDTO1)).thenReturn(1L);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
            .post("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(itemDTO1));
        mockMvc.perform(mockRequest)
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$", Matchers.notNullValue()))
            .andExpect(jsonPath("$", Matchers.is(0)));

        itemDTO2.setId(null);
        when(itemService.createItem(itemDTO2)).thenReturn(2L);
        mockRequest = MockMvcRequestBuilders
            .post("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(itemDTO2));
        mockMvc.perform(mockRequest)
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$", Matchers.notNullValue()))
            .andExpect(jsonPath("$", Matchers.is(0)));
    }

    @Test
    void createItemWithItemCodeNull() throws Exception {
        itemDTO1.setId(null);
        itemDTO1.setItemCode(null);
        mockMvc.perform(MockMvcRequestBuilders
            .post("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(itemDTO1)))
        .andExpect(status().isBadRequest());
    }

    @Test
    void createItemWithNoDescription() throws Exception {
        itemDTO1.setId(null);
        itemDTO1.setDescription(null);
        mockMvc.perform(MockMvcRequestBuilders
            .post("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(itemDTO1)))
        .andExpect(status().isBadRequest());

        itemDTO1.setDescription("");
        mockMvc.perform(MockMvcRequestBuilders
            .post("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(itemDTO1)))
        .andExpect(status().isBadRequest());
    }

    @Test
    void createAlreadyExistingItem() throws Exception {
        itemDTO3.setId(null);
        when(itemService.createItem(itemDTO3)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders
            .post("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(itemDTO3)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$", Matchers.nullValue()));
    }

    @Test
    void createPriceReductionToItemNotFound() throws Exception {
        when(itemService.findByItemCode(itemDTO1.getItemCode())).thenReturn(null);
        PriceReductionDTO priceReductionDTO = Builders.firstPriceReductionDTO();
        priceReductionDTO.setId(null);
        mockMvc.perform(MockMvcRequestBuilders
            .post(String.format("/api/items/%s/priceReductions", itemDTO1.getItemCode()))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(priceReductionDTO)))
        .andExpect(status().isNotFound());
    }

    @Test
    void createPriceReduction() throws Exception {
        when(itemService.findByItemCode(itemDTO1.getItemCode())).thenReturn(itemDTO1);
        PriceReductionDTO priceReductionDTO = Builders.firstPriceReductionDTO();
        priceReductionDTO.setId(null);
        ItemDTO itemUpdated = Builders.firstItemDTO();
        itemUpdated.setCreator(Builders.firstUserDTO());
        itemUpdated.addPriceReduction(priceReductionDTO);
        when(itemService.addPriceReduction(itemDTO1.getItemCode(), priceReductionDTO)).thenReturn(itemUpdated);

        mockMvc.perform(MockMvcRequestBuilders
            .post(String.format("/api/items/%s/priceReductions", itemDTO1.getItemCode()))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(priceReductionDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("S.priceReductions", Matchers.hasSize(itemUpdated.getPriceReductions().size())))
        .andExpect(jsonPath("$.priceReductions[0].reducedPrice",
            Matchers.is(priceReductionDTO.getReducedPrice())))
        .andExpect(jsonPath("$.priceReductions[0].startDate",
            Matchers.is(priceReductionDTO.getStartDate().toString())))
        .andExpect(jsonPath("$.priceReductions[0].endDate",
            Matchers.is(priceReductionDTO.getEndDate().toString())));
    }

    @Test
    void associateSupplierToItemNotFound() throws Exception {
        when(itemService.findByItemCode(itemDTO1.getItemCode())).thenReturn(null);
        SupplierDTO supplierDTO = Builders.firstSupplierDTO();
        supplierDTO.setItems(Set.of(itemDTO1));
        itemDTO1.setSuppliers(Set.of(supplierDTO));
        mockMvc.perform(MockMvcRequestBuilders
            .post(String.format("/api/items/%s/suppliers", itemDTO1.getItemCode()))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(supplierDTO)))
        .andExpect(status().isNotFound());
    }

    @Test
    void associateNewSupplierToItem() throws Exception {
        when(itemService.findByItemCode(itemDTO1.getItemCode())).thenReturn(itemDTO1);
        SupplierDTO supplierDTO = Builders.firstSupplierDTO();
        supplierDTO.setItems(Set.of(itemDTO1));
        itemDTO1.setSuppliers(Set.of(supplierDTO));
        when(itemService.associateNewSupplier(itemDTO1.getItemCode(), supplierDTO)).thenReturn(itemDTO1);
        mockMvc.perform(MockMvcRequestBuilders
            .post(String.format("/api/items/%s/suppliers", itemDTO1.getItemCode()))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(supplierDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.suppliers", Matchers.hasSize(1)))
        .andExpect(jsonPath("$.suppliers.name", Matchers.is(supplierDTO.getName())))
        .andExpect(jsonPath("$.suppliers.country", Matchers.is(supplierDTO.getCountry())));
    }

    @Test
    void updateItem() throws Exception {
        ItemDTO itemDTOUpdated = Builders.firstItemDTO();
        itemDTOUpdated.setDescription("Description updated");
        itemDTOUpdated.setPrice(85);
        itemDTOUpdated.setCreator(Builders.secondUserDTO());
        itemDTOUpdated.setCreationDate(LocalDate.of(2020, 2, 28));

        when(itemService.updateItem(itemDTOUpdated)).thenReturn(itemDTO1);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
            .put("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(itemDTOUpdated));

        mockMvc.perform(mockRequest)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", Matchers.notNullValue()))
            .andExpect(jsonPath("$.id", Matchers.is(Math.toIntExact(itemDTO1.getId()))))
            .andExpect(jsonPath("$.itemCode", Matchers.is(Math.toIntExact(itemDTO1.getItemCode()))))
            .andExpect(jsonPath("$.description", Matchers.is(itemDTOUpdated.getDescription())))
            .andExpect(jsonPath("$.price", Matchers.is(itemDTOUpdated.getPrice())))
            .andExpect(jsonPath("$.itemState", Matchers.is(itemDTO1.getItemState().name())))
            .andExpect(jsonPath("$.creationDate", Matchers.is(itemDTOUpdated.getCreationDate().toString())))
            .andExpect(jsonPath("$.creator.id", Matchers.is(
                Math.toIntExact(itemDTOUpdated.getCreator().getId()))))
            .andExpect(jsonPath("$.priceReductions", Matchers.hasSize(itemDTO1.getPriceReductions().size())))
            .andExpect(jsonPath("$.suppliers", Matchers.hasSize(itemDTO1.getSuppliers().size())))
            .andExpect(jsonPath("$.deactivationReason", Matchers.is(itemDTO1.getDeactivationReason())))
            .andExpect(jsonPath("$.deactivationUser.id", Matchers.is(
                Math.toIntExact(itemDTO1.getDeactivationUser().getId()))));
    }

    @Test
    void updateItemWithItemCodeNull() throws Exception {
        ItemDTO itemDTOUpdated = Builders.firstItemDTO();
        itemDTOUpdated.setItemCode(null);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(itemDTOUpdated));

        mockMvc.perform(mockRequest)
            .andExpect(status().isBadRequest());
    }

    @Test
    void updateItemWithNoDescription() throws Exception {
        ItemDTO itemDTOUpdated = Builders.firstItemDTO();
        itemDTOUpdated.setDescription("");

        mockMvc.perform(MockMvcRequestBuilders
            .put("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(itemDTOUpdated)))
        .andExpect(status().isBadRequest());

        itemDTOUpdated.setDescription(null);

        mockMvc.perform(MockMvcRequestBuilders
            .put("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(itemDTOUpdated)))
        .andExpect(status().isBadRequest());
    }

    @Test
    void updateDeactivatedItem() throws Exception {
        ItemDTO itemDTOUpdated = Builders.secondItemDTO();
        itemDTOUpdated.setDescription("Description updated");

        mockMvc.perform(MockMvcRequestBuilders
            .put("/api/items")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(itemDTOUpdated)))
        .andExpect(status().isBadRequest());
    }

    @Test
    void deactivateActiveItemFound() throws Exception {
        when(itemService.findById(itemDTO1.getId())).thenReturn(itemDTO1);
        ItemDTO itemDeactivated = Builders.firstItemDTO();
        itemDeactivated.setItemState(ItemStateEnum.DISCONTINUED);
        itemDeactivated.setDeactivationReason("Reason X");
        UserDTO userDTO = Builders.firstUserDTO();
//        userDTO.setItemsDeactivated(List.of(itemDeactivated));
        itemDeactivated.setCreator(userDTO);
        itemDeactivated.setDeactivationUser(userDTO);
        when(itemService.deactivateItem(itemDeactivated)).thenReturn(1);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
            .put("/api/items/deactivated")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(itemDeactivated));
        mockMvc.perform(mockRequest)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.itemState", Matchers.is(itemDeactivated.getItemState().name())))
            .andExpect(jsonPath("$.deactivationReason", Matchers.is(itemDeactivated.getDeactivationReason())))
            .andExpect(jsonPath("$.deactivationUser.id", Matchers.is(
                Math.toIntExact(itemDeactivated.getDeactivationUser().getId()))));
    }

    @Test
    void deactivateDiscontinuedItem() throws Exception {
        when(itemService.findById(itemDTO2.getId())).thenReturn(itemDTO2);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
            .put("/api/items/deactivated")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(itemDTO2));
        mockMvc.perform(mockRequest)
            .andExpect(status().isBadRequest());
    }

    @Test
    void deactivateItemNotFound() throws Exception {
        when(itemService.findById(itemDTO2.getId())).thenReturn(null);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
            .put("/api/items/deactivated")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(itemDTO1));
        mockMvc.perform(mockRequest)
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteItemFound() throws Exception {
        when(itemService.findById(itemDTO1.getId())).thenReturn(itemDTO1);
        mockMvc.perform(MockMvcRequestBuilders
            .delete(String.format("/api/items/%s", itemDTO1.getId()))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    void deleteItemNotFound() throws Exception {
        when(itemService.findById(50L)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders
            .delete(String.format("/api/items/%s", 50L))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    }

}