package com.example._rent_apartment.model.Security;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserSessionApplication {

    private String login;

    private String nickName;
}
