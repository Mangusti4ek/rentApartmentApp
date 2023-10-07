package com.example._rent_apartment.service.impl;

import com.example._rent_apartment.service.UniqueTokenGenerateService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UniqueTokenGenerateServiceImpl implements UniqueTokenGenerateService {

    @Override
    public String createToken() {

        String tokenValue = UUID.randomUUID().toString();

        return tokenValue + "|" + LocalDateTime.now().plusDays(1);
    }
}
