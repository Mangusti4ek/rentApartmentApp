package com.example._rent_apartment.service;

import com.example._rent_apartment.dto.ApartmentInfoDTO;
import com.example._rent_apartment.model.Security.UserApplicationEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface RentApartmentService {

    public List<ApartmentInfoDTO> getApartmentByCity(String city);

    public List<ApartmentInfoDTO> getApartmentByAllFilter(String city, Integer price, Integer roomAmount, Integer overallRating);

    public List<ApartmentInfoDTO> getAddressInfoEntitiesByCityAndPriceAndRoomAmount(String city, Integer price, Integer roomAmount);

    List<ApartmentInfoDTO> getAddressInfoEntitiesByCityAndPrice(String city, Integer price);

    List<ApartmentInfoDTO> getAddressInfoEntitiesByCityAndPriceAndRating(String city, Integer price, Integer overallRating);

    List<ApartmentInfoDTO> getAddressInfoEntitiesByCityAndRoomAmountAndRating(String city, Integer roomAmount, Integer overallRating);

    List<ApartmentInfoDTO> getAddressInfoEntitiesByCityAndRating(String city, Integer overallRating);

    List<ApartmentInfoDTO> getApartmentByCityAndRoomAmount(String city, Integer roomAmount);

    List<ApartmentInfoDTO> prepareFieldToCalc (String city, Integer price, Integer roomAmount, Integer overallRating);

    ApartmentInfoDTO findApartmentByID(Long id);

    ApartmentInfoDTO bookingApartment(Long id, LocalDateTime start, LocalDateTime end);
}
