create table ordered_pizzas
(
    id              bigint primary key not null auto_increment,
    ordered_time    timestamp,
    order_header_id bigint,
    title           varchar(100),
    price           int,
    dough_type      varchar(30),
    size            int,
    quantity        int

);