package com.example.pizza.pizza;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/pizzas")
public class PizzaDatabaseController {
    @Autowired
    PizzaRepository pizzaRepository;

    @GetMapping
    Page<Pizza> getAllPizzas(@RequestParam(required = false, name = "sortBy", defaultValue = "popularity") String sortBy,
                             @RequestParam(required = false, name = "filterByCategory", defaultValue = "") Category filterByCategory,
                             @RequestParam(required = false, name = "currentPage", defaultValue = "0") int currentPage,
                             @RequestParam(required = false, name = "filterByTitle", defaultValue = "") String filterByTitle) {


        int totalPagesQty = 0;
        Pageable pageable1 = PageRequest.of(currentPage, 8, Sort.by(sortBy).descending());

        if (filterByCategory.equals(Category.All)) filterByCategory = null;
        if (filterByTitle.equals("")) filterByTitle = null;

        if (filterByCategory != null && filterByTitle != null) {
            totalPagesQty = pizzaRepository.findByTitleContainsIgnoreCaseAndCategory(filterByTitle, filterByCategory, pageable1).getTotalPages();
            if (currentPage + 1 > totalPagesQty) {
                pageable1 = PageRequest.of(totalPagesQty - 1, 8, Sort.by(sortBy).descending());
            }
            return pizzaRepository.findByTitleContainsIgnoreCaseAndCategory(filterByTitle, filterByCategory, pageable1);
        } else if (filterByCategory != null) {
            totalPagesQty = pizzaRepository.findAllByCategory(filterByCategory, pageable1).getTotalPages();
            if (currentPage + 1 > totalPagesQty) {
                pageable1 = PageRequest.of(totalPagesQty - 1, 8, Sort.by(sortBy).descending());
            }
            Page<Pizza> tempPizzaAr = pizzaRepository.findAllByCategory(filterByCategory, pageable1);
            return tempPizzaAr;
        } else if (filterByTitle != null) {
            totalPagesQty = pizzaRepository.findAllByTitleContainsIgnoreCase(filterByTitle, pageable1).getTotalPages();
            if (currentPage + 1 > totalPagesQty) {
                pageable1 = PageRequest.of(totalPagesQty - 1, 8, Sort.by(sortBy).descending());
            }
            return pizzaRepository.findAllByTitleContainsIgnoreCase(filterByTitle, pageable1);
        } else {
            Page<Pizza> tempPizzaAr = pizzaRepository.findAll(pageable1);
            return tempPizzaAr;
        }
    }
}









