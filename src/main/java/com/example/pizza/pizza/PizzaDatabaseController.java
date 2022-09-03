package com.example.pizza.pizza;

import com.example.pizza.pizza.Repositories.CategoriesRepository;
import com.example.pizza.pizza.Repositories.PizzaRepository;
import com.example.pizza.pizza.domain.Categories;
import com.example.pizza.pizza.domain.Pizza;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/pizzas")
public class PizzaDatabaseController {
    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    String getAllPizzas(@RequestParam(required = false, name = "sortBy", defaultValue = "popularity") String sortBy,
                             @RequestParam(required = false, name = "filterByCategoryId", defaultValue = "0") Long filterByCategory,
                             @RequestParam(required = false, name = "currentPage", defaultValue = "0") int currentPage,
                             @RequestParam(required = false, name = "filterByTitle", defaultValue = "") String filterByTitle) throws JsonProcessingException {


        int totalPagesQty = 0;
        Pageable pageable1 = PageRequest.of(currentPage, 8, Sort.by(sortBy).descending());

        Optional<Categories> categoryId = categoriesRepository.findById(filterByCategory);

        @Setter
        @Getter
        @AllArgsConstructor
        @NoArgsConstructor
        class POPO {
            Page<Pizza> pizzas;
            List<Categories> categories;
        }

        if(filterByCategory.equals(0L)) {
            filterByCategory = null;
        } else if (categoryId.isPresent()) {
            filterByCategory = categoryId.get().getId();
        }

        if (filterByTitle.equals("")) filterByTitle = null;

        if (filterByCategory != null && filterByTitle != null) {
            totalPagesQty = pizzaRepository.findByTitleContainsIgnoreCaseAndCategoryId(filterByTitle, filterByCategory, pageable1).getTotalPages();
            if (currentPage + 1 > totalPagesQty) {
                pageable1 = PageRequest.of(totalPagesQty - 1, 8, Sort.by(sortBy).descending());
            }
            return objectMapper.writeValueAsString(new POPO (pizzaRepository.findByTitleContainsIgnoreCaseAndCategoryId(filterByTitle, filterByCategory, pageable1), categoriesRepository.findAll()));
        } else if (filterByCategory != null) {
            totalPagesQty = pizzaRepository.findByCategory(categoriesRepository.findById(filterByCategory), pageable1).getTotalPages();
            if (currentPage + 1 > totalPagesQty) {
                pageable1 = PageRequest.of(totalPagesQty - 1, 8, Sort.by(sortBy).descending());
            }
            return objectMapper.writeValueAsString(new POPO (pizzaRepository.findByCategory(categoriesRepository.findById(filterByCategory).get(), pageable1), categoriesRepository.findAll()));
        } else if (filterByTitle != null) {
            totalPagesQty = pizzaRepository.findAllByTitleContainsIgnoreCase(filterByTitle, pageable1).getTotalPages();
            if (currentPage + 1 > totalPagesQty) {
                pageable1 = PageRequest.of(totalPagesQty - 1, 8, Sort.by(sortBy).descending());
            }
            return objectMapper.writeValueAsString(new POPO (pizzaRepository.findAllByTitleContainsIgnoreCase(filterByTitle, pageable1), categoriesRepository.findAll()));
        } else {
            return objectMapper.writeValueAsString(new POPO (pizzaRepository.findAll(pageable1), categoriesRepository.findAll()));
        }
    }
}









