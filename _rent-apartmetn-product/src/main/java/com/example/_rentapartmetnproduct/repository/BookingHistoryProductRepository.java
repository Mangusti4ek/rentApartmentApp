package com.example._rentapartmetnproduct.repository;

import com.example._rentapartmetnproduct.model.entity.BookingHistoryProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingHistoryProductRepository extends JpaRepository<BookingHistoryProductEntity, Long> {

    @Query(value = "SELECT b from BookingHistoryProductEntity b WHERE b.id = :id")
    public BookingHistoryProductEntity getBookingHistoryByID(Long id);
}
