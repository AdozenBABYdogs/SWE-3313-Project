package com.japapohns.japapohnsapi.order;

import com.japapohns.japapohnsapi.menu.MenuService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {
    private final MenuService menuService;

    public OrderService(MenuService menuService){
        this.menuService = menuService;
    }

    public BigDecimal calculateTotal(CreateOrderRequest request){
        BigDecimal price = menuService.getMenuItemById(request.menuItemId())
                .orElseThrow(() -> new IllegalArgumentException("Menu item not found"))
                .price();
        return price.multiply(BigDecimal.valueOf(request.quantity()));
    }
}
