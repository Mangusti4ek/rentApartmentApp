package com.example._rentapartmetnproduct.repository;

import com.example._rentapartmetnproduct.model.entity.ProductInfoProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInfoProductRepository extends JpaRepository<ProductInfoProductEntity,Long> {
    @Query(value = "SELECT p from ProductInfoProductEntity p WHERE p.id = :id")
    public ProductInfoProductEntity getProductInfoByID(Long id);

}
