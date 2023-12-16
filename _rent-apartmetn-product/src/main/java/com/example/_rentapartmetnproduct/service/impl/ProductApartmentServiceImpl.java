package com.example._rentapartmetnproduct.service.impl;

import com.example._rentapartmetnproduct.model.entity.BookingHistoryProductEntity;
import com.example._rentapartmetnproduct.repository.BookingHistoryProductRepository;
import com.example._rentapartmetnproduct.repository.ProductInfoProductRepository;
import com.example._rentapartmetnproduct.service.ProductApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductApartmentServiceImpl implements ProductApartmentService {
    private final BookingHistoryProductRepository bookingHistoryProductRepository;
    private final ProductInfoProductRepository productInfoProductRepository;
    @Override
    public Long prepareProduct(Long id) {
        BookingHistoryProductEntity bookingHistoryByID = bookingHistoryProductRepository.getBookingHistoryByID(id);
        bookingHistoryByID = chooseProductForBookingHistory(bookingHistoryByID);
        bookingHistoryProductRepository.save(bookingHistoryByID);
        return id;
    }

    private BookingHistoryProductEntity chooseProductForBookingHistory(BookingHistoryProductEntity booking){
        if(!booking.getUserApplication().getParentCity().equals(booking.getApartmentInfo().getAddressInfo().getCity())){
            booking.setProductInfo(productInfoProductRepository.getProductInfoByID(3L));
        } else if (isAutumn(booking.getBookingStartDate())) {
            booking.setProductInfo(productInfoProductRepository.getProductInfoByID(1L));
        } else if (booking.getUserApplication().getBookingCount() > 3) {
            booking.setProductInfo(productInfoProductRepository.getProductInfoByID(2L));
        }
        return booking;
    }

    private Boolean isAutumn(LocalDateTime startBooking) {
        return startBooking.getMonthValue() > 8 && startBooking.getMonthValue() < 12;
    }

}
