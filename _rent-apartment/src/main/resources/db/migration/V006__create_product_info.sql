create sequence product_info_sequence;

CREATE TABLE IF NOT EXISTS product_info
(
    id              int8 primary key NOT NULL,
    description     varchar(1000),
    discount        int4,
    season          varchar
);