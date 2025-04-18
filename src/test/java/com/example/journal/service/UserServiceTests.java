package com.example.journal.service;

import com.example.journal.entity.User;
import com.example.journal.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

//    @Autowired
//    private UserRepository userRepository;

//    @ParameterizedTest
//    @Transactional
//    @ValueSource(strings = {
//            "Too",
//            "Ram",
//            "Hi"
//    })
//    public void testFindByUserName(String name){
////        User user = userRepository.findByUserName("Hi");
////        assertTrue(!user.getJournalEntries().isEmpty());
//        assertNotNull(userRepository.findByUserName(name));
//    }


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user) {
        assertTrue(userService.saveNewEntry(user));
    }



    //Example of parameterized test
    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1, 2, 4",
            "2, 2, 4",
            "3, 5, 0"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a + b);
    }
}
