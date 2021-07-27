package com.exadel.sandbox.team5.service.job;

import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.EmployeeDAO;
import com.exadel.sandbox.team5.service.MailSenderService;
import com.exadel.sandbox.team5.util.Notification;
import com.exadel.sandbox.team5.util.Pair;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Multimaps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
@Component
public class SubscriptionScheduler {
    private final EmployeeDAO employeeDAO;
    private final DiscountDAO discountDAO;
    private final MailSenderService mailSender;

    private static final int DELAY = 60000;

    @Scheduled(fixedRate = DELAY)
    public void senderSchedule() {
        Multimap<String, Pair> discountNamesByEmail = employeeDAO.getNotificationData().stream()
                .collect(Multimaps.toMultimap(Notification::getEmail, x -> new Pair(x.getDiscountId(), x.getDiscountName())
                        , MultimapBuilder.treeKeys().arrayListValues()::build));
        if (discountNamesByEmail.isEmpty()) return;
        discountNamesByEmail.asMap().forEach((k, v) -> mailSender.sendEmails(k, v.stream().map(Pair::getSecond).toList()));
        discountDAO.markDiscountsAsSent(discountNamesByEmail.values().stream()
                .map(x -> Long.parseLong(x.getFirst())).collect(Collectors.toList()));
    }
}
