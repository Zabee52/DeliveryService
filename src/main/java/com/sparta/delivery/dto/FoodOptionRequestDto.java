package com.sparta.delivery.dto;

import com.sparta.delivery.models.FoodOption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodOptionRequestDto {
    private String option;
    private int price;
}
