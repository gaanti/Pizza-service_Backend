package com.example.pizza.pizza.domain.pizza;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class DoughRadius {
	@Id
	int radius;
}
