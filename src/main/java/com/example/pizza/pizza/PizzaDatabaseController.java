package com.example.pizza.pizza;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/pizzas")
public class PizzaDatabaseController {
    @Autowired
    PizzaRepository pizzaRepository;

    @GetMapping
    Page<Pizza> getAllPizzas(@RequestParam(required = false, name = "sortBy", defaultValue = "popularity") String sortBy,
                             @RequestParam(required = false, name = "filterBy", defaultValue = "") Category category,
                             @RequestParam(required = false, name = "page", defaultValue = "0") int page,
                             @RequestParam(required = false, name = "search", defaultValue = "") String search) {
        Pageable pageable = PageRequest.of(page, 8, Sort.by(sortBy).descending());

        if (category != null && search != null) {
            return pizzaRepository.findByTitleContainsIgnoreCaseAndCategory(search, category, pageable);
        } else if (category != null) {
            return pizzaRepository.findAllByCategory(category, pageable);
        } else if (search != null) {
            return pizzaRepository.findAllByTitleContainsIgnoreCase(search, pageable);
        } else return pizzaRepository.findAll(pageable);
    }
}









