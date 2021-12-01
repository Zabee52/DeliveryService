package com.sparta.delivery.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class OrderFood {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Orders orderMenu;

    public OrderFood(int quantity, Food food) {
        this.quantity = quantity;
        this.food = food;
    }

    public void setOrderMenu(Orders orderMenu) {
        this.orderMenu = orderMenu;
    }
}
