package com.bbsw.bitboxer2.practica.restcontroller;

import com.bbsw.bitboxer2.practica.builder.Builders;
import com.bbsw.bitboxer2.practica.dto.ItemDTO;
import com.bbsw.bitboxer2.practica.service.ItemService;
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

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemRestController.class)
class ItemRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    private ItemDTO itemDTO1;
    private ItemDTO itemDTO2;
    private ItemDTO itemDTO3;

    private List<ItemDTO> itemsDTO;

    @BeforeEach
    void setUp() {
        itemDTO1 = Builders.firstItemDTO();
        itemDTO2 = Builders.secondItemDTO();
        itemDTO3 = Builders.thirdItemDTO();

        itemsDTO = List.of(itemDTO1, itemDTO2, itemDTO3);
    }

    @Test
    void getItems() throws Exception {
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
    void getItemByItemCode() {
    }

    @Test
    void createItem() throws Exception {
        when(itemService.createItem(itemDTO1)).thenReturn(itemDTO1.getId());
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(itemDTO1.toString());
        mockMvc.perform(mockRequest)
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$", Matchers.notNullValue()))
            .andExpect(jsonPath("$", Matchers.is(1L)));
    }

    @Test
    void createPriceReduction() {
    }

    @Test
    void associateSupplierToItem() {
    }

    @Test
    void updateItem() {
    }

    @Test
    void deactivateItem() {
    }

    @Test
    void deleteItem() {
    }
}