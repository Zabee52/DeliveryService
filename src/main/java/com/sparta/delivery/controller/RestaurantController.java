package com.sparta.delivery.controller;

import com.sparta.delivery.dto.RestaurantRequestDto;
import com.sparta.delivery.dto.RestaurantResponseDto;
import com.sparta.delivery.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<RestaurantResponseDto> getRestaurants(@RequestParam int x, @RequestParam int y){
        return restaurantService.getRestaurants(x, y);
    }

    @PostMapping("/restaurant/register")
    public RestaurantResponseDto registerRestaurant(@RequestBody RestaurantRequestDto restaurantDto){
        return restaurantService.resgisterRestaurant(restaurantDto);
    }
}
