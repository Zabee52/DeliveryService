package com.sparta.delivery.models;

import com.sparta.delivery.dto.FoodRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @Column
    private FoodCategoryEnum foodCategoryEnum;

    @OneToMany(cascade = CascadeType.ALL)
    List<FoodOption> foodOptionList = new ArrayList<>();

    public Food(Restaurant restaurant, FoodRequestDto foodDto) {
        this.restaurant = restaurant;
        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
        this.foodCategoryEnum = FoodCategoryEnum.valueOf(foodDto.getCategory());
    }

    public Food(Restaurant restaurant, FoodRequestDto foodDto, List<FoodOption> foodOptionList) {
        this.restaurant = restaurant;
        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
        this.foodCategoryEnum = FoodCategoryEnum.valueOf(foodDto.getCategory());
        this.foodOptionList = foodOptionList;
    }
}
