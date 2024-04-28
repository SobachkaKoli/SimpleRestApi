package com.example.myrest.service.impl;

import com.example.myrest.model.User;
import com.example.myrest.model.dto.UserDTO;
import com.example.myrest.repo.UserRepository;
import com.example.myrest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(UserDTO userDTO) {
        validateUser(userDTO);

        User user = User.builder()
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .birthDate(userDTO.getBirthDate())
                .address(userDTO.getAddress())
                .phoneNumber(userDTO.getPhoneNumber())
                .build();

        return userRepository.save(user);
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

    private void validateUser(UserDTO userDTO) throws ResponseStatusException {
        if (userDTO.getEmail() == null || !isValidEmail(userDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required and must be a valid email address.");
        }

        if (userDTO.getFirstName() == null || userDTO.getFirstName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name is required.");
        }

        if (userDTO.getLastName() == null || userDTO.getLastName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Last name is required.");
        }

        if (userDTO.getBirthDate() == null || userDTO.getBirthDate().isAfter(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Birth date is required and must be earlier than today.");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w!#$%&'*+/=?^`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";
        return email != null && email.matches(emailRegex);
    }
}
