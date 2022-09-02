package com.example.pizza.pizza.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class OrderHeader extends Order{

    @JSON
    @Transient
    private List<OrderedPizza> orderedPizzas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderHeader)) return false;
        if (!super.equals(o)) return false;

        OrderHeader that = (OrderHeader) o;

        return getOrderedPizzas() != null ? getOrderedPizzas().equals(that.getOrderedPizzas()) : that.getOrderedPizzas() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getOrderedPizzas() != null ? getOrderedPizzas().hashCode() : 0);
        return result;
    }
}
