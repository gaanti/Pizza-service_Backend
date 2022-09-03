package com.example.pizza.pizza.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Categories{
    @Id
    private Long id;
    private String categoryTitle;
}
