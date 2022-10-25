package com.example.pizza.pizza.Repositories.pizzaParams;

import com.example.pizza.pizza.domain.pizza.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredients, Long> {
}
