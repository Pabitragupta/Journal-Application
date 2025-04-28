package com.example.journal.controller;

import com.example.journal.api.response.WeatherResponse;
import com.example.journal.entity.User;
import com.example.journal.service.UserService;
import com.example.journal.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private WeatherService weatherService;



    //Used to update the user data
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User newUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        User oldUser = userService.findByUserName(name);

        oldUser.setUserName(newUser.getUserName());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setSentimentAnalysis(newUser.isSentimentAnalysis());

        userService.saveNewEntry(oldUser);  // This re-encodes password and saves

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





    // ✅ To delete the user
    @DeleteMapping
    public ResponseEntity<String> deleteUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        userService.deleteByUserName(username);

        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }


    //To get the weather data using weather api
    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        WeatherResponse weatherReasponse = weatherService.getWeather("Mumbai");

        String greeting = "";
        if(weatherReasponse != null){
            greeting = ", Weather feels like " + weatherReasponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi " + userName + greeting, HttpStatus.OK);
    }
}