package com.example._rent_apartment.application_scheduler;

import com.example._rent_apartment.model.Security.UserApplicationEntity;
import com.example._rent_apartment.repository.UserApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableScheduling
public class ApplicationScheduler {

    private final UserApplicationRepository applicationRepository;

    @Scheduled(fixedDelay = 60000)
    public void checkValidToken(){
        log.info("Планировщик начал свою работу " + LocalDateTime.now());
        List<UserApplicationEntity> userList = applicationRepository.getUserApplicationEntitiesByTokenIsNotNull();
        if(!userList.isEmpty()){
            log.info("Текущих сессий " + userList.size());
            for(UserApplicationEntity user : userList){
                String date = user.getToken().substring(37);
                LocalDateTime timeToken = LocalDateTime.parse(date);
                if(timeToken.isBefore(LocalDateTime.now())){
                    user.setToken(null);
                    applicationRepository.save(user);
                }
            }
        } else {
            log.info("Текущих сессий не обнаружено");
        }


    }

}
