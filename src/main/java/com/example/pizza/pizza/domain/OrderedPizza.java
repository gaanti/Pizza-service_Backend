package com.example.pizza.pizza.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
public class OrderedPizza {

    @JsonProperty("title")
    private String title;
    @JsonProperty("price")

    private int price;
    enum DoughType{
        Traditional, Thin
    }
    @JsonProperty("doughType")
    private DoughType doughType;
    @JsonProperty("size")
    private int size;
    @JsonProperty("quantity")
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderedPizza)) return false;

        OrderedPizza that = (OrderedPizza) o;

        if (getPrice() != that.getPrice()) return false;
        if (getSize() != that.getSize()) return false;
        if (getQuantity() != that.getQuantity()) return false;
        if (getTitle() != null ? !getTitle().equals(that.getTitle()) : that.getTitle() != null) return false;
        return getDoughType() == that.getDoughType();
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + getPrice();
        result = 31 * result + (getDoughType() != null ? getDoughType().hashCode() : 0);
        result = 31 * result + getSize();
        result = 31 * result + getQuantity();
        return result;
    }
}
