package com.fivemybab.ittabab.config.redis;

public interface MailService {

    void sendEmail(String to, String subject, String body);
}
