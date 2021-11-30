package com.sparta.delivery.service;

import com.sparta.delivery.dto.RestaurantDto;
import com.sparta.delivery.models.Restaurant;
import com.sparta.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantDto resgisterRestaurant(RestaurantDto restaurantDto) {
        // 음식점 유효성 검사
        validCheck(restaurantDto);

        Restaurant restaurant = new Restaurant(restaurantDto);
        restaurantRepository.save(restaurant);

        return restaurantDtoSetting(restaurant);
    }

    public List<RestaurantDto> getRestaurants() {
        // 모든 음식점 목록을 출력하는 메소드
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        List<RestaurantDto> response = new ArrayList<>();

        for (Restaurant restaurant : restaurantList) {
            response.add(restaurantDtoSetting(restaurant));
        }

        return response;
    }

    private RestaurantDto restaurantDtoSetting(Restaurant restaurant) {
        return new RestaurantDto(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getMinOrderPrice(),
                restaurant.getDeliveryFee());
    }

    private void validCheck(RestaurantDto restaurantDto) {
        // 음식점을 등록하는 메소드
        int minOrderPrice = restaurantDto.getMinOrderPrice();
        int deliveryFee = restaurantDto.getDeliveryFee();

        // 유효성 검사
        if (minOrderPrice < 1000 || minOrderPrice > 100000) {
            throw new IllegalArgumentException("최소주문금액은 1,000원 이상 100,000원 이하로 입력해주세요.");
        } else if (minOrderPrice % 100 != 0) {
            throw new IllegalArgumentException("최소 입력단위는 100원입니다.");
        } else if (deliveryFee < 0 || deliveryFee > 10000) {
            throw new IllegalArgumentException("배달비는 0원 이상 10,000원 이하로 입력해주세요.");
        } else if (deliveryFee % 500 != 0) {
            throw new IllegalArgumentException("배달비는 500원 단위로 입력해주세요.");
        }
    }
}
