package com.example._rent_apartment.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product_info")
public class ProductInfoEntity {

    @Id
    @SequenceGenerator(name = "product_infoSequence", sequenceName = "product_info_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_infoSequence")
    private Long id;

    @Column(name = "description")
    private String description;
    @Column(name = "discount")
    private Integer discount;
    @Column(name = "season")
    private String season;

}
