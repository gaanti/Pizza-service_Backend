package com.example.pizza.payments;

import com.example.pizza.pizza.Repositories.OrderRepository;
import com.example.pizza.pizza.domain.order.OrderHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrdersController {
	@Autowired
	OrderRepository orderRepository;

	/*@PostMapping("/active-orders")
	public List<OrderHeader> getActiveOrdersWithParams () {
		return orderRepository.findAll();
	}*/
	@GetMapping("/active-orders")
	public List<OrderHeader> getActiveOrders () {
		return orderRepository.findAll();
	}
}
