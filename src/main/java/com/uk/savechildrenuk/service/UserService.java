package com.uk.savechildrenuk.service;

import com.uk.savechildrenuk.model.User;

import java.util.List;

public interface UserService {
 User createUser(User user);
 List<User> getUsers();
 User getUserById(Long id);
 void deleteUserById(Long id);
 User updateUserById(User user);

}
