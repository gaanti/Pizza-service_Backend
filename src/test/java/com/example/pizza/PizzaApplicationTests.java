package com.example.pizza;

import com.example.pizza.pizza.Category;
import com.example.pizza.pizza.Pizza;
import com.example.pizza.pizza.PizzaDatabaseController;
import com.example.pizza.pizza.PizzaRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PizzaApplicationTests {
	@Autowired
	PizzaRepository pizzaRepository;
	Pageable pageable = PageRequest.of(0, 3, Sort.by("price").ascending());

	@Test
	void contextLoads() {
	}

	@Test
	@Disabled
	void addARow() {
		Pizza pizza = new Pizza();
		//pizza.setImage(Path.of("/Users/juliagaskevich/IdeaProjects/XchangeTries/Second/backend/Pizza/src/main/java/com/example/pizza/pizza/pzimt.jpeg"));
		pizza.setTitle("Test");
		pizza.setSize(new Integer[]{30,35,40});
		pizza.setCategory(Category.Grille);
		pizza.setDoughType("[\"Thin\", \"Traditional\"]");
		pizza.setPrice(400);
		Pizza saved = pizzaRepository.saveAndFlush(pizza);
	}

	@Test
	void pageble() {
		assertThat(pizzaRepository.findAll(pageable).getTotalPages()).isGreaterThan(2);
	}

	/*@Test
	void search() {
		assertThat(finded).isNotNull();
	}*/
}