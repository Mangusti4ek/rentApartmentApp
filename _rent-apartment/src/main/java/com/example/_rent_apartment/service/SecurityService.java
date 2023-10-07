package com.example._rent_apartment.service;

import com.example._rent_apartment.dto.AuthUserDTO;
import com.example._rent_apartment.dto.UserApplicationDTO;
import com.example._rent_apartment.model.Security.AuthorizationResponse;

public interface SecurityService {

    public String registrationNewUser(UserApplicationDTO userApplicationDTO, String commercial);

    public AuthorizationResponse authorizationUser(AuthUserDTO authUserDTO);

    public void checkValidToken(String token);
}
