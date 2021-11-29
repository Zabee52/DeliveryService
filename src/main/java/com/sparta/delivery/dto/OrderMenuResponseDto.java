package com.sparta.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenuResponseDto {
    String restaurantName;
    List<OrderFoodsResponseDto> foods;
    int deleveryFee;
    int totalPrice;
}
