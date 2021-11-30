package com.sparta.delivery.controller;

import com.sparta.delivery.dto.FoodDto;
import com.sparta.delivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodDto> getFoods(@PathVariable Long restaurantId){
        return foodService.getFoods(restaurantId);
    }

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void addFood(@PathVariable Long restaurantId, @RequestBody List<FoodDto> foodDtos){
        foodService.addFood(restaurantId, foodDtos);
    }
}
