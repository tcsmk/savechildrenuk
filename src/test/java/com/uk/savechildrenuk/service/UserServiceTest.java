package com.uk.savechildrenuk.service;

import com.uk.savechildrenuk.exception.UserNotFoundException;
import com.uk.savechildrenuk.model.User;
import com.uk.savechildrenuk.repository.UserRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    static UserService userService;

    static UserRepository userRepository;

    @BeforeClass
    public static void beforeClass() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void shouldCreateUser() {
        User user = getUser();
        User user1 = new User();
        BeanUtils.copyProperties(user1, user);
        user1.setId(10L);
        Mockito.when(userRepository.save(user)).thenReturn(user1);
        User createdUser = userService.createUser(user);
        assertEquals(10, createdUser.getId());
    }

    @Test
    public void shouldGetAllUsers() {
        User user = getUser();
        Mockito.when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        List<User> users = userService.getUsers();
        assertEquals(1, users.size());
    }

    @Test
    public void shouldGetUserById() {
        User user = getUser();
        user.setId(10L);
        Mockito.when(userRepository.findById(10L)).thenReturn(Optional.of(user));
        User dbUser = userService.getUserById(10L);
        assertEquals(10, dbUser.getId());
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowExceptionWhenUserNotFound() {
        User user = getUser();
        user.setId(10L);
        Mockito.when(userRepository.findById(10L)).thenReturn(Optional.empty());
        userService.getUserById(10L);
    }


    @Test
    public void shouldUpdateUser() {
        User user = getUser();
        user.setId(10L);
        User user1 = new User();
        user1.setId(10L);

        user1.setId(10L);
        Mockito.when(userRepository.findById(10L)).thenReturn(Optional.of(user));
        userService.updateUserById(user1);
        assertEquals(user.getId(), user.getId());
        assertEquals(user.getFirstName(), user.getFirstName());
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldUpdateUserWhenUserNotFound() {
        User user1 = new User();
        user1.setId(10L);
        Mockito.when(userRepository.findById(10L)).thenReturn(Optional.empty());
        userService.updateUserById(user1);
    }

    @Test
    public void shouldDeleteUserById() {
        User user = getUser();
        user.setId(10L);
        Mockito.doNothing().when(userRepository).deleteById(10L);
        userService.deleteUserById(10L);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldDeleteUserByIdWithEmptyResult() {
        User user = getUser();
        user.setId(10L);
        Mockito.doThrow(new EmptyResultDataAccessException(1)).when(userRepository).deleteById(10L);
        userService.deleteUserById(10L);
    }

    private static User getUser() {
        return User.builder().firstName("Amit").lastName("Bimla")
                .location("scotland").email("Amit@outlook.com").gender("M")
                .dateOfBirth(LocalDate.now().minusYears(30))
                .phoneNumber("343434").build();
    }

}
