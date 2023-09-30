package com.example._rent_apartment.application_exeption;

import lombok.Getter;

import static com.example._rent_apartment.constant.ApplicationConstant.NOT_FOUND_CONFIG_INTEGRATION;

@Getter
public class NotFoundConfigIntegrationException extends RuntimeException {

    private final String exceptionDescription = NOT_FOUND_CONFIG_INTEGRATION;

}
