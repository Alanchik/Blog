create table comments
(
    id serial primary key unique not null,
    post_id BIGINT not null,
    author_id BIGINT not null,
    text text not null,
    published timestamp not null,
    foreign key (author_id) references bloggers (id),
    foreign key (post_id) references posts (id)
)