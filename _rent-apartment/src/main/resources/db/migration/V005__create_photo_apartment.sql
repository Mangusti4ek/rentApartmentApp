create sequence photo_apartment_sequence;

CREATE TABLE IF NOT EXISTS photo_apartment
(
    id              int8 primary key NOT NULL,
    apartment_id    int8 references apartment_info(id),
    link            varchar
);

