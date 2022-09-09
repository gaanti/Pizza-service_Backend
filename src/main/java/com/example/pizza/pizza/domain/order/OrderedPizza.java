package com.example.pizza.pizza.domain.order;

import com.example.pizza.pizza.domain.BaseEntity;
import com.example.pizza.pizza.domain.DoughRadius;
import com.example.pizza.pizza.domain.DoughWidth;
import com.example.pizza.pizza.domain.Pizzas;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderedPizza extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "pizza_id", referencedColumnName="id")
	private Pizzas pizza;
	@OneToOne
	private DoughWidth doughWidth;
	@OneToOne
	private DoughRadius doughRadius;
	private int quantity;
	@ManyToOne
	private OrderLine orderLine;

}
