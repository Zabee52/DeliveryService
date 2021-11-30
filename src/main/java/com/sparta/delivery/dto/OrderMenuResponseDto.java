package com.sparta.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenuResponseDto {
    private String restaurantName;
    private List<OrderFoodsResponseDto> foods;
    private int deliveryFee;
    private int totalPrice;
}
