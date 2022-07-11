package com.govtech.assignment.service;

import com.govtech.assignment.dto.UsersResponseDto;

public interface UserService {
    /**
     * Fetch list of users
     * @return list of users
     */
    public UsersResponseDto getUsers();

    public void init();
}
