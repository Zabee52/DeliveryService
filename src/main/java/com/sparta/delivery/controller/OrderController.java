package com.sparta.delivery.controller;

import com.sparta.delivery.dto.OrderMenuRequestDto;
import com.sparta.delivery.dto.OrderMenuResponseDto;
import com.sparta.delivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/orders")
    public List<OrderMenuResponseDto> getOrders() {
        return orderService.getOrders();
    }

    @PostMapping("/order/request")
    public OrderMenuResponseDto requestOrder(@RequestBody OrderMenuRequestDto orderMenuRequestDto) {
        return orderService.requestOrder(orderMenuRequestDto);
    }
}
