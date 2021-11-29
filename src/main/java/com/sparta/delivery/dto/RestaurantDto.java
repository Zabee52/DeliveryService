package com.sparta.delivery.dto;

import lombok.Getter;

@Getter
public class RestaurantDto {
    String name;
    int minOrderPrice;
    int deliveryFee;
}
