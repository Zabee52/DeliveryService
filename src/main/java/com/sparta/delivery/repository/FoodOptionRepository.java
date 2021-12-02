package com.sparta.delivery.repository;

import com.sparta.delivery.models.FoodOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOptionRepository extends JpaRepository<FoodOption, Long> {
}
