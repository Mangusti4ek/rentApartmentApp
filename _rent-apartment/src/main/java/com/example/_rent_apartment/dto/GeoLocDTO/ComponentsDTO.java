package com.example._rent_apartment.dto.GeoLocDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class ComponentsDTO {
    @JsonProperty("city")
    private String city;
    @JsonProperty("town")
    private String town;

}
