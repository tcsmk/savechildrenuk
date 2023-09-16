package com.uk.savechildrenuk.service;

import com.uk.savechildrenuk.exception.UserNotFoundException;
import com.uk.savechildrenuk.model.User;
import com.uk.savechildrenuk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class acts as a service layer for the user management.
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User Not Found");
        }
        return user.get();
    }

    @Override
    public void deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("User Not Found");
        }
    }

    @Override
    public User updateUserById(User user) {
        Optional<User> dbUser = userRepository.findById(user.getId());
        User userToUpdate;
        if (dbUser.isPresent()) {
            userToUpdate = dbUser.get();
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setGender(user.getGender());
            userToUpdate.setLocation(user.getLocation());
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setDateOfBirth(user.getDateOfBirth());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setPhoneNumber(user.getPhoneNumber());
            return userRepository.save(userToUpdate);
        }
        throw new UserNotFoundException("User Id Not Found");
    }


}
