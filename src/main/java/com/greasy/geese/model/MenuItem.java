package com.greasy.geese.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuItem {

    private String menuItemName;
    private double menuItemPrice;

}
