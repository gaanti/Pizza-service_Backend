package com.example.pizza.pizza.Repositories;

import com.example.pizza.pizza.domain.order.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
}
