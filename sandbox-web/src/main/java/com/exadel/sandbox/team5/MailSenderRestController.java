package com.exadel.sandbox.team5;

import com.amazonaws.services.sns.model.PublishResult;
import com.exadel.sandbox.team5.service.SnsService;
import com.exadel.sandbox.team5.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailSenderRestController {

    private final SnsService snsService;

    @GetMapping("/send")
    public PublishResult sendEmailToUsers(Message message) {
        return snsService.sendToAllUsers(message);
    }
}
