package com.example._rent_apartment.service.impl;

import com.example._rent_apartment.application_exeption.ApartmentNonExistentException;
import com.example._rent_apartment.dto.ApartmentInfoDTO;
import com.example._rent_apartment.mapper.RentMapper;
import com.example._rent_apartment.model.RegistrationApartmentForm;
import com.example._rent_apartment.model.Security.UserApplicationEntity;
import com.example._rent_apartment.model.entity.AddressInfoEntity;
import com.example._rent_apartment.model.entity.ApartmentInfoEntity;
import com.example._rent_apartment.model.entity.BookingHistoryEntity;
import com.example._rent_apartment.repository.AddressInfoRepository;
import com.example._rent_apartment.repository.ApartmentInfoRepository;
import com.example._rent_apartment.repository.BookingHistoryRepository;
import com.example._rent_apartment.repository.UserApplicationRepository;
import com.example._rent_apartment.service.IntegrationManagerService;
import com.example._rent_apartment.service.MessageSender;
import com.example._rent_apartment.service.RentApartmentService;
import com.example._rent_apartment.service.TranslateIntegrationService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.example._rent_apartment.constant.ApplicationConstant.*;

@Service
@RequiredArgsConstructor
public class RentApartmentServiceImpl implements RentApartmentService {

    private final UserApplicationRepository userApplicationRepository;
    private final AddressInfoRepository addressInfoRepository;
    private final ApartmentInfoRepository apartmentInfoRepository;
    private final BookingHistoryRepository bookingHistoryRepository;
    private final IntegrationManagerService integrationManagerService;
    private final RentMapper rentMapper;
    private final MessageSender messageSender;
    private final TranslateIntegrationService translateIntegrationService;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public List<ApartmentInfoDTO> getApartmentByCity(String city) {
        List<AddressInfoEntity> addressInfoEntitiesByCity = addressInfoRepository.getAddressInfoEntitiesByCity(city);
        return addressInfoEntitiesByCity.stream()
                .map(a -> rentMapper.prepareApartmentResponse(a, a.getApartmentInfo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentInfoDTO> getApartmentByAllFilter(String city, Integer price, Integer roomAmount, Double overallRating) {
        return addressInfoRepository.getAddressInfoEntitiesByAllFilter(city, price, roomAmount, overallRating).stream()
                .map(a -> rentMapper.prepareApartmentResponse(a, a.getApartmentInfo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentInfoDTO> getAddressInfoEntitiesByCityAndPriceAndRoomAmount(String city, Integer price, Integer roomAmount) {
        List<AddressInfoEntity> addressInfoEntitiesByCityAndPriceAndRoomAmount = addressInfoRepository.getAddressInfoEntitiesByCityAndPriceAndRoomAmount(city, price, roomAmount);
        return addressInfoEntitiesByCityAndPriceAndRoomAmount.stream()
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
    public List<ApartmentInfoDTO> getAddressInfoEntitiesByCityAndPriceAndRating(String city, Integer price, Double overallRating) {
        return addressInfoRepository.getAddressInfoEntitiesByCityAndPriceAndRating(city, price, overallRating).stream()
                .map(a -> rentMapper.prepareApartmentResponse(a, a.getApartmentInfo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentInfoDTO> getAddressInfoEntitiesByCityAndRoomAmountAndRating(String city, Integer roomAmount, Double overallRating) {
        return addressInfoRepository.getAddressInfoEntitiesByCityAndRoomAmountAndRating(city, roomAmount, overallRating).stream()
                .map(a -> rentMapper.prepareApartmentResponse(a, a.getApartmentInfo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentInfoDTO> getAddressInfoEntitiesByCityAndRating(String city, Double overallRating) {
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

    public List<ApartmentInfoDTO> prepareFieldToCalc(String city, Integer price, Integer roomAmount, Double overallRating) {
        city = translateIntegrationService.translate(city);
        if (price != null && roomAmount != null && overallRating != null) {
            return getApartmentByAllFilter(city, price, roomAmount, overallRating);
        } else if (price != null) {
            if (roomAmount != null) {
                return getAddressInfoEntitiesByCityAndPriceAndRoomAmount(city, price, roomAmount);
            } else if (overallRating != null) {
                return getAddressInfoEntitiesByCityAndPriceAndRating(city, price, overallRating);
            } else {
                return getAddressInfoEntitiesByCityAndPrice(city, price);
            }
        } else if (overallRating != null) {
            if (roomAmount != null) {
                return getAddressInfoEntitiesByCityAndRoomAmountAndRating(city, roomAmount, overallRating);
            } else {
                return getAddressInfoEntitiesByCityAndRating(city, overallRating);
            }
        } else if (roomAmount != null) {
            return getApartmentByCityAndRoomAmount(city, roomAmount);
        } else {
            return getApartmentByCity(city);
        }
    }

    @Override
    public ApartmentInfoDTO findApartmentByID(Long id) {

        ApartmentInfoEntity result = apartmentInfoRepository.findById(id).orElseThrow(() -> new ApartmentNonExistentException());
        return rentMapper.prepareApartmentResponse(result.getAddressInfo(), result);

    }

    @Override
    public ApartmentInfoDTO bookingApartment(String authToken, Long id, LocalDateTime start, LocalDateTime end) {

        ApartmentInfoDTO apartmentByID = findApartmentByID(id);

        if (apartmentByID.getAvailable().equals(false)) {
            apartmentByID.setMessage(APARTMENT_NOT_AVAILABLE);
            return apartmentByID;
        }
        ApartmentInfoEntity apartmentEntityByID = prepareApartmentEntity(id);

        UserApplicationEntity userByLogin = userApplicationRepository.getUserApplicationEntityByToken(authToken);

        BookingHistoryEntity bookingHistoryEntity = prepareBookingHistory(apartmentEntityByID, userByLogin, start, end);
        bookingHistoryRepository.save(bookingHistoryEntity);

        try {
            integrationManagerService.prepareProductDiscount(bookingHistoryEntity.getId());
        } catch (Exception ex) {
            apartmentByID.setMessage(APARTMENT_BOOKING_SUCCESS_WITH_OUT + start);
            messageSender.send(String.valueOf(bookingHistoryEntity.getId()));
            return apartmentByID;
        }

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

    @Override
    public String registrationNewApartment(RegistrationApartmentForm registrationApartmentForm) {
        ApartmentInfoEntity newApartment = prepareApartmentInfoEntityForRegistration(registrationApartmentForm);
        addressInfoRepository.save(newApartment.getAddressInfo());
        apartmentInfoRepository.save(newApartment);
        return "Апартаменты успешно зарегестрированы";
    }

    private ApartmentInfoEntity prepareApartmentInfoEntityForRegistration(RegistrationApartmentForm registrationApartmentForm) {
        ApartmentInfoEntity newApartment = new ApartmentInfoEntity();
        newApartment.setRoomAmount(registrationApartmentForm.getRoomsCount());
        newApartment.setPrice(registrationApartmentForm.getPrice());
        newApartment.setAvailable(true);
        newApartment.setRegistrationDate(LocalDateTime.now());
        newApartment.setAddressInfo(prepareAddressInfoForRegistration(registrationApartmentForm));
        newApartment.getAddressInfo().setApartmentInfo(newApartment);
        return newApartment;
    }

    private AddressInfoEntity prepareAddressInfoForRegistration(RegistrationApartmentForm registrationApartmentForm) {
        AddressInfoEntity addressInfoEntity = new AddressInfoEntity();
        addressInfoEntity.setStreet(registrationApartmentForm.getStreet());
        addressInfoEntity.setBuilding(registrationApartmentForm.getBuildingNumber());
        addressInfoEntity.setCity(registrationApartmentForm.getCity());
        return addressInfoEntity;
    }

    @Override
    public String getReport(LocalDate month) {
        LocalDate intervalReport = month.plusMonths(1);
        List<BookingHistoryEntity> bookingList = bookingHistoryRepository.findAll();
        File file = new File("D:\\JavaProjekts\\Learning\\_rent-apartment-application\\template\\report.xlsx");
        try (FileInputStream fileInputStream = new FileInputStream(file);
             XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream)) {

            XSSFSheet sheet = workBook.getSheetAt(0);
            int count = 1;

            for (BookingHistoryEntity b : bookingList) {

                XSSFRow row = sheet.createRow(count++);
                row.createCell(0).setCellValue(b.getBookingStartDate());
                row.createCell(1).setCellValue(getBookingDays(b.getBookingStartDate(), b.getBookingEndDate()));
                row.createCell(2).setCellValue(getBookingDays(b.getBookingStartDate(), b.getBookingEndDate()) * b.getApartmentInfo().getPrice());
                row.createCell(3).setCellValue(getAddress(b));
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workBook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return "Успешно создано";
        } catch (IOException e) {
            throw new RuntimeException("Проблемы с формированием отчета");
        }
    }

    private Long getBookingDays(LocalDateTime startDate, LocalDateTime endDate) {

        Duration between = Duration.between(startDate, endDate);

        return between.toDays();

    }

    private String getAddress(BookingHistoryEntity b) {

        return b.getApartmentInfo().getAddressInfo().getStreet() + " " + b.getApartmentInfo().getAddressInfo().getBuilding();


    }
}
