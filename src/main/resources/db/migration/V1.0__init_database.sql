create table categories
(
    id       bigint      not null auto_increment,
    category_title varchar(30) not null,
    PRIMARY KEY (id)
);

insert into categories(id, category_title)
values (1, 'Meat');
insert into categories(id, category_title)
values (2, 'Vegetarian');
insert into categories(id, category_title)
values (3, 'Grille');
insert into categories(id, category_title)
values (4, 'Spicy');

create table pizza
(
    id          bigint not null auto_increment,
    title       varchar(50),
    image       mediumblob,
    dough_type  JSON,
    size        JSON,
    price       integer,
    category_id bigint,
    popularity  int(2),
    primary key (id),
    foreign key (category_id) references categories (id)
);
