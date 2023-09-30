package com.example._rent_apartment.application_exeption;

import static com.example._rent_apartment.constant.ApplicationConstant.APARTMENT_NON_EXISTENT;

public class ApartmentNonExistentException extends RuntimeException{
    private final String exceptionDescription = APARTMENT_NON_EXISTENT;

}
