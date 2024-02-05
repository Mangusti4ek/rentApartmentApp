package com.example._rent_apartment.dto.TranslateDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class DefinitionTranslateDTO {

    @JsonProperty("tr")
    private List<TranslationDTO> tr;

}
