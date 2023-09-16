package com.uk.savechildrenuk.controller;

import com.uk.savechildrenuk.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UserControllerIT {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    String BASE_URL = "http://localhost:";

    @Test
    public void shouldGetAllUsers() {
        User user1 = User.builder().firstName("Amit").lastName("Modak")
                .location("scotlans").email("Amit@outlook.com").gender("M")
                .dateOfBirth(LocalDate.now().minusYears(30))
                .phoneNumber("343434").build();

        User user2 = User.builder().firstName("vince").lastName("reynolds")
                .location("Manchester").email("vince@outlook.com").gender("M")
                .dateOfBirth(LocalDate.now().minusYears(36))
                .phoneNumber("567567").build();

        createUser(user1);
        createUser(user2);
        List list = restTemplate.getForObject(BASE_URL + port + "/user/getUsers", List.class);
        assertEquals(list.size(), 2);
    }

    @Test
    public void shouldCreateUserSuccessfully() {
        User user = User.builder().firstName("Alex").lastName("Sierra")
                .location("North London").email("test@email.com").gender("M")
                .phoneNumber("123456789").build();
        user.setDateOfBirth(LocalDate.now().minusYears(30));
        User createdUser = createUser(user);
        assertNotNull(createdUser.getId());
        assertEquals(createdUser.getFirstName(), user.getFirstName());
        assertEquals(createdUser.getLocation(), user.getLocation());
        assertEquals(createdUser.getEmail(), user.getEmail());

    }

    @Test
    public void shouldReturnUserDetailsById() {
        User user = User.builder().firstName("Michael").lastName("Bailey")
                .location("East London").email("test123@email.com").gender("M")
                .dateOfBirth(LocalDate.now().minusYears(42))
                .phoneNumber("4564564").build();
        User createdUser = createUser(user);
        User dbUser = getUserById(createdUser.getId());
        assertEquals(createdUser.getId(), dbUser.getId());
        assertEquals(createdUser.getFirstName(), dbUser.getFirstName());
        deleteUserById(dbUser.getId());
    }


    @Test
    public void shouldDeleteUserById() {
        User user = User.builder().firstName("Barbara").lastName("Joshua")
                .location("West London").email("test234@email.com").gender("F")
                .dateOfBirth(LocalDate.now().minusYears(30))
                .phoneNumber("674598235").build();
        User createdUser = createUser(user);
        User dbUser = getUserById(createdUser.getId());
        deleteUserById(dbUser.getId());
        User databaseUser = getUserById(dbUser.getId());
        assertNull(databaseUser.getId());
    }

    @Test
    public void shouldCreateAndUpdateUserById() {
        User user = User.builder().firstName("Caralyn").lastName("hundai")
                .location("South London").email("test345@email.com").gender("F")
                .dateOfBirth(LocalDate.now().minusYears(37))
                .phoneNumber("54892167").build();

        User userToUpdate = createUser(user);
        userToUpdate.setLocation("America");
        userToUpdate.setPhoneNumber("67540987");
        this.restTemplate.put(BASE_URL + port + "/user/updateuser", userToUpdate);
        User updatedUser = getUserById(userToUpdate.getId());
        assertEquals(updatedUser.getLocation(), userToUpdate.getLocation());
        assertEquals(updatedUser.getPhoneNumber(), userToUpdate.getPhoneNumber());
        deleteUserById(userToUpdate.getId());
    }


    private User createUser(User user) {
        return restTemplate.postForObject(
                BASE_URL + port + "/user/createUser", user,
                User.class);
    }

    private User getUserById(Long userId) {
        return restTemplate.getForObject(BASE_URL + port + "/user/getuserbyid/"
                + userId, User.class);
    }

    private void deleteUserById(Long userId) {
        restTemplate.delete(BASE_URL + port + "/user/deleteuser/" + userId);
    }

}
