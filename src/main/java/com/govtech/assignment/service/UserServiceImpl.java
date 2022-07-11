package com.govtech.assignment.service;

import com.govtech.assignment.dto.UsersResponseDto;
import com.govtech.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UsersResponseDto getUsers() {
        return null;
    }

    @Override
    public void init() {


    }
}
