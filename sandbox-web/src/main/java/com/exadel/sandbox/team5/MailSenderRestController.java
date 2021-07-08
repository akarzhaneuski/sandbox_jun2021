package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.Category;
import com.exadel.sandbox.team5.service.MailSenderService;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

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

    @PutMapping("/testSend")
    public String testSend(@RequestBody Long categoryId, String name) throws MessagingException, TemplateException, IOException {
        mailSenderService.testSend(categoryId, name);
        return "Email sent successfully";
    }
}
