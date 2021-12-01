package com.sparta.delivery.controller;

import com.sparta.delivery.dto.FoodDto;
import com.sparta.delivery.models.UserRoleEnum;
import com.sparta.delivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @Secured(UserRoleEnum.Authority.USER)
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodDto> getFoods(@PathVariable Long restaurantId){
        return foodService.getFoods(restaurantId);
    }

    @Secured(UserRoleEnum.Authority.OWNER)
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void addFood(@PathVariable Long restaurantId, @RequestBody List<FoodDto> foodDtos){
        foodService.addFood(restaurantId, foodDtos);
    }
}
