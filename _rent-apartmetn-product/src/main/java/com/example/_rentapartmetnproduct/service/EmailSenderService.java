package com.example._rentapartmetnproduct.service;

import org.springframework.web.bind.annotation.RequestParam;

public interface EmailSenderService {
    String sendEmail(String mail, String message, String subject);
}
