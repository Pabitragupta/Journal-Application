package com.example.journal.scheduler.userscheduler;

import com.example.journal.scheduler.UserScheduler;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulerTest {

    @Autowired
    private UserScheduler userScheduler;


    @Disabled
    @Test
    public void checkingMailService(){
        userScheduler.fetchUsersAndSendSaMail();
    }
}
