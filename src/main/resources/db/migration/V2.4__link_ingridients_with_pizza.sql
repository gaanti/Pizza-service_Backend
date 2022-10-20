create table pizza_ingredient
(
    pizza_id      bigint,
    ingredient_id bigint,
    primary key (pizza_id, ingredient_id),
    foreign key (pizza_id) references pizzas (id),
    foreign key (ingredient_id) references ingredients (id)
);

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Veggie Overload'
  and i.ingredient_name = 'Cheese';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Veggie Overload'
  and i.ingredient_name = 'Tomato';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Veggie Overload'
  and i.ingredient_name = 'Olive';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Veggie Overload'
  and i.ingredient_name = 'Onion';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Veggie Overload'
  and i.ingredient_name = 'Pepper';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Veggie Overload'
  and i.ingredient_name = 'Mushrooms';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Veggie Overload'
  and i.ingredient_name = 'Corn';



insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Hawaiian'
  and i.ingredient_name = 'Chicken';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Hawaiian'
  and i.ingredient_name = 'Pineapple';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Hawaiian'
  and i.ingredient_name = 'Spicy mango sauce';



insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Sweet Chili Chicken'
  and i.ingredient_name = 'Pork';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Sweet Chili Chicken'
  and i.ingredient_name = 'Pepper';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Sweet Chili Chicken'
  and i.ingredient_name = 'Spicy mango sauce';



insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Cheesy Chicken'
  and i.ingredient_name = 'Cheese';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Cheesy Chicken'
  and i.ingredient_name = 'Pepper';



insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Beef Suya'
  and i.ingredient_name = 'Onion';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Beef Suya'
  and i.ingredient_name = 'Beef';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Beef Suya'
  and i.ingredient_name = 'Cheese';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Beef Suya'
  and i.ingredient_name = 'Pepper';



insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Super Meaty'
  and i.ingredient_name = 'Sausage';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Super Meaty'
  and i.ingredient_name = 'Tomato';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Super Meaty'
  and i.ingredient_name = 'Beef';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Super Meaty'
  and i.ingredient_name = 'Mayo';


insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Spicy Mixed Pizza'
  and i.ingredient_name = 'Spicy mango sauce';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Spicy Mixed Pizza'
  and i.ingredient_name = 'Mayo';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Spicy Mixed Pizza'
  and i.ingredient_name = 'Beef';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Spicy Mixed Pizza'
  and i.ingredient_name = 'Corn';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Spicy Mixed Pizza'
  and i.ingredient_name = 'Chicken';



insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Margherita'
  and i.ingredient_name = 'Chicken';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Margherita'
  and i.ingredient_name = 'Cheese';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Margherita'
  and i.ingredient_name = 'Mayo';



insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Cheeseburger Pizza'
  and i.ingredient_name = 'Mayo';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Cheeseburger Pizza'
  and i.ingredient_name = 'Sausage';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Cheeseburger Pizza'
  and i.ingredient_name = 'Onion';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Cheeseburger Pizza'
  and i.ingredient_name = 'Cheese';



insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Chicken BBQ'
  and i.ingredient_name = 'Cheese';
insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Chicken BBQ'
  and i.ingredient_name = 'Corn';
insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Chicken BBQ'
  and i.ingredient_name = 'Onion';
insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Chicken BBQ'
  and i.ingredient_name = 'Mayo';
insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Chicken BBQ'
  and i.ingredient_name = 'Chicken';



insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Meaty BBQ'
  and i.ingredient_name = 'Pork';
insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Meaty BBQ'
  and i.ingredient_name = 'Sausage';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Meaty BBQ'
  and i.ingredient_name = 'Mayo';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Meaty BBQ'
  and i.ingredient_name = 'Cheese';



insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Pepperoni'
  and i.ingredient_name = 'Sausage';

insert into pizza_ingredient (pizza_id, ingredient_id)
select p.id, i.id
from pizzas p,
     ingredients i
where p.title = 'Pepperoni'
  and i.ingredient_name = 'Cheese';













