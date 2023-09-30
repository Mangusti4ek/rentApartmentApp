package com.example._rent_apartment.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "integration_info")
@Data
public class IntegrationInfoEntity {

    @Id
    private String id;
    @Column(name = "path_value")
    private String pathValue;
    @Column(name = "description")
    private String description;
    @Column(name = "api_key")
    private String apiKey;

}
