package com.apsolutions.util;

import org.springframework.stereotype.Service;

@Service
public class MailGenerator {
    private final MailManager mailManager;

    public MailGenerator(MailManager mailManager) {
        this.mailManager = mailManager;
    }

    public void sendMessage(String emailTo, String message) {
        mailManager.sendMessage(emailTo, message, false);
    }

    public void sendMessageHTML(String emailTo, String message) {
        mailManager.sendMessage(emailTo, message, true);
    }
}
