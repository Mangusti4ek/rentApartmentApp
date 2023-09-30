create sequence apartment_rating_sequence start 7 INCREMENT 1;

CREATE TABLE IF NOT EXISTS apartment_rating
(
    id              int8 primary key NOT NULL,
    rating          integer,
    apartment_id    int8 references apartment_info(id),
    comment         varchar(1000)
);

INSERT INTO apartment_rating(id, rating, apartment_id, comment)
VALUES (1,4,1,'хор'),
       (2,5,1,'Отл'),
       (3,3,2,'срд'),
       (4,2,2,'пло'),
       (5,1,3,'ужс'),
       (6,5,3,'Отл');

