create table pizza_category
(
    pizza_id    bigint,
    category_id bigint,
    primary key (pizza_id, category_id),

    foreign key (pizza_id) references pizzas (id),
    foreign key (category_id) references categories (id)
);

insert into pizza_category (pizza_id, category_id)
SELECT p.id, c.id
FROM pizzas p,
     categories c
where p.title = 'Cheesy Chicken'
  and c.category_title = 'Cheesy';

insert into pizza_category (pizza_id, category_id)
SELECT p.id, c.id
FROM pizzas p,
     categories c
where p.title = 'Cheesy Chicken'
  and c.category_title = 'Meaty';

insert into pizza_category (pizza_id, category_id)
SELECT p.id, c.id
FROM pizzas p,
     categories c
where p.title = 'Chicken BBQ'
  and c.category_title = 'B-B-Q';


insert into pizza_category (pizza_id, category_id)
SELECT p.id, c.id
FROM pizzas p,
     categories c
where p.title = 'Chicken BBQ'
  and c.category_title = 'Meaty';

insert into pizza_category (pizza_id, category_id)
SELECT p.id, c.id
FROM pizzas p,
     categories c
where p.title = 'Margherita'
  and c.category_title = 'Cheesy';


insert into pizza_category (pizza_id, category_id)
SELECT p.id, c.id
FROM pizzas p,
     categories c
where p.title = 'Margherita'
  and c.category_title = 'Vegetarian';

insert into pizza_category (pizza_id, category_id)
SELECT p.id, c.id
FROM pizzas p,
     categories c
where p.title = 'Meaty BBQ'
  and c.category_title = 'B-B-Q';

insert into pizza_category (pizza_id, category_id)
SELECT p.id, c.id
FROM pizzas p,
     categories c
where p.title = 'Meaty BBQ'
  and c.category_title = 'Meaty';

insert into pizza_category (pizza_id, category_id)
SELECT p.id, c.id
FROM pizzas p,
     categories c
where p.title = 'Pepperoni'
  and c.category_title = 'Meaty';

insert into pizza_category (pizza_id, category_id)
SELECT p.id, c.id
FROM pizzas p,
     categories c
where p.title = 'Cheeseburger Pizza'
  and c.category_title = 'Traditional';

insert into pizza_category (pizza_id, category_id)
SELECT p.id, c.id
FROM pizzas p,
     categories c
where p.title = 'Super Meaty'
  and c.category_title = 'Meaty';

insert into pizza_category (pizza_id, category_id)
SELECT p.id, c.id
FROM pizzas p,
     categories c
where p.title = 'Veggie Overload'
  and c.category_title = 'Vegetarian';

insert into pizza_category (pizza_id, category_id)
SELECT p.id, c.id
FROM pizzas p,
     categories c
where p.title = 'Hawaiian'
  and c.category_title = 'Spicy';

insert into pizza_category (pizza_id, category_id)
SELECT p.id, c.id
FROM pizzas p,
     categories c
where p.title = 'Hawaiian'
  and c.category_title = 'Meaty';

insert into pizza_category (pizza_id, category_id)
SELECT p.id, c.id
FROM pizzas p,
     categories c
where p.title = 'Beef Suya'
  and c.category_title = 'Traditional';


insert into pizza_category (pizza_id, category_id)
SELECT p.id, c.id
FROM pizzas p,
     categories c
where p.title = 'Beef Suya'
  and c.category_title = 'Meaty';

insert into pizza_category (pizza_id, category_id)
SELECT p.id, c.id
FROM pizzas p,
     categories c
where p.title = 'Sweet Chili Chicken'
  and c.category_title = 'Spicy';

insert into pizza_category (pizza_id, category_id)
SELECT p.id, c.id
FROM pizzas p,
     categories c
where p.title = 'Spicy Mixed Pizza'
  and c.category_title = 'Spicy';
