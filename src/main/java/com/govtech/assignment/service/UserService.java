package com.govtech.assignment.service;

import com.govtech.assignment.dto.UsersResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    /**
     * Fetch list of users
     * @return list of users
     */
    public UsersResponseDto getUsers();

    public void init();

    public void upload(MultipartFile file) throws IOException;
}
