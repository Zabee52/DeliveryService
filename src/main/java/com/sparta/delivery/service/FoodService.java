package com.sparta.delivery.service;

import com.sparta.delivery.dto.FoodOptionRequestDto;
import com.sparta.delivery.dto.FoodOptionResponseDto;
import com.sparta.delivery.dto.FoodRequestDto;
import com.sparta.delivery.dto.FoodResponseDto;
import com.sparta.delivery.models.Food;
import com.sparta.delivery.models.FoodOption;
import com.sparta.delivery.models.Restaurant;
import com.sparta.delivery.repository.FoodOptionRepository;
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
    private final FoodOptionRepository foodOptionRepository;

    // 음식점에 음식 정보를 추가하는 메소드
    public void addFood(Long restaurantId, List<FoodRequestDto> foodDtos) {
        // 레스토랑 정보 유효한지 검사
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (!restaurant.isPresent()) {
            throw new NullPointerException("유효하지 않은 음식점입니다.");
        }

        // 음식점별 음식을 추가하는 메소드
        List<Food> foodList = new ArrayList<>();
        List<FoodOption> foodOptionList = new ArrayList<>();
        for (FoodRequestDto foodDto : foodDtos) {
            // 유효성 검사
            foodDtoValidCheck(restaurantId, foodDto, foodList);
            // 음식 등록
            Food food = new Food(restaurant.get(), foodDto);
            foodList.add(food);

            // 음식 옵션 등록
            for (FoodOptionRequestDto foodOptionRequestDto : foodDto.getOption()) {
                FoodOption foodOption = new FoodOption(foodOptionRequestDto, food);
                foodOptionList.add(foodOption);
            }
        }

        if (foodDtos.size() > 0) {
            List<Food> foodResultList = foodRepository.saveAll(foodList);
            foodOptionSetting(foodResultList, foodOptionList);

            foodOptionRepository.saveAll(foodOptionList);
        } else {
            throw new IllegalArgumentException("음식을 입력해주세요.");
        }
    }

    // 음식점에 음식 정보를 추가할 때, 옵션이 있을 경우 옵션도 같이 추가해주는 메소드
    private void foodOptionSetting(List<Food> foodResultList, List<FoodOption> foodOptionList) {
        for (Food food : foodResultList) {
            for(FoodOption foodOption : foodOptionList){
                if(food.getName().equals(foodOption.getFood().getName())){
                    foodOption.setFood(food);
                    break;
                }
            }
        }
    }

    // 특정 음식점의 음식 목록을 불러오는 메소드
    public List<FoodResponseDto> getFoods(Long restaurantId) {
        // 음식점별 음식 목록을 출력하는 메소드
        // 음식점의 음식 정보 리스트 호출
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        // 반환
        return foodDtoListSetting(foods);
    }

    private List<FoodResponseDto> foodDtoListSetting(List<Food> foods) {
        List<FoodResponseDto> result = new ArrayList<>();
        for (Food food : foods) {
            result.add(new FoodResponseDto(
                    food.getId(),
                    food.getName(),
                    food.getPrice(),
                    foodOptionResponseDtoListSetting(food)));
        }
        return result;
    }

    private List<FoodOptionResponseDto> foodOptionResponseDtoListSetting(Food food) {
        List<FoodOptionResponseDto> result = new ArrayList<>();

        for(FoodOption foodOption : food.getFoodOptionList()){
            result.add(new FoodOptionResponseDto(
                    foodOption.getOption(),
                    foodOption.getPrice()));
        }

        return result;
    }

    private void foodDtoValidCheck(Long restaurantId, FoodRequestDto foodDto, List<Food> foodList) {
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
