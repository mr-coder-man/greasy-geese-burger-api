package com.greasy.geese.controller;

import com.greasy.geese.model.Menu;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class GreaseController {

    @GetMapping("/menu")
    public Menu menuItems(){
        return null;
    }
}