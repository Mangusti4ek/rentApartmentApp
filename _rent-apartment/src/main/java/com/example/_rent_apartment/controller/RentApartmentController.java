package com.example._rent_apartment.controller;

import com.example._rent_apartment.dto.ApartmentInfoDTO;
import com.example._rent_apartment.model.RegistrationApartmentForm;
import com.example._rent_apartment.model.Security.UserSessionApplication;
import com.example._rent_apartment.service.GeolocService;
import com.example._rent_apartment.service.RentApartmentProductService;
import com.example._rent_apartment.service.RentApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example._rent_apartment.constant.ApplicationConstant.*;
import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
public class RentApartmentController {

    private final RentApartmentService rentApartmentService;
    private final UserSessionApplication userSessionApplication;
    private final GeolocService geolocService;
    private final RentApartmentProductService rentApartmentProductService;

    public List<RegistrationApartmentForm> listApartment = new ArrayList<>();

    /**
     * Поиск апартаментов по фильтрам
     */
    @GetMapping(GET_APARTMENT_BY_FILTER)
    public List<ApartmentInfoDTO> getApartmentByFilter(@RequestParam String city,
                                                       @RequestParam(required = false) Integer price,
                                                       @RequestParam(required = false) Integer roomAmount,
                                                       @RequestParam(required = false) Integer overallRating) {

        return rentApartmentService.prepareFieldToCalc(city, price, roomAmount, overallRating);
    }

    /**
     * Поиск и бронирование апартаментов
     */
    @GetMapping(FIND_AND_BOOKING_APARTMENT)
    public ApartmentInfoDTO getApartment(@RequestParam Long id,
                                               @RequestParam(required = false) LocalDateTime start,
                                               @RequestParam(required = false) LocalDateTime end) {
        if(isNull(start) && isNull(end)) {
            return rentApartmentService.findApartmentByID(id);
        } else {
            return rentApartmentService.bookingApartment(id, start, end);
        }
    }

    /**
     * Регистрация новых апартаментов
     */
    @PostMapping(REGISTRATION_NEW_APARTMENT)
    public List<RegistrationApartmentForm> registrationNewApartment(@RequestBody RegistrationApartmentForm apartmentForm) {
        listApartment.add(apartmentForm);
        return listApartment;
    }

    /**
     * Поиск апартаментов в городе по геолокации.
     */
    @GetMapping(FIND_APARTMENT_BY_LOCATION)
    public List<ApartmentInfoDTO> findApartmentByLocation(@RequestParam String latitude, @RequestParam String longitude){

        return geolocService.findApartmentByLoc(latitude,longitude);
    }

    @GetMapping("/test")
    public String getMessageFromMyService(){
        return rentApartmentProductService.getTestMessage();
    }
}
