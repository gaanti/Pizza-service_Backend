package com.example.pizza.pizza.domain;

import com.example.pizza.pizza.domain.order.OrderedPizza;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class DoughWidth{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    String doughWidthTitle;
}
