package com.govtech.assignment.repository;

import com.govtech.assignment.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    public UsersResponseDto getTheUsers(BigDecimal min, BigDecimal max, int limit, int offset, String sort);

    @Query("SELECT u FROM User u WHERE " +
        "u.salary <= ?2 AND " +
        "u.salary >= ?1 ")
    public List<User> getTheUsers(BigDecimal min, BigDecimal max, Pageable pageable);

    @Query("SELECT u FROM User u WHERE " +
        "u.salary <= ?2 AND " +
        "u.salary >= ?1 ")
    public List<User> getAllUsersSortBy(BigDecimal min, BigDecimal max, Sort sort);

    public User findByName(String name);
}
