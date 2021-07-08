create table posts
(
    id serial primary key unique not null,
    author_id BIGINT not null,
    description text not null,
    published timestamp not null,
    foreign key (author_id) references bloggers (id)
)