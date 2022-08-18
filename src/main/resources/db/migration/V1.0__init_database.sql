create table pizza(
    id bigint primary key not null auto_increment,
    title varchar(50),
    image mediumblob,
    dough_type JSON,
    size JSON,
    price integer,
    category enum('Meat','Vegetarian', 'Grille', 'Spicy'),
    popularity int(2)
)