package com.example._rent_apartment.repository;

import com.example._rent_apartment.model.entity.AddressInfoEntity;
import com.example._rent_apartment.model.entity.BookingHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingHistoryRepository extends JpaRepository<BookingHistoryEntity, Long> {

//    @Query(value = "SELECT a FROM AddressInfoEntity a WHERE a.city = :city")
//    public  getAddressInfoEntitiesByCity(String city);



}
