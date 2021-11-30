package com.sparta.delivery.controller;

import com.sparta.delivery.dto.RestaurantDto;
import com.sparta.delivery.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<RestaurantDto> getRestaurants(){
        return restaurantService.getRestaurants();
    }

    @PostMapping("/restaurant/register")
    public RestaurantDto registerRestaurant(@RequestBody RestaurantDto restaurantDto){
        return restaurantService.resgisterRestaurant(restaurantDto);
    }
}
