package com.example.pizza.pizza;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Page<Pizza> findAllByTitleContainsIgnoreCase(String search,Pageable pageable);
    Page<Pizza> findByTitleContainsIgnoreCaseAndCategory(String search, Category category, Pageable pageable);

    Page<Pizza> findAllByCategory(Category category, Pageable pageable);

}
