drop table if exists users;
drop table if exists todo;

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

insert into users(first_name, last_name)
VALUES ('hello', 'world');

insert into users(first_name, last_name)
VALUES ('yellow', 'sunn');

insert into todo(user_id, title, body)
VALUES (1, 'sunt aut facere repellat provident occaecati excepturi optio reprehenderit',
        'quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto');

insert
into todo(user_id, title, body)
VALUES (1, 'qui est esse',
        'est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla')
