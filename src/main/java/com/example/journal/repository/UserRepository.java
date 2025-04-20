package com.example.journal.repository;


import com.example.journal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);

    void deleteByUserName(String userName);

    boolean existsByUserName(String userName);


    @Query(value = "SELECT * FROM users WHERE email REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$' AND sentiment_analysis = true", nativeQuery = true)
    List<User> getUserForSA();

}