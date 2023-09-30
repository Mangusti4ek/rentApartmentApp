create sequence user_info_sequence;

CREATE TABLE IF NOT EXISTS user_info
(
    id              int8 primary key NOT NULL,
    booking_count   integer,
    commercial      boolean,
    date_time_registration timestamp,
    login           varchar(255),
    nick_name       varchar(255),
    parent_city     varchar(255),
    password         varchar(1000)
);



