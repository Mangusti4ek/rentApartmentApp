package com.example._rent_apartment.service.impl;

import com.example._rent_apartment.application_exeption.NotFoundConfigIntegrationException;
import com.example._rent_apartment.model.entity.IntegrationInfoEntity;
import com.example._rent_apartment.repository.IntegrationInfoRepository;
import com.example._rent_apartment.service.RentApartmentProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.example._rent_apartment.constant.ApplicationConstant.API_GATEWAY;

@Service
@RequiredArgsConstructor
public class RentApartmentProductServiceImpl implements RentApartmentProductService {

    private final IntegrationInfoRepository integrationInfoRepository;

    @Override
    public String getTestMessage() {
        String path = "/test";
        RestTemplate restTemplate = new RestTemplate();
        String answer = restTemplate.exchange(prepareRequestPath(path),
                HttpMethod.GET,
                new HttpEntity<>(null),
                String.class).getBody();

        return answer;
    }

    private String prepareRequestPath(String path){
        IntegrationInfoEntity pathInfo = integrationInfoRepository.findById(API_GATEWAY)
                .orElseThrow(() -> new NotFoundConfigIntegrationException());

        return String.format(pathInfo.getId() + pathInfo.getPathValue(), path);

    }
}
