package com.example._rent_apartment.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "photo_apartment")
public class PhotoApartmentEntity {

    @Id
    @SequenceGenerator(name = "photo_apartmentSequence", sequenceName = "photo_apartment_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photo_apartmentSequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private ApartmentInfoEntity apartmentInfo;

    @Column(name = "link")
    private String link;
}
