package com.example.pizza.pizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
                             @RequestParam(required = false, name = "filterBy") Category category,
                             @RequestParam(required = false, name = "page", defaultValue = "0") int page,
                             @RequestParam(required = false, name = "search", defaultValue = "") String search) {
        Pageable pageable = PageRequest.of(page, 8, Sort.by(sortBy).ascending());
        if (category != null) {
            return pizzaRepository.findByCategory(category, pageable);
        } else return pizzaRepository.findAll(pageable);
    }
}









