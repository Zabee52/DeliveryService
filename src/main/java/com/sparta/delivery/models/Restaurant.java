package com.sparta.delivery.models;

import com.sparta.delivery.dto.RestaurantRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;

    @Column(nullable = false)
    private int x;

    @Column(nullable = false)
    private int y;

    @Column(nullable = false)
    private RestaurantStatusEnum restaurantStatusEnum;

    @OneToMany(mappedBy = "restaurant")
    private List<Food> foodList;

    public void setRestaurantStatusEnum(RestaurantStatusEnum restaurantStatusEnum) {
        this.restaurantStatusEnum = restaurantStatusEnum;
    }

    public Restaurant(RestaurantRequestDto restaurantDto) {
        this.name = restaurantDto.getName();
        this.minOrderPrice = restaurantDto.getMinOrderPrice();
        this.deliveryFee = restaurantDto.getDeliveryFee();
        this.x = restaurantDto.getX();
        this.y = restaurantDto.getY();
        this.restaurantStatusEnum = RestaurantStatusEnum.OPEN;
    }
}
