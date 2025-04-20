package com.example.journal.controller;

import com.example.journal.entity.User;
import com.example.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;


    @GetMapping("/health_check")
    public String health(){
        return "EveryThing is Ok.";
    }

    //To add the data into the database
    @PostMapping
    public boolean createUser(@RequestBody User user){

        return userService.saveNewEntry(user);
    }

}