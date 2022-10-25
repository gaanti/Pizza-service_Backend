package com.example.pizza.pizza.domain.order;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class OrderHeader {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@CreationTimestamp
	private LocalDateTime creationTime;

	private float orderCost;
	private String contactPerson;
	//from stripe
	private String paymentId;
	private String notifyMethod;
	private String notifyField;
	private String timeToBeDone;
	@Column(length = 32, columnDefinition = "ENUM ('FULFILLED','CREATED') default 'CREATED'")
	@Enumerated(value = EnumType.STRING)
	private ORDER_STATUS status = ORDER_STATUS.CREATED;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<OrderedPizza> orderedPizza = new HashSet<>();
}

/*@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class OrderHeader {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@CreationTimestamp
	private LocalDateTime creationTime;

	private float orderCost;
	private String contactPerson;
	private String notifyMethod;
	private String notifyField;
	private String timeToBeDone;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinTable(name = "order_header_ordered_pizza",
			joinColumns = {@JoinColumn(name = "order_header")},
			inverseJoinColumns = {@JoinColumn(name = "ordered_pizza")}
	)
	private Set<OrderedPizza> orderedPizza;
}*/
