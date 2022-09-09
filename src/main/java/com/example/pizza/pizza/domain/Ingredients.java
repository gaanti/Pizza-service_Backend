package com.example.pizza.pizza.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ingredients {
	@Id
	private Long id;
	private String ingredientName;
	@ManyToMany
	@JoinTable(name = "pizza_ingredient",
			joinColumns = @JoinColumn(name = "pizza_id"),
			inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	@JsonIgnore
	private Set<Pizzas> pizzas;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Ingredients)) return false;

		Ingredients that = (Ingredients) o;

		if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
		if (getIngredientName() != null ? !getIngredientName().equals(that.getIngredientName()) : that.getIngredientName() != null)
			return false;
		return getPizzas() != null ? getPizzas().equals(that.getPizzas()) : that.getPizzas() == null;
	}

	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		result = 31 * result + (getIngredientName() != null ? getIngredientName().hashCode() : 0);
		return result;
	}
}
