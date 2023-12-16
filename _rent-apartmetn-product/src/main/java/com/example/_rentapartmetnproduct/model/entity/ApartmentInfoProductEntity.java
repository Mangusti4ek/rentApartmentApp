package com.example._rentapartmetnproduct.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "apartment_info")
public class ApartmentInfoProductEntity {
    @Id
    @SequenceGenerator(name = "apartment_infoSequence", sequenceName = "apartment_info_sequence", allocationSize = 1, initialValue = 4)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apartment_infoSequence")
    private Long id;

    @Column(name = "room_amount")
    private Integer roomAmount;

    @Column(name = "price")
    private Integer price;

    @Column(name = "overall_rating")
    private Double overallRating;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "registration_Date")
    private LocalDateTime registrationDate;

    @OneToOne()
    @JoinColumn(name = "address_id")
    private AddressInfoProductEntity addressInfo;
}
