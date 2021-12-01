package com.sparta.delivery.service;

import com.sparta.delivery.dto.OrderFoodsRequestDto;
import com.sparta.delivery.dto.OrderFoodsResponseDto;
import com.sparta.delivery.dto.OrderMenuRequestDto;
import com.sparta.delivery.dto.OrderMenuResponseDto;
import com.sparta.delivery.models.Food;
import com.sparta.delivery.models.OrderFood;
import com.sparta.delivery.models.Orders;
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

    public OrderMenuResponseDto requestOrder(OrderMenuRequestDto orderMenuRequestDto) {
        // 레스토랑 ID로 레스토랑 이름 탐색
        Restaurant restaurant = restaurantRepository.findById(orderMenuRequestDto.getRestaurantId()).orElse(null);
        // 유효하지 않은 레스토랑 정보일 경우 에러 발생
        if (restaurant == null) {
            throw new NullPointerException("유효하지 않은 음식점입니다.");
        }

        // 식품 목록 생성
        List<OrderFood> foods = new ArrayList<>();
        List<OrderFoodsResponseDto> foodsDto = new ArrayList<>();

        // 전체 가격 계산.
        int totalPrice = 0;

        // 유효성 검사 후 foodsDto에 데이터를 삽입
        for (OrderFoodsRequestDto orderFoodsRequestDto : orderMenuRequestDto.getFoods()) {
            // 수량 조건 맞지 않을 경우 에러 발생
            int quantity = orderFoodsRequestDto.getQuantity();
            if (quantity < 1 || quantity > 100) {
                throw new IllegalArgumentException("수량은 1개 이상 100개 이하로 입력해주세요.");
            }

            Food food = foodRepository.findById(orderFoodsRequestDto.getId()).orElseThrow(
                    () -> new NullPointerException("유효하지 않은 음식입니다.")
            );
            // 출력용 Dto 생성
            OrderFoodsResponseDto orderFoodsResponseDto = new OrderFoodsResponseDto(food.getName(),
                    quantity,
                    food.getPrice() * quantity);

            // 전체 가격에 합산.
            totalPrice += orderFoodsResponseDto.getPrice();
            // OrderFood 생성 및 리스트에 주입
            foods.add(new OrderFood(quantity, food));
            foodsDto.add(orderFoodsResponseDto);
        }

        if (totalPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("최소주문금액(" + restaurant.getMinOrderPrice() + "원) 이상 주문하셔야 합니다.");
        }
        // 배달비 추가.
        int deliveryFee = deliveryFeeCalcProc(restaurant, orderMenuRequestDto.getX(), orderMenuRequestDto.getY());
        totalPrice += deliveryFee;

        Orders orderMenu = orderMenuRepository.save(new Orders(restaurant, totalPrice, deliveryFee));

        for (OrderFood orderFood : foods) {
            orderFood.setOrderMenu(orderMenu);
            orderFoodRepository.save(orderFood);
        }

        return new OrderMenuResponseDto(
                restaurant.getName(),
                foodsDto,
                deliveryFee,
                totalPrice);
    }

    private int deliveryFeeCalcProc(Restaurant restaurant, int x, int y) {
        // 1. 배달비 받기
        int result = restaurant.getDeliveryFee();
        // 2. 거리별 배달비 합산. 현재는 1km(정수 1)당 500원씩 할증됨.
        result += (Math.abs(restaurant.getX() - x) + Math.abs(restaurant.getY() - y)) * 500;

        return result;
    }

    public List<OrderMenuResponseDto> getOrders() {
        return getOrderMenuResponseDtos(orderMenuRepository.findAll());
    }

    private List<OrderMenuResponseDto> getOrderMenuResponseDtos(List<Orders> orderMenuList) {
        List<OrderMenuResponseDto> result = new ArrayList<>();

        for (Orders orderMenu : orderMenuList) {
            // OrderMenuResponseDto 구성
            // name, Foods, deliveryFee, totalPrice
            result.add(new OrderMenuResponseDto(
                    orderMenu.getRestaurant().getName(),
                    getOrderFoodsResponseDtos(orderMenu),
                    orderMenu.getDeliveryFee(),
                    orderMenu.getTotalPrice()));
        }

        return result;
    }

    private List<OrderFoodsResponseDto> getOrderFoodsResponseDtos(Orders orderMenu) {
        List<OrderFoodsResponseDto> result = new ArrayList<>();

        List<OrderFood> orderFoodList = orderFoodRepository.findAllByOrderMenu(orderMenu);
        for (OrderFood orderFood : orderFoodList) {
            String foodName = orderFood.getFood().getName();
            int quantity = orderFood.getQuantity();
            int price = quantity * orderFood.getFood().getPrice();

            result.add(new OrderFoodsResponseDto(foodName, quantity, price));
        }

        return result;
    }
}
