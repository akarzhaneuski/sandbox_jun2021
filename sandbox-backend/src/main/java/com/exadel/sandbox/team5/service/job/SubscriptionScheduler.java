package com.exadel.sandbox.team5.service.job;

import com.exadel.sandbox.team5.util.SubscriptionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Duration;
import java.time.Instant;

@RequiredArgsConstructor
@Slf4j
@Component
public class SubscriptionScheduler {
    private SubscriptionManager manager;

    private static final int delayToSchedule = 3600000;

    @PostConstruct
    public void loadManager(){
        //TODO deserialize manager
        log.error("it's loaded");
        manager = new SubscriptionManager();
        manager.setLastExecution(Instant.now().minus(Duration.ofHours(26)));
    }

    @Scheduled(fixedRate = delayToSchedule)
    public void senderSchedule(){
        if (manager.getLastExecution().isBefore(Instant.now().minus(Duration.ofHours(24)))) {
            //TODO send notifications
            log.error("sending emails");
        }
    }

    @PreDestroy
    public void saveManager(){
        //TODO serialize manager
        log.error("it's saved");
        manager = null;
    }
}
