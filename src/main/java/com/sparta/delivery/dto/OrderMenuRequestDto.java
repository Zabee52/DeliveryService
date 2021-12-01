package com.sparta.delivery.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderMenuRequestDto {
    private Long restaurantId;
    private List<OrderFoodsRequestDto> foods;
    private int x;
    private int y;
}

