package com.example.pizza.pizza;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    List<Pizza> findByOrderByPriceAsc();
    List<Pizza> findByOrderByPriceDesc();

    List<Pizza> findByCategoryOrderByCategoryAsc(Category category);
    List<Pizza> findByCategory(Category category, Sort sort);
    Page<Pizza> findByCategory(Category category, Pageable pageable);

}
