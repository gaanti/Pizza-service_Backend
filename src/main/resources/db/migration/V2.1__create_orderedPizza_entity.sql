create table ordered_pizzas
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
)