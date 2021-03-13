create table currency
(
    full_name varchar(50) not null,
    name      varchar(10) not null,
    cur_id    bigserial   not null
        constraint cur_id_pk
            primary key
);

create unique index currency_full_name_uindex
    on currency (full_name);

create unique index currency_name_uindex
    on currency (name);

create table branch
(
    br_address        varchar(50) not null,
    br_city           varchar(30) not null,
    br_country        varchar(30) not null,
    br_zip            varchar(15) not null,
    br_id             bigserial   not null
        constraint br_id_pk
            primary key,
    br_local_currency bigint      not null
        constraint br_local_currency_fk
            references currency (cur_id)
);

create table exchange
(
    exchange_id bigserial                 not null
        constraint exchange_cur_id_pk
            primary key,
    cur_id      bigint                    not null
        constraint exchange_cur_id_fk
            references currency,
    br_id       bigint                    not null
        constraint exchange_br_id_br_id_fk
            references branch,
    buy_price   real                      not null,
    sell_price  real                      not null,
    timestamp   timestamp                 not null,
    rate        bigint                    not null,
    date        date default CURRENT_DATE not null
);

alter table exchange
    owner to postgres;

create unique index exchange_date_uindex
    on exchange (date);

create table account
(
    ac_id     bigserial not null
        constraint account_ac_id_pk
            primary key,
    cur_id    bigint    not null
        constraint account_cur_id_fk
            references currency (cur_id),
    br_id     bigint    not null
        constraint account_br_id_br_id_fk
            references branch (br_id),
    ac_amount real      not null,
    timestamp timestamp not null
);

create type access_level as enum ('USER', 'MODERATOR', 'ADMIN');

create table employee
(
    emp_id           bigserial    not null
        constraint employee_ac_id_pk
            primary key,
    br_id            bigint
        constraint employee_br_id_br_id_fk
            references branch (br_id),
    emp_first_name   varchar(30),
    emp_last_name    varchar(30),
    emp_birthday     date,
    emp_access_level access_level not null,
    emp_login        varchar(30)  not null,
    emp_password     varchar(60)  not null
);

create table exchange_history
(
    ex_history_id bigserial not null
        constraint exchange_history_ac_id_pk
            primary key,
    amount        real      not null,
    exchange_id   bigint    not null
        constraint exchange_history_exchange_id_fk
            references exchange,
    br_id         bigint    not null
        constraint exchange_history_br_id_br_id_fk
            references branch,
    emp_id        bigint    not null
        constraint exchange_history_emp_id_fk
            references employee,
    timestamp     timestamp not null,
    date          date      not null
);