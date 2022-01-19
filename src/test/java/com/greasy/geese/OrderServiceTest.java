package com.greasy.geese;

import com.greasy.geese.model.FoodOrder;
import com.greasy.geese.model.Menu;
import com.greasy.geese.model.MenuItem;
import com.greasy.geese.model.OrderRequest;
import com.greasy.geese.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setupService(){
        orderService = new OrderService(Menu.builder().menuItems(
                Collections.singletonList(
                        new MenuItem("fries", 1.0))).build());
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

}
