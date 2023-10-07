package com.example._rent_apartment.controller;

import com.example._rent_apartment.dto.AuthUserDTO;
import com.example._rent_apartment.dto.UserApplicationDTO;
import com.example._rent_apartment.model.Security.AuthorizationResponse;
import com.example._rent_apartment.service.SecurityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example._rent_apartment.constant.ApplicationConstant.*;

@Tag(name = "Регистрация пользователей приложения", description = "Сервис аренды квартир")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SecurityController {

    private final SecurityService securityService;

    @PostMapping(REGISTRATION_NEW_USER)
    @Operation(summary = "Регистрация новых пользователей")
    @Parameter(name = "commercial", description = "Статус арендодателя", example = "true / false")
    public String registration(@RequestBody UserApplicationDTO userApplicationDTO, @RequestParam String commercial) {

        return securityService.registrationNewUser(userApplicationDTO, commercial);

    }

    @PostMapping()
    @Operation(summary = "Авторизация пользователя")
    public AuthorizationResponse authorization(@RequestBody AuthUserDTO authUserDTO) {
        return securityService.authorizationUser(authUserDTO);
    }

}
