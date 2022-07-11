package com.govtech.assignment.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsersResponseDto {
    List<UserDto> results;

    class UserDto{
        private String name;
        private String salary;
    }
}
