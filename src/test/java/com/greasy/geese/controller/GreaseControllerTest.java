package com.greasy.geese.controller;

import org.junit.Before;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GreaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private GreaseController greaseController;
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
        String responseAsString = result.getResponse().getContentAsString();
        itemsListExpected.forEach(item -> {
            assert(responseAsString.contains(item));
        });
    }

}
