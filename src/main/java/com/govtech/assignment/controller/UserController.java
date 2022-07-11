package com.govtech.assignment.controller;

import com.govtech.assignment.dto.UsersRequestDto;
import com.govtech.assignment.dto.UsersResponseDto;
import com.govtech.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    public ResponseEntity<Object> upload(@RequestPart MultipartFile file) {
        String message = "";
        try {
            userService.upload(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    public UsersResponseDto getUsers(@RequestBody UsersRequestDto usersRequestDto) {
        return userService.getUsers(usersRequestDto);
    }
}