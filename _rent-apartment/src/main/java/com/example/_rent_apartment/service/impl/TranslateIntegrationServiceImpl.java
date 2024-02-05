package com.example._rent_apartment.service.impl;

import com.example._rent_apartment.application_exeption.NotFoundConfigIntegrationException;
import com.example._rent_apartment.dto.GeoLocDTO.GeoCoderResponseDTO;
import com.example._rent_apartment.dto.TranslateDTO.TranslateResponseDTO;
import com.example._rent_apartment.model.entity.IntegrationInfoEntity;
import com.example._rent_apartment.repository.IntegrationInfoRepository;
import com.example._rent_apartment.service.TranslateIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.example._rent_apartment.constant.ApplicationConstant.GEO_LOC;
import static com.example._rent_apartment.constant.ApplicationConstant.TRANSLATOR;
import static com.example._rent_apartment.service.impl.Base64Service.decoder;

@Service
@RequiredArgsConstructor
public class TranslateIntegrationServiceImpl implements TranslateIntegrationService {

    private final IntegrationInfoRepository integrationInfoRepository;

    @Override
    public String translate(String txt) {
        RestTemplate restTemplate = new RestTemplate();
        TranslateResponseDTO body = restTemplate.exchange(prepareRequestPathByTranslate(txt),
                HttpMethod.GET,
                new HttpEntity<>(null),
                TranslateResponseDTO.class).getBody();
        return body.getDefinitions().get(0).getTr().get(0).getText();
    }

    private String prepareRequestPathByTranslate(String txt){
        IntegrationInfoEntity pathInfo = integrationInfoRepository.findById(TRANSLATOR)
                .orElseThrow(() -> new NotFoundConfigIntegrationException());

        return String.format(pathInfo.getId() + pathInfo.getPathValue(),pathInfo.getApiKey(),txt);

    }
}
