package com.sparta.delivery.controller;

import com.sparta.delivery.dto.RestaurantRequestDto;
import com.sparta.delivery.dto.RestaurantResponseDto;
import com.sparta.delivery.models.UserRoleEnum;
import com.sparta.delivery.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Secured(UserRoleEnum.Authority.USER)
    @GetMapping("/restaurants")
    public List<RestaurantResponseDto> getRestaurants(@RequestParam int x, @RequestParam int y){
        return restaurantService.getRestaurants(x, y);
    }

    @Secured(UserRoleEnum.Authority.USER)
    @GetMapping("/restaurants/{category}")
    public List<RestaurantResponseDto> getRestaurants(@PathVariable String category, @RequestParam int x, @RequestParam int y){
        return restaurantService.getRestaurantsByCategory(category.toUpperCase(), x, y);
    }

    @Secured(UserRoleEnum.Authority.OWNER)
    @PostMapping("/restaurant/register")
    public RestaurantResponseDto registerRestaurant(@RequestBody RestaurantRequestDto restaurantDto){
        return restaurantService.registerRestaurant(restaurantDto);
    }

    @Secured(UserRoleEnum.Authority.OWNER)
    @GetMapping("/restaurant/{restaurantId}/status")
    public String registerRestaurant(@PathVariable Long restaurantId, @RequestParam String status){
        return restaurantService.restaurantStatusChange(restaurantId, status);
    }
}
