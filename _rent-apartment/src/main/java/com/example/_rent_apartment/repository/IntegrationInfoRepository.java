package com.example._rent_apartment.repository;

import com.example._rent_apartment.model.entity.IntegrationInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegrationInfoRepository extends JpaRepository<IntegrationInfoEntity,String> {

}
