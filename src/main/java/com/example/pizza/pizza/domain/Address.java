package com.example.pizza.pizza.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Address {
    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    String street;
    private String city;
}
