create sequence product_info_sequence;

CREATE TABLE IF NOT EXISTS product_info
(
    id              int8 primary key NOT NULL,
    description     varchar(1000),
    discount        int4,
    season          varchar
);

INSERT INTO product_info
values (1,'Осенняя скидка',10,'autumn'),
       (2,'Скидка постонного клиента',5,null),
       (3,'Скидка для командировачных',15,null);