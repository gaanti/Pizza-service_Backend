package com.example.pizza.pizza.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;

@Transactional
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pizzas {
	public byte[] image;
	String title;
	@ManyToMany
	@JoinTable(name = "pizza_category",
			joinColumns = @JoinColumn(name = "pizza_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	Set<Categories> category;
	@ManyToMany
	@JoinTable(name = "pizza_ingredient",
			joinColumns = @JoinColumn(name = "pizza_id"),
			inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	Set<Ingredients> ingredients;
	Integer price;
	Integer popularity;
	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	@CreationTimestamp
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime creationTime;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Pizzas)) return false;

		Pizzas pizzas = (Pizzas) o;

		if (getTitle() != null ? !getTitle().equals(pizzas.getTitle()) : pizzas.getTitle() != null) return false;
		if (!Arrays.equals(getImage(), pizzas.getImage())) return false;
		if (getCategory() != null ? !getCategory().equals(pizzas.getCategory()) : pizzas.getCategory() != null)
			return false;
		if (getIngredients() != null ? !getIngredients().equals(pizzas.getIngredients()) : pizzas.getIngredients() != null)
			return false;
		if (getPrice() != null ? !getPrice().equals(pizzas.getPrice()) : pizzas.getPrice() != null) return false;
		return getPopularity() != null ? getPopularity().equals(pizzas.getPopularity()) : pizzas.getPopularity() == null;
	}

	@Override
	public int hashCode() {
		int result = getTitle() != null ? getTitle().hashCode() : 0;
		result = 31 * result + Arrays.hashCode(getImage());
		result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
		result = 31 * result + (getPopularity() != null ? getPopularity().hashCode() : 0);
		return result;
	}
}