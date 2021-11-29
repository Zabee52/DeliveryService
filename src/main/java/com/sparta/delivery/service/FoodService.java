package com.sparta.delivery.service;

import com.sparta.delivery.dto.FoodDto;
import com.sparta.delivery.mapping.FoodMapping;
import com.sparta.delivery.models.Food;
import com.sparta.delivery.models.Restaurant;
import com.sparta.delivery.repository.FoodRepository;
import com.sparta.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    public String addFood(Long restaurantId, List<FoodDto> foodDtos) {
        for(FoodDto foodDto : foodDtos){
            // 음식점별 음식을 추가하는 메소드
            String name = foodDto.getName();
            int price = foodDto.getPrice();

            // 레스토랑 정보 유효한지 검사
            Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
            if(restaurant == null){
                return "유효하지 않은 음식점입니다.";
            }

            // 유효성 검사
            Optional<Food> found = foodRepository.findByRestaurantIdAndName(restaurantId, name);
            if(found.isPresent()){
                return "같은 이름의 음식은 추가할 수 없습니다.";
            }else if(price < 100 || price > 1000000){
                return "음식 가격은 100원 이상 1,000,000원 이하로 입력해주세요.";
            }else if(price % 100 != 0){
                return "음식 가격은 100원 단위로 입력해주세요.";
            }
            // 음식 등록
            Food food = new Food(restaurant, foodDto);
            foodRepository.save(food);
        }

        if(foodDtos.size() > 0){
            return "등록 완료";
        }else{
            return "음식을 입력해주세요.";
        }
    }

    public List<FoodMapping> getFoods(Long restaurantId){
        // 음식점별 음식 목록을 출력하는 메소드
        return foodRepository.findByRestaurantId(restaurantId);
    }
}
