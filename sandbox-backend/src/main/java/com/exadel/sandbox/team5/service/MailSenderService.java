package com.exadel.sandbox.team5.service;

import java.util.List;

public interface MailSenderService {

    void sendEmailToUsers(String message);

    void sendEmails(String email, List<String> discountNames);
}
