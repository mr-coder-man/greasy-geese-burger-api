package com.greasy.geese.controller;

import com.greasy.geese.model.FoodOrder;
import com.greasy.geese.model.Menu;
import com.greasy.geese.model.OrderRequest;
import com.greasy.geese.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreaseController {

    @Autowired
    private Menu menu;
    @Autowired
    private OrderService orderService;

    public GreaseController(Menu menu, OrderService orderService){
        this.menu = menu;
        this.orderService = orderService;
    }

    @GetMapping("/menu")
    private Menu menuItems(){
        return menu;
    }

    @PostMapping("/order")
    public FoodOrder foodOrder(@RequestBody OrderRequest orderRequest){
        return orderService.handleOrder(orderRequest);
    }

}
