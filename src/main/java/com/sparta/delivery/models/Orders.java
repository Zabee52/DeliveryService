package com.sparta.delivery.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private int totalPrice;

    @Column(nullable = false)
    private int deliveryFee;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderFood> foods = new ArrayList<>();

    public Orders(String restaurantName, int totalPrice, int deliveryFee, List<OrderFood> foods) {
        this.restaurantName = restaurantName;
        this.totalPrice = totalPrice;
        this.deliveryFee = deliveryFee;
        this.foods = foods;
    }
}
