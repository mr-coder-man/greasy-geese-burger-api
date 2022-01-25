package com.greasy.geese.service;

import com.greasy.geese.model.FoodOrder;
import com.greasy.geese.model.Menu;
import com.greasy.geese.model.MenuItem;
import com.greasy.geese.model.OrderRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class OrderService {

    @Autowired
    private Menu menu;

    public FoodOrder handleOrder(OrderRequest orderRequest){
        double total = calculateOrder(orderRequest);
        if(total > 0.0){
            // successful order
            return new FoodOrder(orderRequest.getMenuItems(),total);
        }else{
            // nothing added to order
            return new FoodOrder();
        }
    }

    private double calculateOrder(OrderRequest orderRequest){
        // all or none calculation, if one item is bad, throw out the order
        // could also be handled as a cherry-pick strategy, only throwing out bad items
        double runningTotal = 0.0;
        if(validateItemsAreReal(orderRequest)){
            List<MenuItem> itemsToOrder = orderRequest.getMenuItems();
            runningTotal = itemsToOrder.stream().mapToDouble(MenuItem::getMenuItemPrice).sum();
        }
        return runningTotal;
    }

    private boolean validateItemsAreReal(OrderRequest orderRequest){
        for(MenuItem menuItem : orderRequest.getMenuItems()){
            if(!menu.getMenuItems().contains(menuItem)){
                return false;
            }
        }
        return true;
    }

}