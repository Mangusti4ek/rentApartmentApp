create sequence booking_history_sequence;

CREATE TABLE IF NOT EXISTS booking_history
(
    id              int8 primary key NOT NULL,
    booking_start_date    timestamp,
    booking_end_date    timestamp,
    id_apartment        int8 references apartment_info(id),
    id_user_info        int8 references user_info(id),
    id_product_info     int8 references product_info(id)
);