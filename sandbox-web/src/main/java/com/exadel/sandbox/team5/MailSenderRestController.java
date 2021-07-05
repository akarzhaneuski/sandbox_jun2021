package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailSenderRestController {

    private final MailSenderService mailSenderService;

    @GetMapping("/send")
    public String sendEmail(){
        mailSenderService.sendEmail();
        return "Email sent successfully";
    }
}
