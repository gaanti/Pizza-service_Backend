package com.example.pizza.pizza.Repositories;

import com.example.pizza.pizza.domain.Pizzas;
import com.example.pizza.pizza.domain.order.OrderLine;
import com.example.pizza.pizza.domain.order.OrderedPizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderedPizzasRepository extends JpaRepository<OrderedPizza, Long> {
Set<OrderedPizza> findAllByOrderLine(OrderLine orderLine);
Set<OrderedPizza> findAllByPizza(Pizzas pizzas);
}
