package com.example.pizza.pizza.domain.pizza;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
public class Categories {
	@Id
	private Long id;
	private String categoryTitle;
	@ManyToMany
	@JoinTable(name = "pizza_category",
			joinColumns = @JoinColumn(name = "category_id"),
			inverseJoinColumns = @JoinColumn(name = "pizza_id"))
	@JsonIgnore
	private Set<Pizzas> pizzas;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Categories)) return false;

		Categories that = (Categories) o;

		if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
		if (getCategoryTitle() != null ? !getCategoryTitle().equals(that.getCategoryTitle()) : that.getCategoryTitle() != null)
			return false;
		return getPizzas() != null ? getPizzas().equals(that.getPizzas()) : that.getPizzas() == null;
	}

	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		result = 31 * result + (getCategoryTitle() != null ? getCategoryTitle().hashCode() : 0);
		return result;
	}
}
