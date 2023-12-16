package com.example._rent_apartment.service.impl;

import com.example._rent_apartment.repository.IntegrationInfoRepository;
import com.example._rent_apartment.service.TranslateIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TranslateIntegrationServiceImpl implements TranslateIntegrationService {

    private final IntegrationInfoRepository integrationInfoRepository;

    @Override
    public String translate(String txt) {
        RestTemplate restTemplate = new RestTemplate();
        return null;
    }
}
