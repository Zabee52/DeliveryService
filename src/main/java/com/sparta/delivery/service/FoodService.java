package com.sparta.delivery.service;

import com.sparta.delivery.dto.FoodDto;
import com.sparta.delivery.models.Food;
import com.sparta.delivery.models.Restaurant;
import com.sparta.delivery.repository.FoodRepository;
import com.sparta.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    public void addFood(Long restaurantId, List<FoodDto> foodDtos) {
        // 레스토랑 정보 유효한지 검사
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (!restaurant.isPresent()) {
            throw new NullPointerException("유효하지 않은 음식점입니다.");
        }

        // 음식점별 음식을 추가하는 메소드
        List<Food> foodList = new ArrayList<>();
        for (FoodDto foodDto : foodDtos) {
            // 유효성 검사
            foodDtoValidCheck(restaurantId, foodDto, foodList);
            // 음식 등록
            foodList.add(new Food(restaurant.get(), foodDto));
        }

        if (foodDtos.size() > 0) {
            foodRepository.saveAll(foodList);
        } else {
            throw new IllegalArgumentException("음식을 입력해주세요.");
        }
    }

    public List<FoodDto> getFoods(Long restaurantId) {
        // 음식점별 음식 목록을 출력하는 메소드
        // 1. 음식점의 음식 정보 리스트 호출
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
        // 2. 음식 정보들을 매핑해줄 Dto List 생성
        List<FoodDto> response = new ArrayList<>();

        for (Food food : foods) {
            // Dto List에 추가
            response.add(foodDtoSetting(food));
        }

        // 반환
        return response;
    }

    private FoodDto foodDtoSetting(Food food) {
        return new FoodDto(
                food.getId(),
                food.getName(),
                food.getPrice()
        );
    }

    private void foodDtoValidCheck(Long restaurantId, FoodDto foodDto, List<Food> foodList) {
        int price = foodDto.getPrice();

        Optional<Food> found = foodRepository.findByRestaurantIdAndName(restaurantId, foodDto.getName());
        if (found.isPresent()) {
            throw new IllegalArgumentException("같은 이름의 음식은 추가할 수 없습니다.");
        } else if (price < 100 || price > 1000000) {
            throw new IllegalArgumentException("음식 가격은 100원 이상 1,000,000원 이하로 입력해주세요.");
        } else if (price % 100 != 0) {
            throw new IllegalArgumentException("음식 가격은 100원 단위로 입력해주세요.");
        }
        for (Food food : foodList) {
            if (food.getName().equals(foodDto.getName())) {
                throw new IllegalArgumentException("같은 이름의 음식은 추가할 수 없습니다.");
            }
        }
    }
}
