package com.example.pizza.pizza.Repositories;

import com.example.pizza.pizza.domain.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredients, Long> {
}
