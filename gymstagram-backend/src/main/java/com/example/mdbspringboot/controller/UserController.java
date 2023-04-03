/**

This class represents the controller for User entity
*/
package com.example.mdbspringboot.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.mdbspringboot.service.UserService;
import com.example.mdbspringboot.model.User;

import com.example.mdbspringboot.model.LoginBody;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
	private PasswordEncoder bcryptEncoder;
    
    @Autowired
    private UserService userService;

    /**
     * This method returns a list of all users
     * 
     * @return List<User> list of all users
     */
    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * This method returns a user by id
     * 
     * @param id String id of the user
     * @return User user with the given id
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    /**
     * This method creates a new user
     * 
     * @param user User object to be created
     * @return User created user object
     */
    @PostMapping("")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginBody loginBody) {
        User user = userService.getUserByUsername(loginBody.getUsername());
        if (user == null) {
            System.out.println("User not found.");
            return null;  
        }
        
        // Check if PW matches
        if (bcryptEncoder.matches(loginBody.getPassword(), user.getPassword())) {
            return user;
        }

        return null;
    }

    @PutMapping("/{followerId}/follow/{receiverId}")
    public User followUser(@PathVariable String followerId, @PathVariable String receiverId) {
        return userService.followUser(followerId, receiverId);
    }

    @PutMapping("/{followerId}/unfollow/{receiverId}")
    public User unfollowUser(@PathVariable String followerId, @PathVariable String receiverId) {
        return userService.unfollowUser(followerId, receiverId);
    }

    /**
     * This method updates an existing user by id
     * 
     * @param id String id of the user to be updated
     * @param user User object containing updated information
     * @return User updated user object
     */
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    /**
     * This method deletes a user by id
     * 
     * @param id String id of the user to be deleted
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}