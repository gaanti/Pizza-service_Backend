package com.example.pizza.payments;

import com.example.pizza.pizza.domain.Pizzas;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class PaymentsController {
	@PostMapping("/checkout")
	public static RedirectView checkout(@RequestBody String data, HttpServletResponse response) throws StripeException, IOException {
		Stripe.apiKey = "sk_test_51Luj1gE3rpIhISk0ZfABsQsLQrwic0zUvYJu7dGBzwEdStyqPqeX3uLJw8TIOk9ELCV8HmibLydyCkKgAz422Tsk00prapWWEe";
		String YOUR_DOMAIN = "https://pizza-service-5vz9-imarrfcls-gaanti.vercel.app/";
		ObjectMapper mapper = new ObjectMapper();
		Pizzas[] pizzas = mapper.readerFor(Pizzas[].class).readValue(data);

		List<SessionCreateParams.LineItem> orderedPizzas = new ArrayList<>();
		//Fulfill products list
		for (Pizzas pizza : pizzas) {
			//Define the product
			SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
					.setPriceData(
							SessionCreateParams.LineItem.PriceData.builder()
									.setCurrency("usd")
									.setUnitAmount((long) (pizzas[0].getPrice() * 100))
									.setProductData(
											SessionCreateParams.LineItem.PriceData.ProductData.builder()
													.setName(pizza.getTitle())
													.setDescription(String.format("Pizza with %s", pizza.getIngredients().toString()))
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
		response.setStatus(303);
		response.sendRedirect(session.getUrl());*/

		return new RedirectView(session.getUrl());
	}
}
