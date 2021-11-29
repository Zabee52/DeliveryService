package com.sparta.delivery.repository;

import com.sparta.delivery.models.OrderFood;
import com.sparta.delivery.models.OrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderFoodRepository extends JpaRepository<OrderFood, Long> {
    List<OrderFood> findAllByOrderMenu(OrderMenu orderMenu);
}
