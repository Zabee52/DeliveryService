package com.sparta.delivery.controller;

import com.sparta.delivery.dto.FoodDto;
import com.sparta.delivery.mapping.FoodMapping;
import com.sparta.delivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodMapping> getFoods(@PathVariable Long restaurantId){
        return foodService.getFoods(restaurantId);
    }

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public String addFood(@PathVariable Long restaurantId, @RequestBody List<FoodDto> foodDtos){
        return foodService.addFood(restaurantId, foodDtos);
    }
}
