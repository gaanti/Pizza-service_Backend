package com.example.pizza;

import com.example.pizza.pizza.Repositories.*;
import com.example.pizza.pizza.domain.*;
import com.example.pizza.pizza.domain.order.DeliveryOrder;
import com.example.pizza.pizza.domain.order.OrderHeader;
import com.example.pizza.pizza.domain.order.OrderedPizza;
import com.example.pizza.pizza.domain.order.PickUpOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PizzasApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    PickUpPizzaOrderRepository pickUpPizzaOrderRepository;

    @Autowired
    DeliveryOrderRepository deliveryPizzaOrderRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    OrderedPizzasRepository orderedPizzasRepository;

	ObjectMapper objectMapper = new ObjectMapper();


	@Test
    @Transactional
    void testRepository() {
        DeliveryOrder SAVED_deliveryPizzaOrder = deliveryPizzaOrderRepository.save(new DeliveryOrder());
        PickUpOrder SAVED_pickUpPizzaOrder = pickUpPizzaOrderRepository.save(new PickUpOrder());

        List<Pizzas> GETTED_pizzas = pizzaRepository.findAll();
        Categories GETTED_categories = categoriesRepository.getReferenceById(1L);

        assertNotNull(GETTED_pizzas);
        assertNotNull(GETTED_categories);
        assertNotNull(SAVED_deliveryPizzaOrder);
        assertNotNull(SAVED_pickUpPizzaOrder);
    }

    @Test
	@Disabled
    void TestGetPizzas() throws JsonProcessingException {
        @Setter
        @Getter
        @AllArgsConstructor
        @NoArgsConstructor
        class POPO {
            Page<Pizzas> pizzas;
            List<Categories> categories;
        }
        Gson gson = new Gson();
        Pageable pageable1 = PageRequest.of(1, 8, Sort.by("Price").descending());
        Page<Pizzas> pizzas = pizzaRepository.findAll(pageable1);
        List<Categories> categories = categoriesRepository.findAll();
        POPO popo =new POPO(pizzas, categories);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = objectMapper.writeValueAsString(popo);
        assertNotNull(pizzas);
        assertNotNull(categories);
        assertNotNull(json);
    }

	/*@Test
	@Transactional
	void testDbReferences() throws JSONException, JsonProcessingException {

		JSONObject FIRST__PIZZA = new JSONObject();
		FIRST__PIZZA.put("title", "Half & Half pizza");
		FIRST__PIZZA.put("price", 666);
		FIRST__PIZZA.put("doughType", "Thin");
		FIRST__PIZZA.put("size", 30);
		FIRST__PIZZA.put("quantity", 3);

		JSONObject SECOND__PIZZA = new JSONObject();
		SECOND__PIZZA.put("title", "Anton's Test");
		SECOND__PIZZA.put("price", 48);
		SECOND__PIZZA.put("doughType", "Thin");
		SECOND__PIZZA.put("size", 30);
		SECOND__PIZZA.put("quantity", 3);

		OrderHeader orderHeader = new OrderHeader();
		orderHeader.setOrderCost(484848);
		OrderHeader SAVED_orderHeader = orderHeaderRepository.saveAndFlush(orderHeader);

		////////
		OrderedPizza orderedPizzas = new ObjectMapper().readValue(FIRST__PIZZA.toString(), OrderedPizza.class);
		OrderedPizza orderedPizzas1 = new ObjectMapper().readValue(SECOND__PIZZA.toString(), OrderedPizza.class);

		orderedPizzas.setOrderHeader(SAVED_orderHeader);
		orderedPizzas1.setOrderHeader(SAVED_orderHeader);

		List<OrderedPizzas> orderedPizzasSet = new ArrayList<>();
		orderedPizzasSet.add(orderedPizzas);
		orderedPizzasSet.add(orderedPizzas1);

		orderedPizzasRepository.saveAll(orderedPizzasSet);

		Set<OrderedPizzas> FOUND_ORDERED_PIZZA_BY_ID= orderedPizzasRepository.findAllByOrderHeader(orderHeaderRepository.getReferenceById(1L));
		Set<OrderedPizzas> FOUND_ORDERED_PIZZA_BY_TITLE= orderedPizzasRepository.findAllByTitle("Anton's Test");
		assertNotNull(FOUND_ORDERED_PIZZA_BY_ID);
		assertNotNull(FOUND_ORDERED_PIZZA_BY_TITLE);
		////////


		DeliveryPizzaOrder deliveryPizzaOrder = new DeliveryPizzaOrder();
		deliveryPizzaOrder.setOrderHeader(SAVED_orderHeader);
		deliveryPizzaOrder.setCustomerName("Lida");
		deliveryPizzaOrder.setAddress(new Address("Vakhnyanina 3", "Striy"));
		deliveryPizzaOrder.setDeliveryCost(48);
		deliveryPizzaOrder.setContactMethod("By phone");

		DeliveryPizzaOrder deliveryPizzaOrder_SAVED = deliveryPizzaOrderRepository.saveAndFlush(deliveryPizzaOrder);
		assertNotNull(deliveryPizzaOrder_SAVED);
	}*/

	@Setter
	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	class SERVER_RESPONSE {
		Page<Pizzas> pizzas;
		List<Categories> categories;
	}
	@Test
	@Transactional
	void name() {
		List<Pizzas> pizzas = pizzaRepository.findAllByCategoryId(2L);
		assertNotNull(pizzas);
		assertNotNull(pizzas.get(0));
		assertNotNull((pizzas.get(0)).getTitle());
		assertNotNull((pizzas.get(0)).getPrice());
		assertNotNull(pizzas.get(1));
		assertNotNull((pizzas.get(1)).getTitle());
		assertNotNull((pizzas.get(1)).getPrice());
	}
	@Test
	@Transactional
	void name1() throws JsonProcessingException {
		Optional<Categories> temp = categoriesRepository.findById(1L);
		Categories ctg = temp.orElse(null);
		Pageable pageable1 = PageRequest.of(0, 8, Sort.by("price").descending());
		String popo = objectMapper.writeValueAsString(new SERVER_RESPONSE(pizzaRepository.findAllByCategoryId(ctg.getId(), pageable1), categoriesRepository.findAll()));
		List<Pizzas> pizzas = pizzaRepository.findAllByCategoryId(2L);
		assertNotNull(pizzas);
		assertNotNull(pizzas.get(0));
		assertNotNull((pizzas.get(0)).getTitle());
		assertNotNull((pizzas.get(0)).getPrice());
		assertNotNull(pizzas.get(1));
		assertNotNull((pizzas.get(1)).getTitle());
		assertNotNull((pizzas.get(1)).getPrice());
	}
}
