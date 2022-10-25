package com.example.pizza.pizza.Repositories.pizzaParams;

import com.example.pizza.pizza.domain.pizza.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
}

