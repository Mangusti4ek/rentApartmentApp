package com.example._rentapartmetnproduct.application_scheduler;

import com.example._rentapartmetnproduct.consumerConfiguration.ConsumerListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@EnableScheduling
@RequiredArgsConstructor
@Component
public class KafkaConsumerScheduler {


    private final ConsumerListener consumerListener;

    @Scheduled(fixedDelay = 60000)
    public void checkTopicValue(){
        log.info("планировщик начал работу " + LocalDateTime.now());
        List<String> messages = consumerListener.getMessages();
        System.out.println(messages);
        log.info("работа окончена " + LocalDateTime.now());
    }

}
