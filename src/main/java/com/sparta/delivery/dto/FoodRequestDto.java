package com.sparta.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodRequestDto {
    private Long id;
    private String name;
    private int price;
    private String category;
    private List<FoodOptionRequestDto> option = new ArrayList<>();
}
