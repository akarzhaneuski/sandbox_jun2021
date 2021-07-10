package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.EmployeeDAO;
import com.exadel.sandbox.team5.entity.Category;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.service.MailSenderService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import freemarker.template.Configuration;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender sender;
    private final EmployeeDAO employeeDAO;
    private final Configuration freemarker;

    @Override
    public void sendEmailToUsers(String notification) {
        var message = sender.createMimeMessage();
        var helper = new MimeMessageHelper(message);
        freemarker.setClassForTemplateLoading(this.getClass(), "/template");
        Map<String, String> params = new HashMap<>();
        params.put("text",notification);
        try {
            var t = freemarker.getTemplate("notification.ftl");
            var text = FreeMarkerTemplateUtils.processTemplateIntoString(t, params);
            helper.setTo(employeeDAO.getAllEmails().toArray(new String[0]));
            helper.setSubject("Hello there");
            helper.setText(text, true);
        } catch (MessagingException | IOException | TemplateException e){
            log.error("Cannot send mail!", e);
        }
        sender.send(message);
    }

    @Override
    public void sendEmails(String email, List<String> discountNames) {
        var message = sender.createMimeMessage();
        var helper = new MimeMessageHelper(message);
        freemarker.setClassForTemplateLoading(this.getClass(), "/template");
        Map<String,List<String>> params = new HashMap<>();
        params.put("discounts", discountNames);
        try {
            var t = freemarker.getTemplate("mail.ftl");
            var text = FreeMarkerTemplateUtils.processTemplateIntoString(t, params);
            helper.setTo(email);
            helper.setSubject("New Discounts!!!");
            helper.setText(text, true);
        } catch (MessagingException | IOException | TemplateException e){
            log.error("Cannot send mail!", e);
        }
        sender.send(message);
    }
}