package com.example._rent_apartment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ApartmentInfoDTO extends InfoObject{

    public ApartmentInfoDTO(String message) {
        super.setMessage(message);
    }

    private Integer roomAmount;

    private Integer price;

    private Double overallRating;

    private Boolean available;

    private LocalDateTime registrationDate;

    private String city;

    private String street;

    private String building;

    private List<ApartmentRatingDTO> apartmentRatingEntities;

}
