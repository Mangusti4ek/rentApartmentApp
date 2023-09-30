create sequence address_info_sequence start 4 INCREMENT 1;

CREATE TABLE IF NOT EXISTS address_info (
    id int8 primary key NOT NULL,
    building varchar(255),
    city varchar(255),
    street varchar(255)
);

INSERT INTO address_info(id, building, city, street)
VALUES (1,'1','Новокузнецк', 'Ленина'),
       (2,'21','Москва','Мичурина'),
       (3,'23','Новокузнецк','Абагурская');
