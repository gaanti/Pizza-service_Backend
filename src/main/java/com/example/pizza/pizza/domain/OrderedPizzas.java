package com.example.pizza.pizza.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Getter
@Setter
@Entity
public class OrderedPizzas extends Order{
    enum DoughType{
        Traditional, Thin
    }
    @ManyToOne
    private OrderHeader orderHeader;
    private String title;
    private int price;
    private DoughType doughType;
    private int size;
    private int quantity;
}
