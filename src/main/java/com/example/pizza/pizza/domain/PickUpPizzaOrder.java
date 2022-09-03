package com.example.pizza.pizza.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Date;

@Entity
@Getter
@Setter
public class PickUpPizzaOrder extends Order {
    private Date pickupTime;
    @OneToOne
    private OrderHeader orderHeader;
    private String customerName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PickUpPizzaOrder)) return false;

        PickUpPizzaOrder that = (PickUpPizzaOrder) o;

        if (!getPickupTime().equals(that.getPickupTime())) return false;
        if (!getOrderHeader().equals(that.getOrderHeader())) return false;
        return getCustomerName() != null ? getCustomerName().equals(that.getCustomerName()) : that.getCustomerName() == null;
    }

    @Override
    public int hashCode() {
        int result = getPickupTime().hashCode();
        result = 31 * result + getOrderHeader().hashCode();
        result = 31 * result + (getCustomerName() != null ? getCustomerName().hashCode() : 0);
        return result;
    }
}
