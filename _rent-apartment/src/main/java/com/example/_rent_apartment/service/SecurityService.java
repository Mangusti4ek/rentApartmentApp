package com.example._rent_apartment.service;

import com.example._rent_apartment.dto.UserApplicationDTO;

public interface SecurityService {

    public String registrationNewUser(UserApplicationDTO userApplicationDTO, String commercial);

    public String authorizationUser(UserApplicationDTO userApplicationDTO);
}
