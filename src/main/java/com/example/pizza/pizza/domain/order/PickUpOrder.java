package com.example.pizza.pizza.domain.order;

import com.example.pizza.pizza.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Date;

@Entity
@Data
public class PickUpOrder extends BaseEntity {
    @OneToOne
    private OrderHeader orderHeader;
    private String customerName;

    private Date pickupTime;
}
