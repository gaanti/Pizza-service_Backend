package com.example.pizza.pizza.Repositories;

import com.example.pizza.pizza.domain.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
	/*Optional<Categories> findById(Long categories_id);

	Optional<Categories> findById(Optional<Categories> category);*/
}

