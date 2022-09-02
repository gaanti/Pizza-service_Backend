package com.example.pizza.pizza.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class OrderHeader extends Order{

    /*@Transient
    private List<OrderedPizzasRepository> orderedPizzas;*/
    private int orderCost;

}
