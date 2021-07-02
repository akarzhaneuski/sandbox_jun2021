package com.exadel.sandbox.team5.service.job;

import com.exadel.sandbox.team5.dao.OrderDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Slf4j
@Service
public class DiscountStatusScheduler {

    private final OrderDAO orderDAO;

    private static final int delayToInvalidateOrder = 1000;

    @Scheduled(fixedRate = delayToInvalidateOrder)
    public void reportCurrentTime() {
        log.debug("start scha");
        orderDAO.setPromoCodeStatusAfterExpirationTime(new Date());
        log.info("*******************");
    }
}
