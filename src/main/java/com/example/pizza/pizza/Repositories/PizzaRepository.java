package com.example.pizza.pizza.Repositories;

import com.example.pizza.pizza.domain.Categories;
import com.example.pizza.pizza.domain.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Page<Pizza> findAllByTitleContainsIgnoreCase(String search,Pageable pageable);
    Page<Pizza> findByTitleContainsIgnoreCaseAndCategoryId(String title, Long categoryId, Pageable pageable);

    Page<Pizza> findByCategory(Categories categories, Pageable pageable);

    Page<Object> findByCategory(Optional<Categories> byId, Pageable pageable1);
}
