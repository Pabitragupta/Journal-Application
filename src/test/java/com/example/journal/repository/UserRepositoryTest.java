package com.example.journal.repository;

import com.example.journal.entity.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Disabled("tested")
    @Test
//    @Transactional
    public void testGetUserForSA() {
//
//        List<User> users = userRepository.getUserForSA();
//
//
//        assertNotNull(users, "The list of users should not be null");
//
//
//        assertTrue(users.size() > 0, "There should be at least one user with valid email and sentimentAnalysis = true");
//

//        for (User user : users) {
//            assertTrue(user.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"), "Invalid email format for user: " + user.getEmail());
//            assertTrue(user.isSentimentAnalysis(), "Sentiment analysis should be true for user: " + user.getEmail());
//        }
    }
}
