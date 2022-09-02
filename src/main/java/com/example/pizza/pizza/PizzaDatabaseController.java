package com.example.pizza.pizza;

import com.example.pizza.pizza.Repositories.CategoriesRepository;
import com.example.pizza.pizza.Repositories.PizzaRepository;
import com.example.pizza.pizza.domain.Categories;
import com.example.pizza.pizza.domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/pizzas")
public class PizzaDatabaseController {
    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

    @GetMapping
    Page<Pizza> getAllPizzas(@RequestParam(required = false, name = "sortBy", defaultValue = "popularity") String sortBy,
                             @RequestParam(required = false, name = "filterByCategoryId", defaultValue = "1") Long filterByCategory,
                             @RequestParam(required = false, name = "currentPage", defaultValue = "0") int currentPage,
                             @RequestParam(required = false, name = "filterByTitle", defaultValue = "") String filterByTitle) {


        int totalPagesQty = 0;
        Pageable pageable1 = PageRequest.of(currentPage, 8, Sort.by(sortBy).descending());

        Categories categoryId =
                categoriesRepository.getById(filterByCategory);
        //categoriesRepository.findById(Long.valueOf(filterByCategory));
        //Categories categoryId = new Categories();

        if (filterByCategory.equals(0L)) filterByCategory = null;
        if (filterByTitle.equals("")) filterByTitle = null;

        if (filterByCategory != null && filterByTitle != null) {
            totalPagesQty = pizzaRepository.findByTitleContainsIgnoreCaseAndCategoryId(filterByTitle, categoryId.getId(), pageable1).getTotalPages();
            if (currentPage + 1 > totalPagesQty) {
                pageable1 = PageRequest.of(totalPagesQty - 1, 8, Sort.by(sortBy).descending());
            }
            return pizzaRepository.findByTitleContainsIgnoreCaseAndCategoryId(filterByTitle, categoryId.getId(), pageable1);
        } else if (filterByCategory != null) {
            totalPagesQty = pizzaRepository.findAllByCategoryId(filterByCategory, pageable1).getTotalPages();
            if (currentPage + 1 > totalPagesQty) {
                pageable1 = PageRequest.of(totalPagesQty - 1, 8, Sort.by(sortBy).descending());
            }
            return pizzaRepository.findAllByCategoryId(filterByCategory, pageable1);
        } else if (filterByTitle != null) {
            totalPagesQty = pizzaRepository.findAllByTitleContainsIgnoreCase(filterByTitle, pageable1).getTotalPages();
            if (currentPage + 1 > totalPagesQty) {
                pageable1 = PageRequest.of(totalPagesQty - 1, 8, Sort.by(sortBy).descending());
            }
            return pizzaRepository.findAllByTitleContainsIgnoreCase(filterByTitle, pageable1);
        } else {
            return pizzaRepository.findAll(pageable1);
        }
    }
}









