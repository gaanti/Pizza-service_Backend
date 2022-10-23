package com.example.pizza.payments;

import com.example.pizza.pizza.domain.Ingredients;
import com.example.pizza.pizza.domain.Pizzas;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false")
public class PaymentsController {
	@PostMapping("/checkout")
	@CrossOrigin
	public static ResponseEntity<String> checkout(@RequestBody String data, HttpServletRequest request) throws StripeException, IOException {
		Stripe.apiKey = "sk_test_51Luj1gE3rpIhISk0ZfABsQsLQrwic0zUvYJu7dGBzwEdStyqPqeX3uLJw8TIOk9ELCV8HmibLydyCkKgAz422Tsk00prapWWEe";
		String YOUR_DOMAIN = request.getHeader("Origin");
		ObjectMapper mapper = new ObjectMapper();
		Pizzas[] pizzas = mapper.readerFor(Pizzas[].class).readValue(data);

		List<SessionCreateParams.LineItem> orderedPizzas = new ArrayList<>();
		for (Pizzas pizza : pizzas) {
			Set<Ingredients> pizzaIngredients = pizza.getIngredients();
			String ingredientsStringList =
					pizzaIngredients
							.stream()
							.map(ingredients -> ingredients.getIngredientName().toLowerCase())
							.reduce("", (result, ingredient) -> result + " " + ingredient);


			SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
					.setPriceData(
							SessionCreateParams.LineItem.PriceData.builder()
									.setCurrency("usd")
									.setUnitAmount((long) (pizzas[0].getPrice() * 100))
									.setProductData(
											SessionCreateParams.LineItem.PriceData.ProductData.builder()
													.setName(pizza.getTitle())
													.setDescription(String.format("Pizza with %s", ingredientsStringList))
													.build()
									)
									.build()
					)
					.setQuantity((long) pizza.getQuantity())
					.build();
			orderedPizzas.add(lineItem);
		}

		SessionCreateParams params = SessionCreateParams.builder()
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl(YOUR_DOMAIN + "?success=true")
				.setCancelUrl(YOUR_DOMAIN + "?canceled=true")
				.addAllLineItem(orderedPizzas)
				.build();

		Session session = Session.create(params);
		return ResponseEntity.ok().header("Content-Type", "application/json").body(session.getUrl());
	}

	@RequestMapping("/order-fulfilled")
	public static ResponseEntity<String> odrderFulFilled(@RequestBody Object data) throws Exception {
		String responseDescription = "Got payload: " + ((Map) data).get("id");
		System.out.println(responseDescription);
		return new ResponseEntity<>(responseDescription, HttpStatus.OK);
	}
}
