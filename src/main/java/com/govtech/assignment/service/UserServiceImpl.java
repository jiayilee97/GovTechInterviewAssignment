package com.govtech.assignment.service;

import com.govtech.assignment.dto.UserDto;
import com.govtech.assignment.dto.UsersRequestDto;
import com.govtech.assignment.dto.UsersResponseDto;
import com.govtech.assignment.entity.CsvToUser;
import com.govtech.assignment.entity.User;
import com.govtech.assignment.repository.UserRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UsersResponseDto getUsers(UsersRequestDto usersRequestDto) {
        List<User> theUsers = new ArrayList<>();
        Pageable pageable = null;

        // validate sort and limit
        boolean sortIsNull = null == usersRequestDto.getSort() || usersRequestDto.getSort().isEmpty();
        boolean limitIsNull = null == usersRequestDto.getLimit();

        if (!sortIsNull && !limitIsNull) {
            pageable = PageRequest.of(usersRequestDto.getOffset(), usersRequestDto.getLimit(),
                Sort.by(usersRequestDto.getSort().toLowerCase()).ascending());
        } else if (sortIsNull && !limitIsNull){
            pageable = PageRequest.of(usersRequestDto.getOffset(), usersRequestDto.getLimit());
        } else if (!sortIsNull && limitIsNull) {
            // special edge case
            theUsers = userRepository.getAllUsersSortBy(usersRequestDto.getMin(), usersRequestDto.getMax(),
                Sort.by(usersRequestDto.getSort().toLowerCase()).ascending());

        } else {
            pageable = Pageable.unpaged();
        }

        // paginated results
        if (null != pageable) {
            theUsers = userRepository.getTheUsers(usersRequestDto.getMin(),
                usersRequestDto.getMax(), pageable);
        }
        UsersResponseDto responseDto = new UsersResponseDto();
        for (User user : theUsers) {
            UserDto userDto = new UserDto();
            userDto.setName(user.getName());
            userDto.setSalary(user.getSalary().toPlainString());
            responseDto.getResults().add(userDto);
        }
        return responseDto;

    }

    @Override
    public void upload(MultipartFile multipart) throws IOException {
        BufferedReader br;
        try {
            InputStream is = multipart.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));

            CsvToBean<CsvToUser> cb = new CsvToBeanBuilder<CsvToUser>(br)
                .withType(CsvToUser.class)
                .withSkipLines(0)
                .withExceptionHandler(e -> {
                    // parsing errors, e.g. salary contains non-digits
                    throw e;
                })
                .build();

            List<CsvToUser> csvToUsersList = cb.parse();

            for (CsvToUser csvToUser : csvToUsersList) {
                User user = new User(csvToUser);

                // salary must not be negative
                if(csvToUser.getSalary().compareTo(BigDecimal.ZERO) < 0){
                    continue;
                }

                // replace record instead of insert, if name already exists
                User existingUser = userRepository.findByName(csvToUser.getName());
                if (null != existingUser) {
                    user.setId(existingUser.getId());
                }

                userRepository.save(user);
            }

        } catch (IOException | NumberFormatException e) {
            throw e;
        }
    }
}
