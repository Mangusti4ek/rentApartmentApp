package com.example._rentapartmetnproduct.service.impl;

import com.example._rentapartmetnproduct.model.entity.IntegrationInfoProductEntity;
import com.example._rentapartmetnproduct.repository.IntegrationInfoProductRepository;
import com.example._rentapartmetnproduct.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    private final IntegrationInfoProductRepository integrationInfoProductRepository;
    @Override
    public String sendEmail(String mail, String message, String subject) {
        RestTemplate restTemplate = new RestTemplate();
        return  restTemplate.exchange(prepareRequestPathByEmailSender(mail, message, subject),
                HttpMethod.GET,
                new HttpEntity<>(null),
                String.class).getBody();
    }

    private String prepareRequestPathByEmailSender(String mail, String message, String subject) {
        IntegrationInfoProductEntity pathInfo = integrationInfoProductRepository.findById("http://localhost:9098")
                .orElseThrow(() -> new RuntimeException());

        return String.format(pathInfo.getId() + pathInfo.getPathValue(),mail,message,subject);

    }
}
