create table order_header
(
    id                 bigint not null auto_increment,
    ordered_time       timestamp,
    order_cost         integer,

    PRIMARY KEY (id)
);