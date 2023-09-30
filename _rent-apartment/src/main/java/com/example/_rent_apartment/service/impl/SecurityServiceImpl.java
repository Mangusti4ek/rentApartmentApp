package com.example._rent_apartment.service.impl;

import com.example._rent_apartment.dto.UserApplicationDTO;
import com.example._rent_apartment.mapper.UserApplicationMapper;
import com.example._rent_apartment.model.Security.UserApplicationEntity;
import com.example._rent_apartment.model.Security.UserSessionApplication;
import com.example._rent_apartment.repository.UserApplicationRepository;
import com.example._rent_apartment.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example._rent_apartment.constant.ApplicationConstant.*;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final UserApplicationRepository userApplicationRepository;
    private final UserApplicationMapper userApplicationMapper;
    private final UserSessionApplication userSessionApplication;

    @Override
    public String registrationNewUser(UserApplicationDTO userApplicationDTO, String commercial) {
        List<UserApplicationEntity> userByNickName = userApplicationRepository.getUserByNickName(userApplicationDTO.getNickName());
        if (!userByNickName.isEmpty()) {
            return NICKNAME_ERROR;
        }

        List<UserApplicationEntity> userByLogin = userApplicationRepository.getUserByLogin(userApplicationDTO.getLogin());
        if (!userByLogin.isEmpty()) {
            return LOGIN_ERROR;
        }

        userApplicationRepository.save(userApplicationMapper.userDTOToUserEntity(userApplicationDTO, commercial));

        return REGISTRATION_APPROVED;
    }

    @Override
    public String authorizationUser(UserApplicationDTO userApplicationDTO) {
        List<UserApplicationEntity> userByLogin = userApplicationRepository.getUserByLogin(userApplicationDTO.getLogin());

        if (userByLogin.isEmpty()) {
            return INCORRECT_LOGIN;
        }

        if (userApplicationDTO.getPassword().equals(Base64Service.decoder(userByLogin.get(0).getPassword()))) {
            userSessionApplication.setLogin(userByLogin.get(0).getLogin());
            userSessionApplication.setNickName(userByLogin.get(0).getNickName());
            return AUTHORIZATION_APPROVED;
        } else {
            return INCORRECT_PASSWORD;
        }

    }
}
