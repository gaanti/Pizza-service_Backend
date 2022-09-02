create table order_header
(
    id            bigint not null auto_increment,
    customer_name varchar(100),
    order_line_id bigint,
    order_cost    integer,
    ordered_time  timestamp,
    ordered_pizzas  JSON,
    PRIMARY KEY (id)
);