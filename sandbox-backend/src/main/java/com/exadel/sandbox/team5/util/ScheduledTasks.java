package com.exadel.sandbox.team5.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@RequiredArgsConstructor
@Slf4j
@Configuration
@EnableScheduling
public class ScheduledTasks {

//    @Autowired
//    OrderDAO OrderDAO;
//
//    private final int delayToInvalidateOrder = 1000;
//
//    @Scheduled(fixedRate = delayToInvalidateOrder)
//    public void reportCurrentTime() {
//        log.info("*******************");
//        Date d = new Date();
//        OrderDAO.setPromoCodeStatusAfterExpirationTime(d);
//    }
}
