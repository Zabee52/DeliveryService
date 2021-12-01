package com.sparta.delivery.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrdersRequestDto {
    private Long restaurantId;
    private List<OrderFoodsRequestDto> foods;
    private int x;
    private int y;
}

