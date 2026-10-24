package com.japapohns.japapohnsapi.menu;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    private final List<MenuItemResponse> menuItems = new ArrayList<>();

    public MenuService(){
        menuItems.add(new MenuItemResponse(1L, "Cheese Pizza", new BigDecimal("10.99")));
        menuItems.add(new MenuItemResponse(2L, "Pepperoni Pizza", new BigDecimal("12.99")));
        menuItems.add(new MenuItemResponse(3L, "Soda", new BigDecimal("2.49")));
    }

    public List<MenuItemResponse> getAllMenuItems(){
        return menuItems;
    }

    public Optional<MenuItemResponse> getMenuItemById(long id){
        return menuItems.stream().filter(menuItem -> menuItem.id().equals(id)).findFirst();
    }

}
