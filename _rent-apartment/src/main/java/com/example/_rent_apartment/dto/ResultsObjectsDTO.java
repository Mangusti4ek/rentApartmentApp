package com.example._rent_apartment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class ResultsObjectsDTO {
    @JsonProperty("components")
    private ComponentsDTO componentsDTO;

}
