package com.example.myrest.controller;

import com.example.myrest.model.User;
import com.example.myrest.model.dto.UserDTO;
import com.example.myrest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @Value("${min.registration.age}")
    private int minAge;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        Period age = Period.between(userDTO.getBirthDate(), LocalDate.now());
        if (age.getYears() >= minAge) {
            return ResponseEntity.ok(userService.createUser(userDTO));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You must be 18 or older");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUserDTO) {
        User updatedUser = userService.updateUser(id, updatedUserDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsersByBirthDate(@RequestParam("from") LocalDate fromDate, @RequestParam("to") LocalDate toDate) {
        if (fromDate.isAfter(toDate)) {
            throw new IllegalArgumentException("From date must be less than or equal to To date.");
        }

        List<User> users = userService.searchUsersByBirthDate(fromDate, toDate);
        return ResponseEntity.ok(users);
    }


}
