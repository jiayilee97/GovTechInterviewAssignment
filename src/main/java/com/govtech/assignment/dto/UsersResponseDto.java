package com.govtech.assignment.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UsersResponseDto {
    List<UserDto> results;
    
    public UsersResponseDto() {
        this.results = new ArrayList<>();
    }
}
