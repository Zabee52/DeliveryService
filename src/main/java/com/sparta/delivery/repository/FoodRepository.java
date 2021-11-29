package com.sparta.delivery.repository;

import com.sparta.delivery.mapping.FoodMapping;
import com.sparta.delivery.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<FoodMapping> findByRestaurantId(Long restaurantId);
    Optional<Food> findByRestaurantIdAndName(Long restaurantId, String name);
}
