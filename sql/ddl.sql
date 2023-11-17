-- auto-generated definition
create table input_entity
(
    id          int                     not null
        primary key,
    device_name varchar(128)            not null,
    model       varchar(128) default '' not null,
    price       int                     not null
);


-- auto-generated definition
create table output_entity
(
    id             int auto_increment
        primary key,
    input_id       int           not null,
    device_name    varchar(128)  not null,
    model          varchar(128)  not null,
    manufacturer   varchar(512)  null,
    suppliers      varchar(512)  null,
    price          int           null,
    quantity       int default 0 null,
    source_website varchar(1024) null,
    source_url     varchar(1024) null,
    buyer          varchar(256)  null,
    date           varchar(256)  null,
    is_used_model  tinyint(1)    null
);
