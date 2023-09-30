package com.example._rent_apartment.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "apartment_rating")
public class ApartmentRatingEntity {

    @Id
    @SequenceGenerator(name="apartment_ratingSequence", sequenceName="apartment_rating_sequence", allocationSize = 1, initialValue = 7)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="apartment_ratingSequence")
    private Long id;

    @Column(name = "comment",length = 1000)
    private String comment;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private ApartmentInfoEntity apartmentInfo;

}
