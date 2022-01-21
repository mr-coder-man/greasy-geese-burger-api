package com.greasy.geese.controller;

import com.greasy.geese.model.Menu;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class GreaseController {

    @Autowired
    private Menu menu;

    @GetMapping("/menu")
    public Menu menuItems(){
        return menu;
    }
}