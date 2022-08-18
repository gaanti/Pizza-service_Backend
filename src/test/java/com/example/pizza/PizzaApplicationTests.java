package com.example.pizza;

import com.example.pizza.pizza.Category;
import com.example.pizza.pizza.DoughType;
import com.example.pizza.pizza.Pizza;
import com.example.pizza.pizza.PizzaRepository;
import org.json.JSONException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PizzaApplicationTests {
	@Autowired
	PizzaRepository pizzaRepository;

	@Test
	void contextLoads() {
	}

	@Test
	@Disabled
	void readAndCreateImage() throws IOException {
		Path path = Paths.get(
				"/Users/juliagaskevich/IdeaProjects/XchangeTries/Second/backend/Pizza/src/main/java/com/example/pizza/pizza/pzimt.jpeg");
		byte[] arr = Files.readAllBytes(path);
		System.out.println(Arrays.toString(arr));
		System.out.println(arr.length);


		BufferedImage img = ImageIO.read(new ByteArrayInputStream(arr));
		System.out.println("img = " + img.createGraphics());
		ImageIO.write(img, "jpg", new File("output.jpg"));
		System.out.println("image created");
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
		pizza.setRating(9);
		Pizza saved = pizzaRepository.saveAndFlush(pizza);
	}

	@Test
	void pageble() {
		Pageable pageable = PageRequest.of(0, 3, Sort.by("price").ascending());
		assertThat(pizzaRepository.findAll(pageable).getTotalPages()).isGreaterThan(2);
	}
}