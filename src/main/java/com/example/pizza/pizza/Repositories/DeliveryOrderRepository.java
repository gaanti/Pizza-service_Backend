package com.example.pizza.pizza.Repositories;

import com.example.pizza.pizza.domain.order.delivery.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {
}
