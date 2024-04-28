package com.example.myrest.service;

import com.example.myrest.model.User;
import com.example.myrest.model.dto.UserDTO;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    User createUser(UserDTO userDTO);

    User updateUser(Long userId, UserDTO userDTO);

    void deleteUser(Long userId);

    List<User> searchUsersByBirthDate(LocalDate fromDate, LocalDate toDate);

}
