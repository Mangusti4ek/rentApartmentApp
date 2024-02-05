package com.example._rent_apartment.constant;

public class ApplicationConstant {
    /** Path constant */
    public static final String BASE_PATH = "/rent";
    public static final String GET_APARTMENT_BY_FILTER = BASE_PATH + "/get-apartment-by-filter";
    public static final String FIND_AND_BOOKING_APARTMENT = BASE_PATH + "/find-and-booking-apartment";
    public static final String REGISTRATION_NEW_APARTMENT = BASE_PATH + "/registration-new-apartment";
    public static final String REGISTRATION_NEW_USER = "/registration";
    public static final String FIND_APARTMENT_BY_LOCATION = BASE_PATH + "/find-apartment-by-location";

    /** Exception constant */
    public static final String LOGIN_ERROR = "Пользователь с таким логином уже существует";
    public static final String NICKNAME_ERROR = "Пользователь с таким ником уже существует";
    public static final String REGISTRATION_APPROVED = "Пользователь успешно зарегестрирован";
    public static final String AUTHORIZATION_APPROVED = "Успешная авторизация";
    public static final String NON_VALID_TOKEN = "Войдите в систему";
    public static final String INCORRECT_PASSWORD = "Неверный пароль";
    public static final String INCORRECT_LOGIN = "Неверный логин";
    public static final String APARTMENT_NON_EXISTENT ="Квартира в данный момент недоступна";
    public static final String APARTMENT_BOOKING_SUCCESS = "Квартира успешно забронирована, дата заезда: ";
    public static final String APARTMENT_BOOKING_SUCCESS_WITH_OUT = "Квартира успешно забронирована,без учета скидки. Расчет скидки будет отправлен вам в течении сукток. Дата заезда: ";
    public static final String APARTMENT_NOT_AVAILABLE = "Квартира недоступна для бронирования";
    public static final String BOOKING_ONLY_FOR_USERS = "Бронирование квартир доступно авторизированым пользователям";
    public static final String NON_AVAILABLE_APARTMENT_IN_LOCATION = "В локации отсутствуют доступные квартиры";
    public static final String NOT_FOUND_CONFIG_INTEGRATION = "Отсутсвуют необходимые параметры запроса";


    /** Info constant */

    /** Query constant */

    public static final String GEO_LOC = "https://api.opencagedata.com";
    public static final String API_GATEWAY = "http://localhost:9097";
    public static final String TRANSLATOR = "https://dictionary.yandex.net";

}
