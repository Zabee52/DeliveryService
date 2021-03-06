package com.sparta.delivery.controller;

import com.sparta.delivery.dto.OrdersRequestDto;
import com.sparta.delivery.dto.OrdersResponseDto;
import com.sparta.delivery.models.UserRoleEnum;
import com.sparta.delivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @Secured(UserRoleEnum.Authority.USER)
    @GetMapping("/orders")
    public List<OrdersResponseDto> getOrders() {
        return orderService.getOrders();
    }

    @Secured(UserRoleEnum.Authority.USER)
    @PostMapping("/order/request")
    public OrdersResponseDto requestOrder(@RequestBody OrdersRequestDto orderMenuRequestDto) {
        return orderService.requestOrder(orderMenuRequestDto);
    }
}
