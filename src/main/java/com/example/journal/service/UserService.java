package com.example.journal.service;


import com.example.journal.entity.User;
import com.example.journal.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;


@Service
@Slf4j //At runtime it inject the Logger instance and we use here "log" method
public class UserService {

    @Autowired
    private UserRepository userRepository;


    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //For custom error we create the instance of the Logger of that particular class and use "logger" method like below.
//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    //Used to return all the data into the database
    public List<User> getAll(){
        return userRepository.findAll();
    }



    //Used to Add the data into the database
    public boolean saveNewEntry(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        }
        catch (Exception e){
            log.error("Error occurs for {}", user.getUserName());
            log.info("haahahahahahaha");
            log.warn("hahahahahahah");
            log.debug("hhhhhaaaaaaaa");
            log.trace("hahahahahahahahhhh");
            return false;
        }
    }

    //Used for different case
    public void saveUser(User user){
        userRepository.save(user);
    }


    //used to find the data based on the id
    public User findByUserName(String username){
        return userRepository.findByUserName(username);
    }



    //used to delete the data based on the user name
    @Transactional
    public boolean deleteByUserName(String userName){
        if (!userRepository.existsByUserName(userName)) {
            throw new RuntimeException("User not found with username: " + userName);
        }

        try {
            userRepository.deleteByUserName(userName);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting the user", e);
        }
    }


    //To Save the User whose role is {user and also admin}
    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(user);
    }

}