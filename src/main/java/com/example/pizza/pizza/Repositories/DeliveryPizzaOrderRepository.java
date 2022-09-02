package com.example.pizza.pizza.Repositories;

import com.example.pizza.pizza.domain.DeliveryPizzaOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryPizzaOrderRepository extends JpaRepository<DeliveryPizzaOrder, Long> {
}
