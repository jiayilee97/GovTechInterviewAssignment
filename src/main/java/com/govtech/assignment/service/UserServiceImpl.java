package com.govtech.assignment.service;

import com.govtech.assignment.dto.UsersResponseDto;
import com.govtech.assignment.entity.CsvToUser;
import com.govtech.assignment.entity.User;
import com.govtech.assignment.repository.UserRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

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

    @Override
    public void upload(MultipartFile multipart) throws IOException {
        BufferedReader br;
        try {
            InputStream is = multipart.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));

            CsvToBean<CsvToUser> cb = new CsvToBeanBuilder<CsvToUser>(br)
                .withType(CsvToUser.class)
                .withSkipLines(1)
                .build();
            List<CsvToUser> csvToUsersList = cb.parse();

            for (CsvToUser csvToUser : csvToUsersList) {
                userRepository.save(new User(csvToUser));
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }
}
