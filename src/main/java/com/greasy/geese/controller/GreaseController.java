package com.greasy.geese.controller;

import com.greasy.geese.model.FoodOrder;
import com.greasy.geese.model.Menu;
import com.greasy.geese.model.MenuItem;
import com.greasy.geese.model.OrderRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class GreaseController {

    @Autowired
    private Menu menu;

    @GetMapping("/menu")
    public Menu menuItems(){
        return menu;
    }

    @PostMapping("/order")
    public FoodOrder menuItems(@RequestBody OrderRequest orderRequest){
        return handleOrder(orderRequest);
    }

    // helper method to help validate, and calculate the order
    public FoodOrder handleOrder(OrderRequest orderRequest){
        // calculate the order total
        double total = calculateOrder(orderRequest);
        if(total > 0.0){
            // successful order
            return new FoodOrder(orderRequest.getMenuItems(),total);
        }else{
            // nothing added to order
            return new FoodOrder(Collections.emptyList(),0.00);
        }
    }

    private double calculateOrder(OrderRequest orderRequest){
        // all or none calculation, if one item is bad, throw out the order
        // could also be handled as a cherry-pick strategy, only throwing out bad items
        double runningTotal = 0.0;
        if(validateItemsAreReal(orderRequest)){
            List<MenuItem> itemsToOrder = orderRequest.getMenuItems();
            // this is a bit risky trusting the user's order item price
            // but imagine people are trustworthy in this world we can always fix this later
            runningTotal = itemsToOrder.stream().mapToDouble(MenuItem::getMenuItemPrice).sum();
        }
        return runningTotal;
    }

    private boolean validateItemsAreReal(OrderRequest orderRequest){
        // check that all the items are valid menu items my looping through and checking against
        // our list of items we only validate the items, not the price
        // oops we had a null pointer exception (NPE) this should take care of that!!
        List<MenuItem> menuItems = Optional.ofNullable(orderRequest.getMenuItems())
                .orElse(Collections.emptyList());
        // no need to do a calculation, something isn't right
        if(menuItems.isEmpty())
            return false;
        for(MenuItem menuItem :
                Optional.ofNullable(orderRequest.getMenuItems())
                        .orElse(Collections.emptyList())){
            if(!menu.getMenuItems().contains(menuItem)){
                return false;
            }
        }
        return true;
    }
}