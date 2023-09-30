package com.example._rent_apartment.mapper;

import com.example._rent_apartment.dto.UserApplicationDTO;
import com.example._rent_apartment.model.Security.UserApplicationEntity;
import com.example._rent_apartment.service.impl.Base64Service;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface UserApplicationMapper {

    @Mapping(target = "commercial", source = "commercial")
    public UserApplicationEntity userDTOToUserEntity(UserApplicationDTO userApplicationDTO, String commercial);

    @AfterMapping
    public default void afterMapperPrepareDateAndBookingCount(@MappingTarget UserApplicationEntity userApplicationEntity) {
        userApplicationEntity.setBookingCount(0);
        userApplicationEntity.setDateTimeRegistration(LocalDateTime.now());
        userApplicationEntity.setPassword(Base64Service.encode(userApplicationEntity.getPassword()));
    }

}
