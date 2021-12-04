package com.sparta.delivery.dto;

import com.sparta.delivery.models.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodResponseDto {
    private Long id;
    private String name;
    private int price;
    private List<FoodOptionResponseDto> option;

    public FoodResponseDto(Food food, List<FoodOptionResponseDto> option) {
        this.id = food.getId();
        this.name = food.getName();
        this.price = food.getPrice();
        this.option = option;
    }
}