package com.example.pizza.payments;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PaymentsController {
	@RequestMapping("/checkout")
	public static RedirectView chackout(@RequestBody(required = false) Object reqParams, RedirectAttributes attributes) throws StripeException {
		Stripe.apiKey = "sk_test_51Luj1gE3rpIhISk0ZfABsQsLQrwic0zUvYJu7dGBzwEdStyqPqeX3uLJw8TIOk9ELCV8HmibLydyCkKgAz422Tsk00prapWWEe";
		String YOUR_DOMAIN = "https://pizza-service-5vz9-imarrfcls-gaanti.vercel.app/";
		Map<String, Object> priceParams = new HashMap<>();
		priceParams.put("unit_amount", 2000);
		priceParams.put("currency", "usd");
		priceParams.put("product", "prod_MeLTZf9oSOX9qY");

		Price price = Price.create(priceParams);

		SessionCreateParams params =
				SessionCreateParams.builder()
						.setMode(SessionCreateParams.Mode.PAYMENT)
						.setSuccessUrl(YOUR_DOMAIN + "?success=true")
						.setCancelUrl(YOUR_DOMAIN + "?canceled=true")
						.addLineItem(
								SessionCreateParams.LineItem.builder()
										.setQuantity(1L)
										// Provide the exact Price ID (for example, pr_1234) of the product you want to sell
										.setPrice(price.getId())
										.build())
						.build();


		Session session = Session.create(params);
		attributes.addFlashAttribute("flashAttribute", "redirectWithRedirecvtView");
		attributes.addAttribute("attribute", "redirectWithRedirectView");
		return new RedirectView(session.getUrl());
	}
}
