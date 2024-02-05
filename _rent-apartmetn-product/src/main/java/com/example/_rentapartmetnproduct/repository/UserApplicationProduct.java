package com.example._rentapartmetnproduct.repository;

import com.example._rentapartmetnproduct.model.entity.ProductInfoProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserApplicationProduct {

    @Query(value = "SELECT u from UserApplicationProductEntity u WHERE u.id = :id")
    public ProductInfoProductEntity getUserApplicationByID(Long id);

}
