CREATE TABLE if not exists integration_info
(
  id varchar primary key,
  path_value varchar,
  description varchar,
  api_key   varchar
);

INSERT INTO integration_info
VALUES ('https://api.opencagedata.com','/geocode/v1/json?q=%s+%s&no_annotations=1&key=%s',
        'Сервис позволяющий определить населенный пункт по локации пользователя','YzVlYzNlYTVmNjVmNDgxM2I4NTllOTNjNGEyYTE0Njc=');
