package com.example._rent_apartment.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegistrationApartmentForm {
    private Integer roomsCount;
    private String price;

    private String city;

    private String street;

    private String buildingNumber;

}
