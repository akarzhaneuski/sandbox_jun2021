package com.exadel.sandbox.team5.service.impl;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishResult;
import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.service.NotificationService;
import com.exadel.sandbox.team5.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final AmazonSNS amazonSNS;

    @Value("${app.snsRegion}")
    private String region;

    public PublishResult sendToAllUsers(Message message) {
        if (message.getSubject() == null)
            return amazonSNS.publish(getARN(), message.getMessage());
        return amazonSNS.publish(getARN(), message.getMessage(), message.getSubject());
    }

    public void sendToSubscribers(DiscountDto discountDto) {
        var message = String.format("New discount %s is waiting for you!", discountDto.getName());
        amazonSNS.publish(getARN(), message, "Special offer");
    }

    public void subscribeUser(String email) {
        amazonSNS.subscribe(email, "email", getARN());
    }

    private String getARN() {
        return String.format("arn:aws:sns:%s:468080558953:ToAllUsers",region);
    }
}
