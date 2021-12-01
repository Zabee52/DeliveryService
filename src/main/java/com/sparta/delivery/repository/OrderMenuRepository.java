package com.sparta.delivery.repository;

import com.sparta.delivery.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<Orders, Long> {
}
