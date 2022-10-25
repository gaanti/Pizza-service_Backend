package com.example.pizza.pizza.domain.order.pickUp;

import com.example.pizza.pizza.domain.order.OrderHeader;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
public class PickUpOrder extends OrderHeader {
	/*	@Id
		@GeneratedValue(strategy = GenerationType.TABLE)
	//	@Column(name = "id", updatable = false, nullable = false)
		private long id;*/

	public PickUpOrder() throws Exception {
	}
}
