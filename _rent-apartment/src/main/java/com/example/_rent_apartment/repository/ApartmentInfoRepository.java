package com.example._rent_apartment.repository;

import com.example._rent_apartment.model.entity.ApartmentInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentInfoRepository extends JpaRepository<ApartmentInfoEntity, Long> {

//    @Query(nativeQuery = true, value = "SELECT * FROM apartment_info WHERE price = :price AND room_amount = :roomAmount AND overall_rating = :overallRating")
//    public List<ApartmentInfoEntity> getApartmentInfoEntitiesbyFiltrs(Integer price, Integer roomAmount, Integer overallRating);

}
