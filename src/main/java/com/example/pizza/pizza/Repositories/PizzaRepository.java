package com.example.pizza.pizza.Repositories;

import com.example.pizza.pizza.domain.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Page<Pizza> findAllByTitleContainsIgnoreCase(String search,Pageable pageable);
    Page<Pizza> findByTitleContainsIgnoreCaseAndCategoryId(String title, Long categoryId, Pageable pageable);

    Page<Pizza> findAllByCategoryId(Long categories, Pageable pageable);
}
