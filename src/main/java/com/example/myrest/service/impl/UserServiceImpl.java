package com.example.myrest.service.impl;

import com.example.myrest.model.User;
import com.example.myrest.model.dto.UserDTO;
import com.example.myrest.repo.UserRepository;
import com.example.myrest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(UserDTO userDTO) {
        return userRepository.save(
                User.builder()
                        .email(userDTO.getEmail())
                        .firstName(userDTO.getFirstName())
                        .lastName(userDTO.getLastName())
                        .birthDate(userDTO.getBirthDate())
                        .address(userDTO.getAddress())
                        .phoneNumber(userDTO.getPhoneNumber()).build());
    }

    @Override
    public User updateUser(Long userId, UserDTO userDTO) {

        User currentUser = userRepository.findById(userId).orElseThrow();

        if (userDTO.getEmail() != null) {
            currentUser.setEmail(userDTO.getEmail());
        }
        if (userDTO.getFirstName() != null) {
            currentUser.setFirstName(userDTO.getFirstName());
        }
        if (userDTO.getLastName() != null) {
            currentUser.setLastName(userDTO.getLastName());
        }
        if (userDTO.getBirthDate() != null) {
            currentUser.setBirthDate(userDTO.getBirthDate());
        }
        if (userDTO.getAddress() != null) {
            currentUser.setAddress(userDTO.getAddress());
        }
        if (userDTO.getPhoneNumber() != null) {
            currentUser.setPhoneNumber(userDTO.getPhoneNumber());
        }
        return userRepository.save(currentUser);
    }

    @Override
    public void deleteUser(Long userId) {

        userRepository.deleteById(userId);
    }

    @Override
    public List<User> searchUsersByBirthDate(LocalDate fromDate, LocalDate toDate) {

        return userRepository.findAllByBirthDateBetween(fromDate, toDate);
    }
}
