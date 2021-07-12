create table subscribers
(
    blogger_id BIGINT not null,
    subscriber_id BIGINT not null,
    primary key (blogger_id,subscriber_id),
    foreign key (blogger_id) references bloggers (id),
    foreign key (subscriber_id) references bloggers(id)

)