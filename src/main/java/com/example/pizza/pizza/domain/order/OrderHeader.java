package com.example.pizza.pizza.domain.order;

import com.example.pizza.pizza.domain.BaseEntity;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
@Data
public class OrderHeader extends BaseEntity {

    /*@Transient
    private List<OrderedPizzasRepository> orderedPizzas;*/

	private float orderCost;
	private String contactPerson;
	private String contactMethod;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<OrderLine> orderLine;
	@OneToOne
	private PickUpOrder pickUpOrder;
	@OneToOne
	private DeliveryOrder deliveryOrder;

}
