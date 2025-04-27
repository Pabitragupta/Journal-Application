package com.example.journal.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Disabled
    @Test
    public void testSendMail(){
        emailService.sendEmail("sdebanjan692@gmail.com", "Testing java mail in different Email Id", "Hello coder, Everything is ok???");
    }
}
