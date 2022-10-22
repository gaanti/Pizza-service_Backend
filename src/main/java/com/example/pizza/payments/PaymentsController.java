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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false")
public class PaymentsController {
	@PostMapping("/checkout")
	@CrossOrigin
	public static ResponseEntity<String> checkout(@RequestBody String data, HttpServletRequest request) throws StripeException, IOException {
		Stripe.apiKey = "sk_test_51Luj1gE3rpIhISk0ZfABsQsLQrwic0zUvYJu7dGBzwEdStyqPqeX3uLJw8TIOk9ELCV8HmibLydyCkKgAz422Tsk00prapWWEe";
//		String YOUR_DOMAIN = "https://pizza-service-5vz9-imarrfcls-gaanti.vercel.app/";
		String YOUR_DOMAIN = request.getHeader("Origin");
		ObjectMapper mapper = new ObjectMapper();
		Pizzas[] pizzas = mapper.readerFor(Pizzas[].class).readValue(data);

		List<SessionCreateParams.LineItem> orderedPizzas = new ArrayList<>();
		//Fulfill products list
		for (Pizzas pizza : pizzas) {
			//Define the product
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
/*//		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
		response.setHeader(HttpHeaders.CONTENT_TYPE, "text/html; charset=utf-8; application/json");
		response.setStatus(303);*/
//		response.sendRedirect(session.getUrl());
//		return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(session.getUrl()))
//				.build();
//				httpServletResponse.setHeader("Content-Type","text/html; charset=utf-8; application/json; text/plain; application/x-www-form-urlencoded; multipart/form-data");
		return ResponseEntity.ok().header("Content-Type", "application/json").body(session.getUrl());
	}

	@PostMapping("/checkout2")
	@CrossOrigin
	public static ResponseEntity<Void> checkout2(HttpServletResponse response) throws StripeException, IOException {
		Stripe.apiKey = "sk_test_51Luj1gE3rpIhISk0ZfABsQsLQrwic0zUvYJu7dGBzwEdStyqPqeX3uLJw8TIOk9ELCV8HmibLydyCkKgAz422Tsk00prapWWEe";
		String YOUR_DOMAIN = "https://pizza-service-5vz9-imarrfcls-gaanti.vercel.app/";

		List<SessionCreateParams.LineItem> orderedPizzas = new ArrayList<>();
		//Fulfill products list
		for (int i = 5; i > 0; i--) {
			//Define the product
			SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
					.setPriceData(
							SessionCreateParams.LineItem.PriceData.builder()
									.setCurrency("usd")
									.setUnitAmount((long) (10000))
									.setProductData(
											SessionCreateParams.LineItem.PriceData.ProductData.builder()
													.setName("data")
													.setDescription(String.format("Pizza with %s", "data"))
													.build()
									)
									.build()
					)
					.setQuantity((long) 48)
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
		return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(session.getUrl()))
				.build();
	}

	@PostMapping("/checkout3")
	@CrossOrigin
	public static String checkout3(@RequestBody String data) throws StripeException {
		Stripe.apiKey = "sk_test_51Luj1gE3rpIhISk0ZfABsQsLQrwic0zUvYJu7dGBzwEdStyqPqeX3uLJw8TIOk9ELCV8HmibLydyCkKgAz422Tsk00prapWWEe";
		String YOUR_DOMAIN = "https://pizza-service-5vz9-imarrfcls-gaanti.vercel.app/";

		List<SessionCreateParams.LineItem> orderedPizzas = new ArrayList<>();
		//Fulfill products list
		for (int i = 4; i > 0; i--) {
			//Define the product
			SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
					.setPriceData(
							SessionCreateParams.LineItem.PriceData.builder()
									.setCurrency("usd")
									.setUnitAmount((long) (10000))
									.setProductData(
											SessionCreateParams.LineItem.PriceData.ProductData.builder()
													.setName(data)
													.setDescription(String.format("Pizza with %s", data))
													.build()
									)
									.build()
					)
					.setQuantity((long) 4)
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
		return session.getUrl();
	}
}
