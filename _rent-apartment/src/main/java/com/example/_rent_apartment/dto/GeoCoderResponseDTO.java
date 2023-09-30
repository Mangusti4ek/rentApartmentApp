package com.example._rent_apartment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class GeoCoderResponseDTO {
    @JsonProperty("results")
    private List<ResultsObjectsDTO> resultsObjects;

}
