package com.example.pizza;

import com.example.pizza.pizza.PizzaDatabaseController;
import com.example.pizza.pizza.Repositories.*;
import com.example.pizza.pizza.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PizzaApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    PickUpPizzaOrderRepository pickUpPizzaOrderRepository;

    @Autowired
    DeliveryPizzaOrderRepository deliveryPizzaOrderRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    OrderedPizzasRepository orderedPizzasRepository;

    @Test
    @Transactional
    void testRepository() {
        DeliveryPizzaOrder SAVED_deliveryPizzaOrder = deliveryPizzaOrderRepository.save(new DeliveryPizzaOrder());
        PickUpPizzaOrder SAVED_pickUpPizzaOrder = pickUpPizzaOrderRepository.save(new PickUpPizzaOrder());

        List<Pizza> GETTED_pizza = pizzaRepository.findAll();
        Categories GETTED_categories = categoriesRepository.getReferenceById(1L);

        assertNotNull(GETTED_pizza);
        assertNotNull(GETTED_categories);
        assertNotNull(SAVED_deliveryPizzaOrder);
        assertNotNull(SAVED_pickUpPizzaOrder);
    }

    @Test
    void TestGetPizzas() throws JsonProcessingException {
        @Setter
        @Getter
        @AllArgsConstructor
        @NoArgsConstructor
        class POPO {
            Page<Pizza> pizzas;
            List<Categories> categories;
        }
        Gson gson = new Gson();
        Pageable pageable1 = PageRequest.of(1, 8, Sort.by("Price").descending());
        Page<Pizza> pizzas = pizzaRepository.findAll(pageable1);
        List<Categories> categories = categoriesRepository.findAll();
        POPO popo =new POPO(pizzas, categories);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = objectMapper.writeValueAsString(popo);
        assertNotNull(pizzas);
        assertNotNull(categories);
        assertNotNull(json);
    }

    @Test
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
        OrderedPizzas orderedPizzas = new ObjectMapper().readValue(FIRST__PIZZA.toString(), OrderedPizzas.class);
        OrderedPizzas orderedPizzas1 = new ObjectMapper().readValue(SECOND__PIZZA.toString(), OrderedPizzas.class);

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
    }
}