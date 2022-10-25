package com.example.pizza.pizza.domain.pizza;

import com.example.pizza.pizza.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ingredients extends BaseEntity {
	private String ingredientName;
	@ManyToMany
	@JoinTable(name = "pizza_ingredient",
			joinColumns = @JoinColumn(name = "pizza_id"),
			inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	@JsonIgnore
	private Set<Pizzas> pizzas;

	public Ingredients(String ingredientName) {
		this.ingredientName = ingredientName;
	}
}
