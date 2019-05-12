package com.daxueshi.sqlwork.service;

public interface MailService {

    void sendHtmlMail(String to, String subject,String content);
}
