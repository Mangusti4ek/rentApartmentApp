create sequence apartment_info_sequence start 4 INCREMENT 1;

CREATE TABLE IF NOT EXISTS apartment_info
(
    id                int8 primary key NOT NULL,
    available         bool,
    overall_rating    double precision,
    price             integer,
    room_amount       integer,
    address_id        int8 references address_info(id),
    registration_date timestamp
);

INSERT INTO apartment_info(id, available, overall_rating, price, room_amount, address_id, registration_date)
VALUES (1,true,null,2120,2,1,null),
       (2,true,null,2121,1,2,null),
       (3,true,null,2122,3,3,null);