package com.greasy.geese.initializer;

import com.greasy.geese.model.Menu;
import com.greasy.geese.model.MenuItem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class Initializer {

    @Bean
    public Menu getMenu(){
        ArrayList<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Single Cheese Burger",5.0));
        items.add(new MenuItem("Double Cheese Burger",7.25));
        items.add(new MenuItem("Fries",3.00));
        items.add(new MenuItem("Hot Dog",5.00));
        items.add(new MenuItem("Fountain Soda",1.00));
        return new Menu(items);
    }

}
