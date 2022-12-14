package com.example.pizza.payments;

import com.example.pizza.pizza.Repositories.DeliveryOrderRepository;
import com.example.pizza.pizza.Repositories.OrderRepository;
import com.example.pizza.pizza.Repositories.PickUpPizzaOrderRepository;
import com.example.pizza.pizza.Repositories.PizzaRepository;
import com.example.pizza.pizza.domain.Address;
import com.example.pizza.pizza.domain.order.ORDER_STATUS;
import com.example.pizza.pizza.domain.order.OrderHeader;
import com.example.pizza.pizza.domain.order.OrderedPizza;
import com.example.pizza.pizza.domain.order.delivery.DeliveryOrder;
import com.example.pizza.pizza.domain.order.pickUp.PickUpOrder;
import com.example.pizza.pizza.domain.pizza.Pizzas;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.stripe.Stripe;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.net.ApiResource;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/checkout")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false")
public class PaymentsController {
	@Autowired
	PickUpPizzaOrderRepository pickUpPizzaOrderRepository;
	@Autowired
	DeliveryOrderRepository deliveryOrderRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	PizzaRepository pizzaRepository;
	@Getter
	@Setter
	@ToString
	static class OrderPickUpPizza {
		String contactPerson;
		String notifyField;
		String notifyMethod;
		String timeToBeDone;
		OrderedPizza[] pizzas;
	}

	@Getter
	@Setter
	@ToString
	static class OrderDeliveryPizza extends OrderPickUpPizza {
		Address deliveryAddress;
	}

	public List<SessionCreateParams.LineItem> fillOrderWithItems(OrderPickUpPizza data) {
		List<SessionCreateParams.LineItem> orderedPizzas = new ArrayList<>();
		for (OrderedPizza pizza : data.getPizzas()) {
			Optional<Pizzas> optionalPizzaFromDB = pizzaRepository.findById(pizza.getPizza_id());
			if (optionalPizzaFromDB.isPresent()) {
				Pizzas pizzaFromDB = optionalPizzaFromDB.get();
				Set<String> pizzaIngredients = pizza.getIngredients();
				String ingredientsStringList =
						pizzaIngredients
								.stream()
								.map(String::toLowerCase)
								.reduce("", (result, ingredient) -> result + " " + ingredient);


				SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
						.setPriceData(
								SessionCreateParams.LineItem.PriceData.builder()
										.setCurrency("usd")
										.setUnitAmount((long) (pizzaFromDB.getPrice() * 100))
										.setProductData(
												SessionCreateParams.LineItem.PriceData.ProductData.builder()
														.setName(pizzaFromDB.getTitle())
														.setDescription(String.format("Pizza with %s", ingredientsStringList))
														.build()
										)
										.build()
						)
						.setQuantity((long) pizza.getQuantity())
						.build();
				orderedPizzas.add(lineItem);
			} else break;
		}
		return orderedPizzas;
	}

	@PostMapping("/delivery")
	@CrossOrigin
	public ResponseEntity<String> checkoutDelivery(@RequestBody OrderDeliveryPizza data, HttpServletRequest request) throws Exception {
		Stripe.apiKey = "sk_test_51Luj1gE3rpIhISk0ZfABsQsLQrwic0zUvYJu7dGBzwEdStyqPqeX3uLJw8TIOk9ELCV8HmibLydyCkKgAz422Tsk00prapWWEe";
		String YOUR_DOMAIN = request.getHeader("Origin");

		List<SessionCreateParams.LineItem> orderedPizzas = fillOrderWithItems(data);

		SessionCreateParams params = SessionCreateParams.builder()
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl(YOUR_DOMAIN + "?success=true")
				.setCancelUrl(YOUR_DOMAIN + "?canceled=true")
				.addAllLineItem(orderedPizzas)
				.build();

//		Order order = new Order();
		Session session = Session.create(params);
		DeliveryOrder deliveryOrder = new DeliveryOrder();
		deliveryOrder.setContactPerson(data.getContactPerson());
		deliveryOrder.setNotifyField(data.getNotifyField());
		deliveryOrder.setNotifyMethod(data.getNotifyMethod());
		deliveryOrder.setTimeToBeDone(data.getTimeToBeDone());
		deliveryOrder.setOrderCost(session.getAmountTotal());
		deliveryOrder.setOrderedPizza(Arrays.stream(data.getPizzas()).collect(Collectors.toSet()));
		deliveryOrder.setAddress(data.getDeliveryAddress());
		deliveryOrder.setPaymentId(session.getId());

		DeliveryOrder savedOrder = deliveryOrderRepository.save(deliveryOrder);
		System.out.println("savedPickupOrder = " + savedOrder.toString());
		return ResponseEntity.ok().header("Content-Type", "application/json").body(session.getUrl());
	}

	@PostMapping("/pickUp")
	@CrossOrigin
	public ResponseEntity<String> checkoutPickUp(@RequestBody OrderPickUpPizza data, HttpServletRequest request) throws Exception {
		Stripe.apiKey = "sk_test_51Luj1gE3rpIhISk0ZfABsQsLQrwic0zUvYJu7dGBzwEdStyqPqeX3uLJw8TIOk9ELCV8HmibLydyCkKgAz422Tsk00prapWWEe";
		String YOUR_DOMAIN = request.getHeader("Origin");

		List<SessionCreateParams.LineItem> orderedPizzas = fillOrderWithItems(data);

		SessionCreateParams params = SessionCreateParams.builder()
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl(YOUR_DOMAIN + "?success=true")
				.setCancelUrl(YOUR_DOMAIN + "?canceled=true")
				.addAllLineItem(orderedPizzas)
				.build();

//		Order order = new Order();
		Session session = Session.create(params);
		PickUpOrder pickUpOrder = new PickUpOrder();
		pickUpOrder.setContactPerson(data.getContactPerson());
		pickUpOrder.setNotifyField(data.getNotifyField());
		pickUpOrder.setNotifyMethod(data.getNotifyMethod());
		pickUpOrder.setTimeToBeDone(data.getTimeToBeDone());
		pickUpOrder.setOrderCost(session.getAmountTotal());
		pickUpOrder.setOrderedPizza(Arrays.stream(data.getPizzas()).collect(Collectors.toSet()));
		pickUpOrder.setPaymentId(session.getId());

		PickUpOrder savedOrder = pickUpPizzaOrderRepository.save(pickUpOrder);
		System.out.println("savedPickupOrder.toString() = " + savedOrder);
		return ResponseEntity.ok().header("Content-Type", "application/json").body(session.getUrl());
	}

	@RequestMapping("/fulfilled")
	public ResponseEntity<String> odrderFulFilled(@RequestBody String data) throws Exception {
		Stripe.apiKey = "sk_test_51Luj1gE3rpIhISk0ZfABsQsLQrwic0zUvYJu7dGBzwEdStyqPqeX3uLJw8TIOk9ELCV8HmibLydyCkKgAz422Tsk00prapWWEe";
		Event event = null;
		try {
			event = ApiResource.GSON.fromJson(data, Event.class);
		} catch (JsonSyntaxException e) {
			System.out.println("??????  Webhook error while parsing basic request.");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (Objects.equals(event.getType(), "checkout.session.completed")) {
			Event eventWithRightId = Event.retrieve(event.getId());
			Object JSON_WithRightId = ((eventWithRightId.getDataObjectDeserializer().getRawJson()));
			String Stringified_JSON_WithRightId = JSON_WithRightId.toString();
			String RightId = new ObjectMapper().readValue(Stringified_JSON_WithRightId, Map.class).get("id").toString();
			OrderHeader foundOrderHeader = orderRepository.findByPaymentId(RightId);
			foundOrderHeader.setStatus(ORDER_STATUS.FULFILLED);
			OrderHeader savedOrderHeader = orderRepository.save(foundOrderHeader);
			return new ResponseEntity<>(event.getId(), HttpStatus.OK);
		}
		return null;
	}
}
