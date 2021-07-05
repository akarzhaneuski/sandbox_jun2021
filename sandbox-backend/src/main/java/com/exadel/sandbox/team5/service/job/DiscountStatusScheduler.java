package com.exadel.sandbox.team5.service.job;

import com.exadel.sandbox.team5.dao.OrderDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class DiscountStatusScheduler {

    private final OrderDAO orderDAO;

    private static final int delayToInvalidateOrder = 1000*60;

    @Scheduled(fixedRate = delayToInvalidateOrder)
    public void reportCurrentTime() {
        log.debug("start scheduler");
        orderDAO.changePromoCodeStatusAfterExpirationTime(new Date());
        log.debug("stop scheduler");
    }
}
