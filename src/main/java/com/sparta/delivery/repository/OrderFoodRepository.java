package com.sparta.delivery.repository;

import com.sparta.delivery.models.OrderFood;
import com.sparta.delivery.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderFoodRepository extends JpaRepository<OrderFood, Long> {
//    List<OrderFood> findAllByOrderMenu(Orders orderMenu);
}
