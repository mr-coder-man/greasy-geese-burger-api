package com.greasy.geese.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodOrder {

    private List<MenuItem> menuItems;
    private Double calculation;

}
