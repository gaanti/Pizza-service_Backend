create table order_header
(
    id             bigint not null auto_increment,
    creation_time   timestamp,

    order_cost     float,
    contact_person varchar(60),
    contact_method varchar(55),

    PRIMARY KEY (id)
);

create table order_lines
(
    id              bigint not null auto_increment,
    creation_time    timestamp,

    order_header_id bigint,
    pizza_id        bigint,

    dough_type      varchar(30),
    size            int,
    quantity        int,

    PRIMARY KEY (id),
    foreign key (pizza_id) references ordered_pizzas (id),
    foreign key (order_header_id) references order_header (id)
);

create table pickup_order
(
    id              bigint not null auto_increment,
    creation_time    datetime,
    order_header_id bigint,

    pickup_time     time,

    primary key (id),
    foreign key (order_header_id) references order_header (id)
);

create table delivery_order
(
    id                      bigint not null auto_increment,
    creation_time            datetime,
    order_header_id         bigint,

    delivery_address_street varchar(50),
    delivery_address_city   varchar(40),
    delivery_cost           integer,
    delivery_time           datetime,

    primary key (id),
    foreign key (order_header_id) references order_header (id)

);