package com.example.pizza.pizza;

import com.example.pizza.pizza.Repositories.*;
import com.example.pizza.pizza.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class PizzaDatabaseController {
	@Autowired
	PizzaRepository pizzaRepository;

	@Autowired
	CategoriesRepository categoriesRepository;

	@Autowired
	DoughWidthRepository doughWidthRepository;
	@Autowired
	DoughRadiusRepository doughRadiusRepository;

	@Autowired
	IngredientRepository ingredientRepository;


	ObjectMapper objectMapper = new ObjectMapper();

	Categories popo(Long filterByCategory) {
		System.out.println(filterByCategory);
		Optional<Categories> temp = categoriesRepository.findById(filterByCategory);
		System.out.println("temp = " + temp);
		return temp.orElse(null);
	}

	private Sort.Direction getSortDirection(String sortDirection) {
		if (sortDirection.equalsIgnoreCase("decrease")){
			return Sort.Direction.ASC;

		} else {
			return Sort.Direction.DESC;

		}
	}

	@GetMapping()
	String getAllPizzas(@RequestParam(required = false, name = "sortBy", defaultValue = "popularity") String sortBy,
						@RequestParam(required = false, name = "sortDirection", defaultValue = "decrease") String sortDirection,
						@RequestParam(required = false, name = "filterByCategoryId") Long filterByCategory,
						@RequestParam(required = false, name = "currentPage", defaultValue = "0") int currentPage,
						@RequestParam(required = false, name = "filterByTitle") String filterByTitle) throws JsonProcessingException {

		if (filterByCategory != null) {
			if (filterByCategory == 0){
				filterByCategory = null;
			}
		}
		int totalPagesQty;
		Sort.Direction  sortDirection1 = getSortDirection(sortDirection);
		Pageable pageable1 = PageRequest.of(currentPage, 8, Sort.by(sortDirection1,sortBy));

		@Setter
		@Getter
		@AllArgsConstructor
		@NoArgsConstructor
		class SERVER_RESPONSE {
			Page<Pizzas> pizzas;
			List<Categories> categories;
			List<DoughWidth> doughWidths;
			List<DoughRadius> doughRadius;
			List<Ingredients> ingredients;
		}
		if (filterByCategory != null){
			Categories ctg = popo(filterByCategory);
			if (ctg != null && filterByTitle != null) {
				totalPagesQty = pizzaRepository.findByTitleContainsIgnoreCaseAndCategoryId(filterByTitle, ctg.getId(), pageable1).getTotalPages();
				if (currentPage + 1 > totalPagesQty && totalPagesQty > 0) {
					pageable1 = PageRequest.of(totalPagesQty - 1, 8, Sort.by(sortDirection1, sortBy));
				}
				return objectMapper.writeValueAsString(new SERVER_RESPONSE(pizzaRepository.findByTitleContainsIgnoreCaseAndCategoryId(filterByTitle, ctg.getId(), pageable1), categoriesRepository.findAll(), doughWidthRepository.findAll(), doughRadiusRepository.findAll(), ingredientRepository.findAll()));
			} else {
				totalPagesQty = pizzaRepository.findAllByCategoryId(ctg.getId(), pageable1).getTotalPages();
				if (currentPage + 1 > totalPagesQty && totalPagesQty > 0) {
					pageable1 = PageRequest.of(totalPagesQty - 1, 8, Sort.by(sortDirection1,sortBy));
				}
				return objectMapper.writeValueAsString(new SERVER_RESPONSE(pizzaRepository.findAllByCategoryId(ctg.getId(), pageable1), categoriesRepository.findAll(), doughWidthRepository.findAll(), doughRadiusRepository.findAll(), ingredientRepository.findAll()));
			}
		} else if (filterByTitle != null) {
			totalPagesQty = pizzaRepository.findAllByTitleContainsIgnoreCase(filterByTitle, pageable1).getTotalPages();
			if (currentPage + 1 > totalPagesQty && totalPagesQty > 0) {
				pageable1 = PageRequest.of(totalPagesQty - 1, 8, Sort.by(sortDirection1,sortBy));
			}
			return objectMapper.writeValueAsString(new SERVER_RESPONSE(pizzaRepository.findAllByTitleContainsIgnoreCase(filterByTitle, pageable1), categoriesRepository.findAll(), doughWidthRepository.findAll(), doughRadiusRepository.findAll(), ingredientRepository.findAll()));
		} else {
			return objectMapper.writeValueAsString(new SERVER_RESPONSE(pizzaRepository.findAll(pageable1), categoriesRepository.findAll(), doughWidthRepository.findAll(), doughRadiusRepository.findAll(), ingredientRepository.findAll()));
		}
	}

	@GetMapping("/get-info")
	String getInfo(){
		return "I'm working";
	}
}