create table users
(
    id         bigint auto_increment,
    first_name varchar(255),
    last_name  varchar(255),
    primary key (id)
);

create table todo
(
    id      bigint auto_increment,
    user_id bigint,
    title   varchar(255),
    body    varchar(255),
    primary key (id)
);
