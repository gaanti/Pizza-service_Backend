create table categories
(
    id             bigint      not null auto_increment,
    category_title varchar(30) not null,
    PRIMARY KEY (id)
);

create table pizzas
(
    id            bigint not null auto_increment,
    creation_time timestamp not null default now(),
    title         varchar(50),
    image         mediumblob,
    price         float,
    popularity    int,
    primary key (id)
);