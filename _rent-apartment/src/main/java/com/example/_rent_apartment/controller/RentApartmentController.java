package com.example._rent_apartment.controller;

import com.example._rent_apartment.dto.ApartmentInfoDTO;
import com.example._rent_apartment.model.RegistrationApartmentForm;
import com.example._rent_apartment.service.IntegrationManagerService;
import com.example._rent_apartment.service.RentApartmentService;
import com.example._rent_apartment.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.example._rent_apartment.constant.ApplicationConstant.*;
import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
public class RentApartmentController {

    private Logger logger = LoggerFactory.getLogger(RentApartmentController.class);
    private final RentApartmentService rentApartmentService;
    private final IntegrationManagerService geolocService;
    private final SecurityService securityService;


    /**
     * Поиск апартаментов по фильтрам
     */
    @GetMapping(GET_APARTMENT_BY_FILTER)
    public List<ApartmentInfoDTO> getApartmentByFilter(@RequestParam String city,
                                                       @RequestParam(required = false) Integer price,
                                                       @RequestParam(required = false) Integer roomAmount,
                                                       @RequestParam(required = false) Double overallRating) {

        return rentApartmentService.prepareFieldToCalc(city, price, roomAmount, overallRating); // разобраться с не работающим поиском по overallRating, проблема в том что присваивается значнеие в мапере при выгрузке. Переложить логику из мапера в планировщик?Scheduler
    }

    /**
     * Поиск и бронирование апартаментов
     */
    @GetMapping(FIND_AND_BOOKING_APARTMENT)
    public ApartmentInfoDTO getApartment(@RequestHeader(required = false) String authToken,
                                         @RequestParam Long id,
                                         @RequestParam(required = false) LocalDateTime start,
                                         @RequestParam(required = false) LocalDateTime end) {
        if (isNull(start) && isNull(end)) {
            return rentApartmentService.findApartmentByID(id);
        } else {
            securityService.checkValidToken(authToken);
            return rentApartmentService.bookingApartment(authToken, id, start, end);
        }
    }

    /**
     * Регистрация новых апартаментов
     */
    @PostMapping(REGISTRATION_NEW_APARTMENT)
    public String registrationNewApartment(@RequestHeader String authToken,
                                           @RequestBody RegistrationApartmentForm apartmentForm) {
        securityService.checkValidToken(authToken);
        rentApartmentService.registrationNewApartment(apartmentForm);
        return "Апартаменты успешно зарегистрированы";
    }

    /**
     * Поиск апартаментов в городе по геолокации.
     */
    @GetMapping(FIND_APARTMENT_BY_LOCATION)
    public List<ApartmentInfoDTO> findApartmentByLocation(@RequestParam String latitude, @RequestParam String longitude) {

        return geolocService.findApartmentByLoc(latitude, longitude);
    }

    @GetMapping("/rent/get_report")
    public String getReport(@RequestParam LocalDate month) {

        return rentApartmentService.getReport(month);
    }

    @GetMapping("/test")
    public String getMessageFromMyService(@RequestParam String message) {
        logger.info("RentApartmentController: ->getMessageFromMyService with param: " + message);
        return message;
    }
}
