package com.example.pizza.pizza.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Address {
    String street;
    private String city;
}
