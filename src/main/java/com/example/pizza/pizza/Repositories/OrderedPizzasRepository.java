package com.example.pizza.pizza.Repositories;

import com.example.pizza.pizza.domain.OrderHeader;
import com.example.pizza.pizza.domain.OrderedPizzas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderedPizzasRepository extends JpaRepository<OrderedPizzas, Long> {
Set<OrderedPizzas> findAllByOrderHeader(OrderHeader orderHeader);
Set<OrderedPizzas> findAllByTitle(String title);
}
