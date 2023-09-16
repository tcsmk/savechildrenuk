package com.uk.savechildrenuk.controller;

import com.uk.savechildrenuk.model.User;
import com.uk.savechildrenuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     *  This method is used to create a new user.
     * @param user
     * @return user
     */

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    /**
     *  This method is used to get the user details for the passed user Id as input parameter
     * @param id
     * @return user
     */
    @GetMapping("/getuserbyid/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * This method is used to get all the users.
     * @return List of user details
     */
    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    /**
     * This method is used to delete user by passing id as input parameter.
     * @param id
     */
    @DeleteMapping("/deleteuser/{id}")
    public void deleteUserById(@PathVariable Long id){
         userService.deleteUserById(id);

    }

    /**
     * This method is used to update the user
     * @param user
     * @return user
     */
    @PutMapping("/updateuser")
    public User updateUserById(@RequestBody User user){
        return userService.updateUserById(user);
    }

}
