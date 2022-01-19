package com.greasy.geese.model;

import lombok.*;

import java.util.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    private List<MenuItem> menuItems;

}
