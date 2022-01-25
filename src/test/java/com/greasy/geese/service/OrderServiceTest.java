package com.greasy.geese.service;

import com.greasy.geese.model.FoodOrder;
import com.greasy.geese.model.Menu;
import com.greasy.geese.model.MenuItem;
import com.greasy.geese.model.OrderRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setupService(){
        orderService = new OrderService(Menu.builder().menuItems(
                Arrays.asList(new MenuItem("fries", 1.0),
                        new MenuItem("Single Cheese Burger", 5.00),
                        new MenuItem("Double Cheese Burger", 7.25))
                        ).build());
    }

    @Test
    void returnsValidOrderObject(){
        FoodOrder order = orderService.handleOrder(
                new OrderRequest(
                        new ArrayList<>(
                                Collections.singleton(new MenuItem("fries", 1.0))
                        )
                )
        );
        assert(order.getCalculation()==1.0);
        assert(order.getMenuItems().contains(new MenuItem("fries",1.0)));
    }

    @Test
    void orderRequestCalculatedAndReturnsOrder(){
        OrderRequest request = OrderRequest.builder()
                .menuItems(Arrays.asList(MenuItem.builder()
                        .menuItemName("Single Cheese Burger")
                        .menuItemPrice(5.00)
                        .build(),MenuItem.builder()
                        .menuItemName("Double Cheese Burger")
                        .menuItemPrice(7.25)
                        .build()))
                .build();
        FoodOrder order = orderService.handleOrder(request);
        assert(!order.getMenuItems().isEmpty());
        assert(order.getCalculation()==12.25);
       // here you could also test the items are on the order - I leave this task to you
    }

}