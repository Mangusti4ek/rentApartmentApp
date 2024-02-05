package com.example._rentapartmetnproduct.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "integration_info")
@Data
public class IntegrationInfoProductEntity {
    @Id
    private String id;
    @Column(name = "path_value")
    private String pathValue;
    @Column(name = "description")
    private String description;
    @Column(name = "api_key")
    private String apiKey;
}


