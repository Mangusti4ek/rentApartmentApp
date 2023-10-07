package com.example._rent_apartment.service.impl;

import com.example._rent_apartment.application_exeption.AuthException;
import com.example._rent_apartment.dto.AuthUserDTO;
import com.example._rent_apartment.dto.UserApplicationDTO;
import com.example._rent_apartment.mapper.UserApplicationMapper;
import com.example._rent_apartment.model.Security.AuthorizationResponse;
import com.example._rent_apartment.model.Security.UserApplicationEntity;
import com.example._rent_apartment.repository.UserApplicationRepository;
import com.example._rent_apartment.service.SecurityService;
import com.example._rent_apartment.service.UniqueTokenGenerateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example._rent_apartment.constant.ApplicationConstant.*;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final UserApplicationRepository userApplicationRepository;
    private final UserApplicationMapper userApplicationMapper;
    private final UniqueTokenGenerateService uniqueTokenGenerateService;

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
    public AuthorizationResponse authorizationUser(AuthUserDTO authUserDTO) {
        List<UserApplicationEntity> userByLogin = userApplicationRepository.getUserByLogin(authUserDTO.getLogin());

        if (userByLogin.isEmpty()) {
            throw new AuthException(INCORRECT_LOGIN);
        }
        UserApplicationEntity userApplicationEntity = userByLogin.get(0);
        if (authUserDTO.getPassword().equals(Base64Service.decoder(userApplicationEntity.getPassword()))) {
            String token = uniqueTokenGenerateService.createToken();
            userApplicationEntity.setToken(token);
            userApplicationRepository.save(userApplicationEntity);
            return new AuthorizationResponse(AUTHORIZATION_APPROVED,token);
        } else {
            throw new AuthException(INCORRECT_PASSWORD);
        }

    }

    @Override
    public void checkValidToken(String token) {
        UserApplicationEntity user = userApplicationRepository.getUserApplicationEntityByToken(token);
        if(isNull(user)){
            throw new AuthException(NON_VALID_TOKEN);
        }
    }
}
