create table ingredients
(
    id               bigint      not null auto_increment,
    creation_time    timestamp   not null default now(),
    ingredient_name  varchar(30) not null,

    PRIMARY KEY (id)
);
insert into ingredients (ingredient_name) value ('Sausage');
insert into ingredients (ingredient_name) value ('Cheese');
insert into ingredients (ingredient_name) value ('Tomato');
insert into ingredients (ingredient_name) value ('Spicy mango sauce');
insert into ingredients (ingredient_name) value ('Tomato paste');
insert into ingredients (ingredient_name) value ('Olive');
insert into ingredients (ingredient_name) value ('Cucumber');
insert into ingredients (ingredient_name) value ('Onion');
insert into ingredients (ingredient_name) value ('Corn');
insert into ingredients (ingredient_name) value ('Pepper');
insert into ingredients (ingredient_name) value ('Pineapple');
insert into ingredients (ingredient_name) value ('Meat');
insert into ingredients (ingredient_name) value ('Chicken');
insert into ingredients (ingredient_name) value ('Beef');
insert into ingredients (ingredient_name) value ('Pork');
insert into ingredients (ingredient_name) value ('Mushrooms');
insert into ingredients (ingredient_name) value ('Ketchup');
insert into ingredients (ingredient_name) value ('Mayo');
insert into ingredients (ingredient_name) value ('Greens');