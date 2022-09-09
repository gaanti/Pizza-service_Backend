package com.example.pizza.pizza.domain.order;

import com.example.pizza.pizza.domain.Address;
import com.example.pizza.pizza.domain.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
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
public class DeliveryOrder extends BaseEntity {
    @OneToOne(
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private OrderHeader orderHeader;
    @Embedded
    private Address address;
    private int deliveryCost;
    private String contactMethod;
    private String customerName;
}