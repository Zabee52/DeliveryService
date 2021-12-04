package com.sparta.delivery.dto;

import com.sparta.delivery.models.OrderFood;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderFoodsResponseDto {
    String name;
    int quantity;
    int price;

    public OrderFoodsResponseDto(OrderFood orderFood) {
        this.name = orderFood.getName();
        this.quantity = orderFood.getQuantity();
        this.price = orderFood.getPrice();
    }
}
