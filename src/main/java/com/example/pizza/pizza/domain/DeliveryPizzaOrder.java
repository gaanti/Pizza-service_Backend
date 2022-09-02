package com.example.pizza.pizza.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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
public class DeliveryPizzaOrder extends Order {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryPizzaOrder)) return false;

        DeliveryPizzaOrder that = (DeliveryPizzaOrder) o;

        if (getDeliveryCost() != that.getDeliveryCost()) return false;
        if (!getOrderHeader().equals(that.getOrderHeader())) return false;
        if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null) return false;
        return getContactMethod() != null ? getContactMethod().equals(that.getContactMethod()) : that.getContactMethod() == null;
    }

    @Override
    public int hashCode() {
        int result = getOrderHeader().hashCode();
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + getDeliveryCost();
        result = 31 * result + (getContactMethod() != null ? getContactMethod().hashCode() : 0);
        return result;
    }
}