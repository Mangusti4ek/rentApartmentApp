package com.example._rent_apartment.application_exeption;

import static com.example._rent_apartment.constant.ApplicationConstant.NON_AVAILABLE_APARTMENT_IN_LOCATION;

public class NotFoundAvailableApartmentInLocationException extends RuntimeException{

    private final String exceptionDescription = NON_AVAILABLE_APARTMENT_IN_LOCATION;

}
