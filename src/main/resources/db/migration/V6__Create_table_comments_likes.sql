create table comment_likes
(
    comment_id BIGINT not null,
    blogger_id BIGINT not null,
    primary key (comment_id, blogger_id),
    foreign key (blogger_id) references bloggers (id),
    foreign key (comment_id) references posts (id)
)