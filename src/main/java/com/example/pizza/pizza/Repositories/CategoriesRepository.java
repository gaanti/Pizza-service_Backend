package com.example.pizza.pizza.Repositories;

import com.example.pizza.pizza.domain.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
}
