create table pick_up_pizza_order
(
    id              bigint primary key not null auto_increment,
    order_header_id bigint,
    ordered_time    datetime,
    customer_name   varchar(40),

    pickup_time     time,

    FOREIGN KEY (order_header_id) references order_header (id)
);

create table delivery_pizza_order
(
    id                      bigint primary key not null auto_increment,
    order_header_id         bigint,
    ordered_time            datetime,
    customer_name           varchar(40),


    delivery_address_street varchar(30),
    delivery_address_city   varchar(30),

    delivery_cost           integer,

    contact_method          varchar(50),

    FOREIGN KEY (order_header_id) references order_header (id)
);
