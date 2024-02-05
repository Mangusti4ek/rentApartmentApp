package com.example._rent_apartment.service.impl;

import com.example._rent_apartment.service.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageSenderImpl implements MessageSender {

    private final KafkaTemplate<String, String> kafkaTemplate;
    @Override
    public void send(String message) {

        kafkaTemplate.send("MyTopic", message);

    }
}
