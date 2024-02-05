CREATE TABLE if not exists integration_info
(
    id          varchar primary key,
    path_value  varchar,
    description varchar,
    api_key     varchar
);

INSERT INTO integration_info
VALUES ('https://api.opencagedata.com', '/geocode/v1/json?q=%s+%s&no_annotations=1&language=ru&key=%s',
        'Сервис позволяющий определить населенный пункт по локации пользователя',
        'YzVlYzNlYTVmNjVmNDgxM2I4NTllOTNjNGEyYTE0Njc='),
       ('https://dictionary.yandex.net', '/api/v1/dicservice.json/lookup?key=%s&lang=en-ru&text=%s',
        'Сервис для перевода', 'dict.1.1.20231209T114615Z.b583d30e7398e800.55233a5c11fb44d13c3995a6a3426512611f9a77'),
       ('http://localhost:9097', '/product_check/%s', 'Сервис подбора продуктов', null),
    ('http://localhost:9098', '/get_mail?mail=%s&message=%s&subject=%s', 'Сервис отправки e-mail сообщений', null);