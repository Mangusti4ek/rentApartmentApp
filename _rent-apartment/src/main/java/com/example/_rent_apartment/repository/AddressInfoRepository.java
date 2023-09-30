package com.example._rent_apartment.repository;

import com.example._rent_apartment.model.entity.AddressInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressInfoRepository extends JpaRepository<AddressInfoEntity, Long> {

    @Query(value = "SELECT a FROM AddressInfoEntity a WHERE a.city = :city")
    public List<AddressInfoEntity> getAddressInfoEntitiesByCity(String city);

    @Query(value = "SELECT a FROM AddressInfoEntity a WHERE a.city = :city AND a.apartmentInfo.price <= :price")
    public List<AddressInfoEntity> getAddressInfoEntitiesByCityAndPrice(String city, Integer price);

    @Query(value = "SELECT a FROM AddressInfoEntity a WHERE a.city = :city AND a.apartmentInfo.price <= :price AND a.apartmentInfo.roomAmount = :roomAmount AND a.apartmentInfo.overallRating >= :overallRating")
    public List<AddressInfoEntity> getAddressInfoEntitiesByAllFilter(String city, Integer price, Integer roomAmount, Integer overallRating);

    @Query(value = "SELECT a FROM AddressInfoEntity a WHERE a.city = :city AND a.apartmentInfo.price <= :price AND a.apartmentInfo.roomAmount = :roomAmount")
    public List<AddressInfoEntity> getAddressInfoEntitiesByCityAndPriceAndRoomAmount(String city, Integer price, Integer roomAmount);

    @Query(value = "SELECT a FROM AddressInfoEntity a WHERE a.city = :city AND a.apartmentInfo.roomAmount = :roomAmount AND a.apartmentInfo.overallRating >= :overallRating")
    public List<AddressInfoEntity> getAddressInfoEntitiesByCityAndRoomAmountAndRating(String city, Integer roomAmount, Integer overallRating);

    @Query(value = "SELECT a FROM AddressInfoEntity a WHERE a.city = :city AND a.apartmentInfo.overallRating >= :overallRating")
    public List<AddressInfoEntity> getAddressInfoEntitiesByCityAndRating(String city, Integer overallRating);

    @Query(value = "SELECT a FROM AddressInfoEntity a WHERE a.city = :city AND a.apartmentInfo.roomAmount = :roomAmount")
    public List<AddressInfoEntity> getApartmentByCityAndRoomAmount(String city, Integer roomAmount);

    @Query(value = "SELECT a FROM AddressInfoEntity a WHERE a.city = :city AND a.apartmentInfo.price <= :price AND a.apartmentInfo.overallRating >= :overallRating")
    public List<AddressInfoEntity> getAddressInfoEntitiesByCityAndPriceAndRating(String city, Integer price, Integer overallRating);

}
