package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.entity.Category;
import com.exadel.sandbox.team5.entity.Discount;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface MailSenderService {

    void sendEmail();

    void sendEmailToSubscribers(Discount discount) throws MessagingException;

    void testSend(Long categoryId, String name) throws MessagingException, IOException, TemplateException;
}
