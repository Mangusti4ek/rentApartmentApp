package com.example._rent_apartment.mapper;

import com.example._rent_apartment.dto.ApartmentInfoDTO;
import com.example._rent_apartment.model.entity.AddressInfoEntity;
import com.example._rent_apartment.model.entity.ApartmentInfoEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RentMapper {

    @Mapping(target = "overallRating", ignore = true)
    public ApartmentInfoDTO prepareApartmentResponse(AddressInfoEntity addressInfoEntity, ApartmentInfoEntity apartmentInfoEntity);

    @AfterMapping
    default void afterMapperPrepareOverallRating(@MappingTarget ApartmentInfoDTO apartmentInfoDTO) {
        Double averageRating = apartmentInfoDTO.getApartmentRatingEntities().stream()
                .mapToDouble(a -> a.getRating())
                .average()
                .getAsDouble();
        String format = String.format("%.2f", averageRating).replace(',', '.');
        apartmentInfoDTO.setOverallRating(Double.parseDouble(format));
    }

}
