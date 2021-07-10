package com.exadel.sandbox.team5.service.job;

import com.exadel.sandbox.team5.dao.EmployeeDAO;
import com.exadel.sandbox.team5.dao.ParamsDAO;
import com.exadel.sandbox.team5.service.MailSenderService;
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

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
@Component
public class SubscriptionScheduler {
    private final EmployeeDAO employeeDAO;
    private final ParamsDAO paramsDAO;
    private final MailSenderService mailSender;

    private static final int delay = 3600000 * 12;

    @Scheduled(fixedRate = delay)
    public void senderSchedule() {
        Multimap<String, String> emails = employeeDAO.getNewDiscounts().stream()
                .collect(Multimaps.toMultimap(Pair::getFirst, Pair::getSecond, MultimapBuilder.treeKeys().arrayListValues()::build));
        if (emails.isEmpty()) return;
        emails.asMap().forEach((k, v) -> mailSender.sendEmails(k, List.copyOf(v)));
        paramsDAO.updateLastExecutionTime(String.valueOf(Instant.now()));
    }
}
