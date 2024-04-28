package com.example.myrest;

import com.example.myrest.model.User;
import com.example.myrest.model.dto.UserDTO;
import com.example.myrest.repo.UserRepository;
import com.example.myrest.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl underTest;

    @Test
    public void createUser_ValidInput_ReturnsUser() {
        // Arrange
        UserDTO userDTO = new UserDTO("test@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Street", "1234567890");
        when(userRepository.save(any(User.class))).thenReturn(new User());

        // Act
        User result = underTest.createUser(userDTO);

        // Assert
        assertEquals(User.class, result.getClass());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void updateUser_UserExists_ReturnsUpdatedUser() {
        // Arrange
        Long userId = 1L;
        UserDTO userDTO = new UserDTO("test@example.com", "John", "Doe", LocalDate.of(1990, 1, 1), "123 Street", "1234567890");
        User existingUser = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        // Act
        User result = underTest.updateUser(userId, userDTO);

        // Assert
        assertEquals(existingUser, result);
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void deleteUser_UserExists_DeletesUser() {
        // Arrange
        Long userId = 1L;

        // Act
        underTest.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void searchUsersByBirthDate_ReturnsListOfUsers() {
        // Arrange
        LocalDate fromDate = LocalDate.of(1990, 1, 1);
        LocalDate toDate = LocalDate.of(1990, 12, 31);
        List<User> userList = new ArrayList<>();
        when(userRepository.findAllByBirthDateBetween(fromDate, toDate)).thenReturn(userList);

        // Act
        List<User> result = underTest.searchUsersByBirthDate(fromDate, toDate);

        // Assert
        assertEquals(userList, result);
        verify(userRepository, times(1)).findAllByBirthDateBetween(fromDate, toDate);
    }

}
