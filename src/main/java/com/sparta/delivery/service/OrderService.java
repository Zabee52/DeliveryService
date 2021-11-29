package com.sparta.delivery.service;

import com.sparta.delivery.dto.OrderFoodsRequestDto;
import com.sparta.delivery.dto.OrderMenuRequestDto;
import com.sparta.delivery.models.Food;
import com.sparta.delivery.models.OrderFood;
import com.sparta.delivery.models.OrderMenu;
import com.sparta.delivery.models.Restaurant;
import com.sparta.delivery.repository.FoodRepository;
import com.sparta.delivery.repository.OrderFoodRepository;
import com.sparta.delivery.repository.OrderMenuRepository;
import com.sparta.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMenuRepository orderMenuRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderFoodRepository orderFoodRepository;

    public OrderMenu requestOrder(OrderMenuRequestDto orderMenuRequestDto) {
        // 레스토랑 ID로 레스토랑 이름 탐색
        Restaurant restaurant = restaurantRepository.findById(orderMenuRequestDto.getRestaurantId()).orElse(null);
        // 유효하지 않은 레스토랑 정보일 경우 에러 발생
        if (restaurant == null) {
            throw new NullPointerException("유효하지 않은 음식점입니다.");
        }

        // 식품 목록 생성
        List<OrderFood> foods = new ArrayList<>();

        // 전체 가격 계산. 배달비 포함하기 때문에 기본값으로 추가.
        int totalPrice = restaurant.getDeliveryFee();

        // 유효성 검사
        for (OrderFoodsRequestDto orderFoodsRequestDto : orderMenuRequestDto.getFoods()) {
            // 수량 조건 맞지 않을 경우 에러 발생
            int quantity = orderFoodsRequestDto.getQuantity();
            if (quantity < 1 || quantity > 100) {
                throw new IllegalArgumentException("수량은 1개 이상 100개 이하로 입력해주세요.");
            }

            Food food = foodRepository.findById(orderFoodsRequestDto.getId()).orElseThrow(
                    () -> new NullPointerException("유효하지 않은 음식입니다.")
            );

            totalPrice += food.getPrice();

            // OrderFoods 생성 및 리스트에 주입
            foods.add(new OrderFood(quantity, food, restaurant));
        }

        if (totalPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("최소주문금액(" + restaurant.getMinOrderPrice() + "원) 이상 주문하셔야 합니다.");
        }

        OrderMenu orderMenu = orderMenuRepository.save(new OrderMenu(totalPrice, restaurant));

        for(OrderFood orderFood : foods){
            orderFood.setOrderMenu(orderMenu);
            orderFoodRepository.save(orderFood);
        }

        return orderMenu;
    }

    public List<OrderMenu> getOrders() {
        return orderMenuRepository.findAll();
    }
}
