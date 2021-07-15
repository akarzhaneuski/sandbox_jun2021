package com.exadel.sandbox.team5.service.impl;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishResult;
import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.service.SnsService;
import com.exadel.sandbox.team5.util.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SnsServiceImpl implements SnsService {
    private final AmazonSNS amazonSNS;

    //    @Value("${app.snsRegion}")
    private final String REGION = "us-east-2";

    private final String ARN = String.format("arn:aws:sns:%s:468080558953:", REGION);

    public PublishResult sendToAllUsers(Message message) {
        if (message.getSubject() == null)
            return amazonSNS.publish("arn:aws:sns:us-east-2:468080558953:ToAllUsers", message.getMessage());
        return amazonSNS.publish("arn:aws:sns:us-east-2:468080558953:ToAllUsers", message.getMessage(), message.getSubject());
    }

    public void sendToSubscribers(DiscountDto discountDto) {
        var topic = ARN + discountDto.getCategory().getName();
        var message = String.format("New discount %s is waiting for you!", discountDto.getName());
        amazonSNS.publish(topic, message, "Subscription notification");
    }
}
