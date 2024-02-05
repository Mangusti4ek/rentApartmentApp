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
    password         varchar(1000),
    token varchar (100)
);

INSERT INTO user_info
VALUES (1,0,false,'2023-12-09','alexeremeev7@gmail.com', 'Mangust', 'Новокузнецк', 'MTM1MjQ2','706054e0-bb28-446e-bb1b-6e7a470a1845|2024-12-10T17:22:03.427539300');



