create table post_likes
(
    post_id BIGINT not null,
    blogger_id BIGINT not null,
    primary key (post_id, blogger_id),
    foreign key (blogger_id) references bloggers (id),
    foreign key (post_id) references posts (id)
)