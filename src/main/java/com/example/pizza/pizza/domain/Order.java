package com.example.pizza.pizza.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime orderedTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (!getId().equals(order.getId())) return false;
        return getOrderedTime().equals(order.getOrderedTime());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getOrderedTime().hashCode();
        return result;
    }
}
