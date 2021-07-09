package com.exadel.sandbox.team5.service.job;

import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.ParamsDAO;
import com.exadel.sandbox.team5.entity.Params;
import com.exadel.sandbox.team5.service.MailSenderService;
import com.exadel.sandbox.team5.util.Pair;
import com.exadel.sandbox.team5.util.SubscriptionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
@Component
public class SubscriptionScheduler {
    private final DiscountDAO discountDAO;
    private final ParamsDAO paramsDAO;
    private final MailSenderService mailSender;

    private static final int delayToSchedule = 3600000;

    /*@PostConstruct
    public void loadManager() {
        //TODO deserialize manager
        log.error("it's loaded");
        manager = new SubscriptionManager();
        manager.setLastExecution(Instant.now().minus(Duration.ofHours(26)));
    }*/

    @Scheduled(fixedRate = delayToSchedule)
    public void senderSchedule() {
        Map<Long, String> newDiscounts = discountDAO.getNewDiscounts().stream().collect(Collectors.toMap(x->Long.valueOf(x.getFirst()), Pair::getSecond));
//        Multimap<Long, String> test;
        if(newDiscounts.isEmpty()) return;
        log.error(newDiscounts.toString());
        //TODO send mails
//        newDiscounts.entrySet().stream().forEach(mailSender.testSend());
        paramsDAO.updateLastExecutionTime(String.valueOf(Instant.now()));
        log.error(String.valueOf(Instant.now()));
    }

    /*@PreDestroy
    public void saveManager() {
        //TODO serialize manager
        log.error("it's saved");
        manager = null;
    }*/
}
