package com.example._rentemailsender.controller;

import com.example._rentemailsender.service.SenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RentMailSenderController {


    private final SenderService senderService;

    @GetMapping("/get_mail")
    public String getMail(@RequestParam String mail, @RequestParam String message, @RequestParam String subject){

        return senderService.sendEmail(mail, message, subject);
    }
}
