package com.greasy.geese.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greasy.geese.model.MenuItem;
import com.greasy.geese.model.OrderRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GreaseControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ArrayList<String> itemsListExpected;

    @BeforeEach
    public void before(){
        itemsListExpected = new ArrayList<>();
        // set menu items map up
        itemsListExpected.add("Single Cheese Burger");
        itemsListExpected.add("Double Cheese Burger");
        itemsListExpected.add("Fries");
        itemsListExpected.add("Hot Dog");
        itemsListExpected.add("Fountain Soda");
    }

    @Test
    void returnsMenuItems() throws Exception {
        MockHttpServletRequestBuilder requestBuilder
                = get("/menu")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        // get entire string from response
        String responseAsString = result.getResponse().getContentAsString();
        // check to make sure the response contains all menu items
        itemsListExpected.forEach(item -> {
            assert(responseAsString.contains(item));
        });
    }

    @Test
    void orderRequestCalculatedAndReturnsOrder() throws Exception {
        // post request because we're creating something, and sending a body
        // technically it could be POST, PUT or PATCH but really POST makes the most
        // sense here
        MockHttpServletRequestBuilder requestBuilder
                = post("/order")
                .content(new ObjectMapper()
                        .writeValueAsString(
                                OrderRequest.builder()
                                        .menuItems(Arrays.asList(MenuItem.builder()
                                                .menuItemName("Single Cheese Burger")
                                                .menuItemPrice(5.00)
                                                .build(),MenuItem.builder()
                                                .menuItemName("Double Cheese Burger")
                                                .menuItemPrice(7.25)
                                                .build())
                                        )
                                        .build()))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        String responseAsString = result.getResponse().getContentAsString();
        assert(responseAsString.contains("12.25"));
        assert(responseAsString.contains("Single Cheese Burger"));
        assert(responseAsString.contains("Double Cheese Burger"));
    }

    @Test
    void orderRequestCalculatedAndReturnsZeroWhenNoItemsOnOrder() throws Exception {
        // post request because we're creating something, and sending a body
        // technically it could be POST, PUT or PATCH but really POST makes the most
        // sense here
        MockHttpServletRequestBuilder requestBuilder
                = post("/order")
                .content(new ObjectMapper()
                        .writeValueAsString(
                                OrderRequest.builder()
                                        .build()))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        String responseAsString = result.getResponse().getContentAsString();
        assert(responseAsString.contains("0"));
    }

}
