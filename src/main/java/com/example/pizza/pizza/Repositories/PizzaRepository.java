package com.example.pizza.pizza.Repositories;

import com.example.pizza.pizza.domain.pizza.Pizzas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizzas, Long> {
	Page<Pizzas> findAllByTitleContainsIgnoreCase(String search, Pageable pageable);

	//Page<Pizzas> findByTitleContainsIgnoreCaseAndCategoryId(String title, Long categoryId, Pageable pageable);
	Page<Pizzas> findByTitleContainsIgnoreCaseAndCategoryId(String title, Long category_id, Pageable pageable);

	List<Pizzas> findAllByCategoryId(Long category_id);

	Page<Pizzas> findAllByCategoryId(Long category_id, Pageable pageable);

	//Page<Pizzas> findByCategory(List<Categories> category, Pageable pageable);
	Page<Pizzas> findByCategoryCategoryTitle(String category_categoryTitle, Pageable pageable);

	//Page<Object> findByCategoriesId(Long categories_id, Pageable pageable);
}
