package com.example.pizza.pizza.domain.order;

import com.example.pizza.pizza.domain.BaseEntity;
import com.example.pizza.pizza.domain.pizza.Ingredients;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ordered_pizza")
public class OrderedPizza extends BaseEntity {
	private Long pizza_id;

	private String doughWidth;
	private int doughRadius;
	private int quantity;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Ingredients> ingredients = new HashSet<>();
}

/*@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ordered_pizza")
public class OrderedPizza extends BaseEntity {
	private Long pizza_id;

	private String doughWidth;
	private int doughRadius;
	private int quantity;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Ingredients> ingredients = new HashSet<>();

	@ManyToOne
	@JoinTable(name = "order_header_ordered_pizza",
			joinColumns = {@JoinColumn(name = "ordered_pizza")},
			inverseJoinColumns = {@JoinColumn(name = "order_header")}
	)
	private OrderHeader orderHeader;
}*/