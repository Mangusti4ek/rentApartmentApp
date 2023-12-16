package com.example._rentemailsender.service.impl;

import com.example._rentemailsender.service.SenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SenderServiceImpl implements SenderService {

    private final JavaMailSender javaMailSender;

    @Override
    public String sendEmail(String mail, String message, String subject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("erem3effalex@yandex.ru");
        simpleMailMessage.setTo(mail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        javaMailSender.send(simpleMailMessage);

        return "письмо отправлено";
    }
}
