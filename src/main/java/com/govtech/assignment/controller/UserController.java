package com.govtech.assignment.controller;

import com.govtech.assignment.dto.ErrorResponseDto;
import com.govtech.assignment.dto.UsersRequestDto;
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
    public ResponseEntity<?> upload(@RequestPart MultipartFile file) {
        String message = "";
        try {
            userService.upload(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            ErrorResponseDto errorResponseDto = new ErrorResponseDto(message);
            return new ResponseEntity<ErrorResponseDto>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    public Object getUsers(@RequestBody UsersRequestDto usersRequestDto) {
        try {
            return userService.getUsers(usersRequestDto);
        } catch (Exception e) {
            String message = "Could not process request!";
            ErrorResponseDto errorResponseDto = new ErrorResponseDto(message);
            return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
