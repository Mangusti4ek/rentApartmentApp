package com.example._rentapartmetnproduct.consumerConfiguration;

import lombok.Getter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class ConsumerListener {

    private final List<String> messages = new ArrayList<>();

    @KafkaListener(topics = "MyTopic", groupId = "Kafka_Test")
    public void listen(String message){
        synchronized (messages){
            messages.add(message);
        }
    }
}
