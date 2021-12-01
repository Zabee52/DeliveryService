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
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int totalPrice;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false)
    private int deliveryFee;

    @OneToMany(mappedBy = "orderMenu")
    private List<OrderFood> foods = new ArrayList<>();

    public OrderMenu(Restaurant restaurant, int totalPrice, int deliveryFee) {
        this.restaurant = restaurant;
        this.totalPrice = totalPrice;
        this.deliveryFee = deliveryFee;
    }
}
