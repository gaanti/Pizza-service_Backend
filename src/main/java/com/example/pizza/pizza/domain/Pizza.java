package com.example.pizza.pizza.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Arrays;

@Entity
@Getter
@Setter
@Transactional
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    public byte[] image;
    String doughType;
    String size;
    Integer price;
    @OneToOne
    Categories category;
    Integer popularity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pizza)) return false;

        Pizza pizza = (Pizza) o;

        if (getId() != null ? !getId().equals(pizza.getId()) : pizza.getId() != null) return false;
        if (getTitle() != null ? !getTitle().equals(pizza.getTitle()) : pizza.getTitle() != null) return false;
        if (!Arrays.equals(getImage(), pizza.getImage())) return false;
        if (getDoughType() != null ? !getDoughType().equals(pizza.getDoughType()) : pizza.getDoughType() != null)
            return false;
        if (getSize() != null ? !getSize().equals(pizza.getSize()) : pizza.getSize() != null) return false;
        if (getPrice() != null ? !getPrice().equals(pizza.getPrice()) : pizza.getPrice() != null) return false;
        if (getCategory() != null ? !getCategory().equals(pizza.getCategory()) : pizza.getCategory() != null)
            return false;
        return getPopularity() != null ? getPopularity().equals(pizza.getPopularity()) : pizza.getPopularity() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getImage());
        result = 31 * result + (getDoughType() != null ? getDoughType().hashCode() : 0);
        result = 31 * result + (getSize() != null ? getSize().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (getPopularity() != null ? getPopularity().hashCode() : 0);
        return result;
    }
}