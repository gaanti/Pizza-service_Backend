package com.example.pizza.pizza.domain;

import com.example.pizza.pizza.domain.order.OrderedPizza;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class DoughRadius{
    @Id
    int radius;
}
