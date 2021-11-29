package com.sparta.delivery.dto;

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
}
