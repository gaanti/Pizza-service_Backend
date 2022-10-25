package com.example.pizza.pizza.domain.order.delivery;

import com.example.pizza.pizza.domain.Address;
import com.example.pizza.pizza.domain.order.OrderHeader;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AttributeOverrides({
		@AttributeOverride(
				name = "address.street",
				column = @Column(name = "delivery_address_street")
		),
		@AttributeOverride(
				name = "address.city",
				column = @Column(name = "delivery_address_city")
		)
})

public class DeliveryOrder extends OrderHeader {
	/*	@Id
		@GeneratedValue(strategy = GenerationType.TABLE)
	//	@Column(name = "id", updatable = false, nullable = false)
		private long id;*/
	@Embedded
	private Address address;
	private int deliveryCost;

	public DeliveryOrder() throws Exception {
	}
}
