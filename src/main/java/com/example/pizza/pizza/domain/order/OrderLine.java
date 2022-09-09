package com.example.pizza.pizza.domain.order;

import com.example.pizza.pizza.domain.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OrderLine extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL)
    private OrderedPizza orderedPizza;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_header_id")
    private OrderHeader orderHeader;

}
