package com.bank.shareaccount.email.service;

import org.springframework.data.crossstore.ChangeSetPersister;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public interface EmailService {
    public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException;
    public String sendSimpleMessage(String to)throws Exception;
    public String verifyEmail(String key) throws ChangeSetPersister.NotFoundException;
}
