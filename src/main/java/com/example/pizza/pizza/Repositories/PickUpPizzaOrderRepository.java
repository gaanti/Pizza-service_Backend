package com.example.pizza.pizza.Repositories;

import com.example.pizza.pizza.domain.order.PickUpOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickUpPizzaOrderRepository extends JpaRepository<PickUpOrder, Long> {
}
