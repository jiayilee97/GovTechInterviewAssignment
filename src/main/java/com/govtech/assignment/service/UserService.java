package com.govtech.assignment.service;

import com.govtech.assignment.dto.UsersRequestDto;
import com.govtech.assignment.dto.UsersResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    public UsersResponseDto getUsers(UsersRequestDto usersRequestDto);

    public void upload(MultipartFile file) throws IOException;
}
