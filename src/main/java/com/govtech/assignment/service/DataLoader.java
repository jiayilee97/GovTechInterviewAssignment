package com.govtech.assignment.service;

import com.govtech.assignment.entity.User;
import com.govtech.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    public void run(ApplicationArguments args) {
        // pre-load database with seed data
        String lionel = "Lionel";
        String jack = "Jack";

        // user 1
        User newUser = new User(1,lionel, BigDecimal.valueOf(1000));
        if (null == userRepository.findByName(newUser.getName())) {
            userRepository.save(newUser);;
        }

        // user 2
        newUser = new User(2,jack, BigDecimal.valueOf(2000));
        if (null == userRepository.findByName(newUser.getName())) {
            userRepository.save(newUser);;
        }
    }
}
