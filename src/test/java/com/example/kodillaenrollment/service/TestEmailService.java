package com.example.kodillaenrollment.service;

import com.example.kodillaenrollment.domain.Mail;
import com.example.kodillaenrollment.service.email.EmailService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class TestEmailService implements EmailService {


    @Override
    public void send(Mail mail) {
        //do nothing
    }
}
