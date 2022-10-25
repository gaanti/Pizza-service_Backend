create table ordered_pizza
(
    id            bigint not null auto_increment,
    creation_time timestamp,
    pizza_id      bigint,
    dough_width   varchar(30),
    dough_radius  int,
    quantity      int,
    primary key (id),
    foreign key (pizza_id) references pizzas (id),
    foreign key (dough_width) references dough_width (dough_width_title),
    foreign key (dough_radius) references dough_radius (radius)
);

CREATE TABLE ordered_pizza_ingredients
(
    ordered_pizza_id bigint(20) NOT NULL,
    ingredient       varchar(255) DEFAULT NULL,
    FOREIGN KEY (ordered_pizza_id) REFERENCES ordered_pizza (id)
);