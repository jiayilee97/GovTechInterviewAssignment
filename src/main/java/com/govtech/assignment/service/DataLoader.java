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

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        userRepository.save(new User(1,"Lionel", BigDecimal.valueOf(1000)));
        userRepository.save(new User(2,"Jack", BigDecimal.valueOf(2000)));
    }
}
