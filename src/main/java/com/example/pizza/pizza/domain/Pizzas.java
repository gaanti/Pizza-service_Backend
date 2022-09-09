package com.example.pizza.pizza.domain;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Set;

@Transactional
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pizzas extends BaseEntity{
    String title;
    public byte[] image;
    @ManyToMany
    @JoinTable(name = "pizza_category",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<Categories> category;

    @ManyToMany
    @JoinTable(name = "pizza_ingredient",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    Set<Ingredients> ingredients;

    Integer price;
    Integer popularity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pizzas)) return false;

        Pizzas pizzas = (Pizzas) o;

        if (getTitle() != null ? !getTitle().equals(pizzas.getTitle()) : pizzas.getTitle() != null) return false;
        if (!Arrays.equals(getImage(), pizzas.getImage())) return false;
        if (getCategory() != null ? !getCategory().equals(pizzas.getCategory()) : pizzas.getCategory() != null)
            return false;
        if (getIngredients() != null ? !getIngredients().equals(pizzas.getIngredients()) : pizzas.getIngredients() != null)
            return false;
        if (getPrice() != null ? !getPrice().equals(pizzas.getPrice()) : pizzas.getPrice() != null) return false;
        return getPopularity() != null ? getPopularity().equals(pizzas.getPopularity()) : pizzas.getPopularity() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + Arrays.hashCode(getImage());
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getPopularity() != null ? getPopularity().hashCode() : 0);
        return result;
    }
}