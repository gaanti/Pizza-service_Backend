package com.example.pizza;

import com.example.pizza.pizza.Repositories.*;
import com.example.pizza.pizza.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    @Transactional
    @Disabled
    void testRepository() {
        DeliveryPizzaOrder SAVED_deliveryPizzaOrder = deliveryPizzaOrderRepository.save(new DeliveryPizzaOrder());
        PickUpPizzaOrder SAVED_pickUpPizzaOrder = pickUpPizzaOrderRepository.save(new PickUpPizzaOrder());

        Pizza GETTED_pizza = pizzaRepository.getReferenceById(1L);
        Categories GETTED_categories = categoriesRepository.getReferenceById(1L);

        assertNotNull(GETTED_pizza);
        assertNotNull(GETTED_categories);
        assertNotNull(SAVED_deliveryPizzaOrder);
        assertNotNull(SAVED_pickUpPizzaOrder);
    }


    @Test
    @Transactional
    @Commit
    void testDbReferences() throws JSONException, JsonProcessingException {

        JSONObject array = new JSONObject();
        array.put("title", "Half & Half pizza");
        array.put("price", 666);
        array.put("doughType", "Thin");
        array.put("size", 30);
        array.put("quantity", 3);

        JSONObject SECOND__PIZZA = new JSONObject();
        SECOND__PIZZA.put("title", "Anton's Test");
        SECOND__PIZZA.put("price", 48);
        SECOND__PIZZA.put("doughType", "Thin");
        SECOND__PIZZA.put("size", 30);
        SECOND__PIZZA.put("quantity", 3);

        ////////
        OrderedPizza orderedPizza = new ObjectMapper().readValue(array.toString(), OrderedPizza.class);
        OrderedPizza orderedPizza1 = new ObjectMapper().readValue(SECOND__PIZZA.toString(), OrderedPizza.class);
        List<OrderedPizza> orderedPizzaSet = new ArrayList<>();
        orderedPizzaSet.add(orderedPizza);
        orderedPizzaSet.add(orderedPizza1);
        ////////

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setOrderedPizzas(orderedPizzaSet);
        OrderHeader SAVED_orderHeader = orderHeaderRepository.saveAndFlush(orderHeader);

        DeliveryPizzaOrder deliveryPizzaOrder = new DeliveryPizzaOrder();
        deliveryPizzaOrder.setOrderHeader(SAVED_orderHeader);

        DeliveryPizzaOrder deliveryPizzaOrder_SAVED =
                deliveryPizzaOrderRepository.saveAndFlush(deliveryPizzaOrder);
        assertNotNull(deliveryPizzaOrder_SAVED);
    }
}