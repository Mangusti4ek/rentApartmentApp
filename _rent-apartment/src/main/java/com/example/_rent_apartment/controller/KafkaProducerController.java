package com.example._rent_apartment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class KafkaProducerController {

    private final KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping("/get_message")
    public String getMessage(@RequestParam String message){
        kafkaTemplate.send("MyTopic", message);
        return "Сообщение опубликовано";
    }

    @PostMapping("/get_product_id")
    public Long getIdBookingHistoryForProduct(@RequestParam Long id) {
        kafkaTemplate.send("MyTopic", String.valueOf(id));
        return id;
    }

}
