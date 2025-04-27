package com.example.journal.controller;

import com.example.journal.cache.AppCache;
import com.example.journal.entity.User;
import com.example.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AppCache appCache;

    @Autowired
    private UserService userService;


    @GetMapping("/all-users")
    public ResponseEntity<?> getAll(){

        List<User> all = userService.getAll();

        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




    //For personal purpose
    @GetMapping("/clear-app-cache")
    public void clearAppCache(){
        appCache.init();
    }
}
