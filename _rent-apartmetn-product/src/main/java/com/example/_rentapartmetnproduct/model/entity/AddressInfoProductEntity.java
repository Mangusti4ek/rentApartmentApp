package com.example._rentapartmetnproduct.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "address_info")
public class AddressInfoProductEntity {
    @Id
    @SequenceGenerator(name = "address_infoSequence", sequenceName = "address_info_sequence", allocationSize = 1, initialValue = 4)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_infoSequence")
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "building")
    private String building;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "addressInfo")
    private ApartmentInfoProductEntity apartmentInfo;
}
