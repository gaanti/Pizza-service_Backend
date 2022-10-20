create table dough_radius
(
    radius int not null,
    primary key (radius)
);
insert into dough_radius(radius)
VALUES (30);
insert into dough_radius(radius)
VALUES (35);
insert into dough_radius(radius)
VALUES (45);

create table dough_width
(
    id                bigint not null auto_increment,
    dough_width_title varchar(30),
    primary key (id)
);

insert into dough_width(id, dough_width_title)
VALUES (1, 'Traditional');
insert into dough_width(id, dough_width_title)
VALUES (2, 'Thin');
CREATE INDEX dough_width_title ON dough_width (dough_width_title);