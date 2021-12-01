package com.sparta.delivery.repository;

import com.sparta.delivery.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAllByXBetweenAndYBetween(int lesserX, int greaterX, int lesserY, int greaterY);
}
