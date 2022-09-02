package com.example.pizza.pizza.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Categories {
    @Id
    private Long id;
    private String categoryTitle;
    @OneToMany
    private Set<Pizza> pizza;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categories)) return false;

        Categories that = (Categories) o;

        if (!getId().equals(that.getId())) return false;
        return getCategoryTitle().equals(that.getCategoryTitle());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getCategoryTitle().hashCode();
        return result;
    }
}
