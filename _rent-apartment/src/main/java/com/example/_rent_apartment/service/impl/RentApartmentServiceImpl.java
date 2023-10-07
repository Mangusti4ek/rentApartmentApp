package com.example._rent_apartment.service.impl;

import com.example._rent_apartment.application_exeption.ApartmentNonExistentException;
import com.example._rent_apartment.dto.ApartmentInfoDTO;
import com.example._rent_apartment.mapper.RentMapper;
import com.example._rent_apartment.model.Security.UserApplicationEntity;
import com.example._rent_apartment.model.entity.ApartmentInfoEntity;
import com.example._rent_apartment.model.entity.BookingHistoryEntity;
import com.example._rent_apartment.repository.AddressInfoRepository;
import com.example._rent_apartment.repository.ApartmentInfoRepository;
import com.example._rent_apartment.repository.BookingHistoryRepository;
import com.example._rent_apartment.repository.UserApplicationRepository;
import com.example._rent_apartment.service.RentApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.example._rent_apartment.constant.ApplicationConstant.*;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class RentApartmentServiceImpl implements RentApartmentService {

    private final UserApplicationRepository userApplicationRepository;
    private final AddressInfoRepository addressInfoRepository;
    private final ApartmentInfoRepository apartmentInfoRepository;
    private final RentMapper rentMapper;
    private final BookingHistoryRepository bookingHistoryRepository;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public List<ApartmentInfoDTO> getApartmentByCity(String city) {
        return addressInfoRepository.getAddressInfoEntitiesByCity(city).stream()
                .map(a -> rentMapper.prepareApartmentResponse(a, a.getApartmentInfo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentInfoDTO> getApartmentByAllFilter(String city, Integer price, Integer roomAmount, Integer overallRating) {
        return addressInfoRepository.getAddressInfoEntitiesByAllFilter(city, price, roomAmount, overallRating).stream()
                .map(a -> rentMapper.prepareApartmentResponse(a, a.getApartmentInfo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentInfoDTO> getAddressInfoEntitiesByCityAndPriceAndRoomAmount(String city, Integer price, Integer roomAmount) {
        return addressInfoRepository.getAddressInfoEntitiesByCityAndPriceAndRoomAmount(city, price, roomAmount).stream()
                .map(a -> rentMapper.prepareApartmentResponse(a, a.getApartmentInfo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentInfoDTO> getAddressInfoEntitiesByCityAndPrice(String city, Integer price) {
        return addressInfoRepository.getAddressInfoEntitiesByCityAndPrice(city, price).stream()
                .map(a -> rentMapper.prepareApartmentResponse(a, a.getApartmentInfo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentInfoDTO> getAddressInfoEntitiesByCityAndPriceAndRating(String city, Integer price, Integer overallRating) {
        return addressInfoRepository.getAddressInfoEntitiesByCityAndPriceAndRating(city, price, overallRating).stream()
                .map(a -> rentMapper.prepareApartmentResponse(a, a.getApartmentInfo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentInfoDTO> getAddressInfoEntitiesByCityAndRoomAmountAndRating(String city, Integer roomAmount, Integer overallRating) {
        return addressInfoRepository.getAddressInfoEntitiesByCityAndRoomAmountAndRating(city, roomAmount, overallRating).stream()
                .map(a -> rentMapper.prepareApartmentResponse(a, a.getApartmentInfo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentInfoDTO> getAddressInfoEntitiesByCityAndRating(String city, Integer overallRating) {
        return addressInfoRepository.getAddressInfoEntitiesByCityAndRating(city, overallRating).stream()
                .map(a -> rentMapper.prepareApartmentResponse(a, a.getApartmentInfo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentInfoDTO> getApartmentByCityAndRoomAmount(String city, Integer roomAmount) {
        return addressInfoRepository.getApartmentByCityAndRoomAmount(city, roomAmount).stream()
                .map(a -> rentMapper.prepareApartmentResponse(a, a.getApartmentInfo()))
                .collect(Collectors.toList());
    }

    public List<ApartmentInfoDTO> prepareFieldToCalc(String city, Integer price, Integer roomAmount, Integer overallRating) {
        if (price != null || roomAmount != null || overallRating != null) {
            if (price != null && roomAmount != null && overallRating != null) {
                return getApartmentByAllFilter(city, price, roomAmount, overallRating);
            } else {
                if (price != null) {
                    if (roomAmount != null) {
                        return getAddressInfoEntitiesByCityAndPriceAndRoomAmount(city, price, roomAmount);
                    } else {
                        if (overallRating != null) {
                            return getAddressInfoEntitiesByCityAndPriceAndRating(city, price, overallRating);
                        } else {
                            return getAddressInfoEntitiesByCityAndPrice(city, price);
                        }
                    }
                } else if (overallRating != null) {
                    if (roomAmount != null) {
                        return getAddressInfoEntitiesByCityAndRoomAmountAndRating(city, roomAmount, overallRating);
                    } else {
                        return getAddressInfoEntitiesByCityAndRating(city, overallRating);
                    }
                } else if (roomAmount != null) {
                    return getApartmentByCityAndRoomAmount(city, roomAmount);
                }
            }
        } else {
            return getApartmentByCity(city);
        }
        throw new RuntimeException();
    }

    @Override
    public ApartmentInfoDTO findApartmentByID(Long id) {

        ApartmentInfoEntity result = apartmentInfoRepository.findById(id).orElseThrow(() -> new ApartmentNonExistentException());
        return rentMapper.prepareApartmentResponse(result.getAddressInfo(), result);

    }

    @Override
    public ApartmentInfoDTO bookingApartment(String authToken,Long id, LocalDateTime start, LocalDateTime end) {

        /*if (isNull(userSessionApplication.getLogin())) {
            return new ApartmentInfoDTO(BOOKING_ONLY_FOR_USERS);
        }*/
        ApartmentInfoDTO apartmentByID = findApartmentByID(id);

        if (apartmentByID.getAvailable().equals(false)) {
            apartmentByID.setMessage(APARTMENT_NOT_AVAILABLE);
            return apartmentByID;
        }
        ApartmentInfoEntity apartmentEntityByID = prepareApartmentEntity(id);

        UserApplicationEntity userByLogin = userApplicationRepository.getUserApplicationEntityByToken(authToken);

        BookingHistoryEntity bookingHistoryEntity = prepareBookingHistory(apartmentEntityByID, userByLogin, start, end);
        bookingHistoryRepository.save(bookingHistoryEntity);

        apartmentByID.setMessage(APARTMENT_BOOKING_SUCCESS + start);
        return apartmentByID;
    }

    private ApartmentInfoEntity findApartmentEntityByID(Long id) {
        return apartmentInfoRepository.findById(id).orElseThrow(() -> new ApartmentNonExistentException());
    }

    private ApartmentInfoEntity prepareApartmentEntity(Long id) {
        ApartmentInfoEntity apartmentEntityByID = findApartmentEntityByID(id);
        apartmentEntityByID.setAvailable(false);
        apartmentInfoRepository.save(apartmentEntityByID);
        return apartmentEntityByID;
    }

    private BookingHistoryEntity prepareBookingHistory(ApartmentInfoEntity apartmentEntityByID, UserApplicationEntity userByLogin, LocalDateTime start, LocalDateTime end) {
        BookingHistoryEntity bookingHistoryEntity = new BookingHistoryEntity();
        bookingHistoryEntity.setApartmentInfo(apartmentEntityByID);
        bookingHistoryEntity.setUserApplication(userByLogin);
        String startBooking = start.format(dateTimeFormatter);
        String endBooking = end.format(dateTimeFormatter);
        bookingHistoryEntity.setBookingStartDate(LocalDateTime.parse(startBooking, dateTimeFormatter));
        bookingHistoryEntity.setBookingEndDate(LocalDateTime.parse(endBooking, dateTimeFormatter));
        return bookingHistoryEntity;
    }
}
