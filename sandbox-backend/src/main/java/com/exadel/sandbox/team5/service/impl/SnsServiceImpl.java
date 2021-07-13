package com.exadel.sandbox.team5.service.impl;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishResult;
import com.exadel.sandbox.team5.service.SnsService;
import com.exadel.sandbox.team5.util.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SnsServiceImpl implements SnsService {
    private final AmazonSNS amazonSNS;

    public PublishResult sendToAllUsers(Notification notification) {
        if(notification.getSubject()==null) return amazonSNS.publish("ToAllUsers", notification.getMessage());
        return amazonSNS.publish("ToAllUsers", notification.getMessage(), notification.getSubject());
    }
}
