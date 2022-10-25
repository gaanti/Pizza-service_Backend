/*create table order_header
(
    id             bigint not null auto_increment,
    creation_time  timestamp,

    order_cost     float,
    contact_person varchar(60),
    notify_method varchar(55),
    notify_field varchar(55),
    time_to_be_done varchar(55),
    ordered_pizza_id bigint,

    PRIMARY KEY (id),
    foreign key (ordered_pizza_id) references ordered_pizza (id)
);*/

create table pick_up_order
(
    id             bigint not null auto_increment,
    creation_time  timestamp,

    order_cost     float,
    contact_person varchar(60),
    notify_method varchar(55),
    notify_field varchar(55),
    time_to_be_done varchar(55),

    PRIMARY KEY (id)
);

create table delivery_order
(

    id             bigint not null auto_increment,
    creation_time  timestamp,

    order_cost     float,
    contact_person varchar(60),
    notify_method varchar(55),
    notify_field varchar(55),
    time_to_be_done varchar(55),

    delivery_address_street varchar(50),
    delivery_address_city   varchar(40),
    delivery_cost   int,

    PRIMARY KEY (id)
);

create table order_header_ordered_pizza
(
    order_header_id  bigint not null,
    ordered_pizza_id bigint not null,
    primary key (order_header_id, ordered_pizza_id),
    unique key (`ordered_pizza_id`),
    foreign key (ordered_pizza_id) references ordered_pizza (id)
);
create table hibernate_sequence (
    next_val bigint
) engine=InnoDB;
insert into hibernate_sequence values ( 1 )