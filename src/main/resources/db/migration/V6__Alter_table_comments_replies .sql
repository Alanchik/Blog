alter table comments
    add comment_id BIGINT,
    add dtype varchar(10),
    add constraint comments_comment_id_fkey
        foreign key (comment_id)
            references comments (id)