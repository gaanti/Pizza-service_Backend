package com.example.pizza.pizza.domain;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pizza)) return false;

        Pizza pizza = (Pizza) o;

        if (!getId().equals(pizza.getId())) return false;
        if (getTitle() != null ? !getTitle().equals(pizza.getTitle()) : pizza.getTitle() != null) return false;
        if (!Arrays.equals(getImage(), pizza.getImage())) return false;
        if (getDoughType() != null ? !getDoughType().equals(pizza.getDoughType()) : pizza.getDoughType() != null)
            return false;
        if (getSize() != null ? !getSize().equals(pizza.getSize()) : pizza.getSize() != null) return false;
        if (getPrice() != null ? !getPrice().equals(pizza.getPrice()) : pizza.getPrice() != null) return false;
        if (getCategoryId() != null ? !getCategoryId().equals(pizza.getCategoryId()) : pizza.getCategoryId() != null)
            return false;
        return getPopularity() != null ? getPopularity().equals(pizza.getPopularity()) : pizza.getPopularity() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getImage());
        result = 31 * result + (getDoughType() != null ? getDoughType().hashCode() : 0);
        result = 31 * result + (getSize() != null ? getSize().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getCategoryId() != null ? getCategoryId().hashCode() : 0);
        result = 31 * result + (getPopularity() != null ? getPopularity().hashCode() : 0);
        return result;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    @Fetch(FetchMode.JOIN)
    public byte[] image;
    String doughType;
    String size;
    Integer price;
    @ManyToOne
    Categories categoryId;
    Integer popularity;
}