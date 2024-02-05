create sequence apartment_rating_sequence start 7 INCREMENT 1;

CREATE TABLE IF NOT EXISTS apartment_rating
(
    id              int8 primary key NOT NULL,
    rating          integer,
    apartment_id    int8 references apartment_info(id),
    comment         varchar(1000)
);

