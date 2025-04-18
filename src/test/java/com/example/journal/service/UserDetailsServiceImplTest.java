package com.example.journal.service;

import com.example.journal.entity.User;
import com.example.journal.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;

import static  org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Disabled
    @Test
    public void loadUserByUserNameTest(){
        User mockUser = new User();
        mockUser.setUserName("ram");
        mockUser.setPassword("inrinrick");
        mockUser.setRoles(Arrays.asList("USER"));

        when(userRepository.findByUserName("ram")).thenReturn(mockUser);

        UserDetails user = userDetailsService.loadUserByUsername("ram");

//        assert(userDetails.getUsername().equals("ram"));
//        assert(userDetails.getPassword().equals("inrinrick"));
        Assertions.assertNotNull(user);
    }
}
