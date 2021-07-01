create table bloggers
(
    id serial primary key unique not null,
    username varchar(50) unique not null,
    password varchar(255) not null
)