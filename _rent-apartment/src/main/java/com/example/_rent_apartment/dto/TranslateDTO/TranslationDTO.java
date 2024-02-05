package com.example._rent_apartment.dto.TranslateDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class TranslationDTO {
    @JsonProperty("text")
    private String text;
}
