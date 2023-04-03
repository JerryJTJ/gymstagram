package com.example.mdbspringboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.mdbspringboot.model.User;
import com.example.mdbspringboot.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
	private PasswordEncoder bcryptEncoder;

    /**
     * Returns a list of all users in the database.
     * @return List of users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Returns the user with the specified ID, if it exists.
     * @param id The ID of the user to retrieve
     * @return The user with the specified ID, or null if it does not exist
     */
    public User getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }

    /**
     * Creates a new user in the database.
     * @param user The user to create
     * @return The created user
     * @throws IllegalArgumentException If the user is null
     */
    public User createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Updates the user with the specified ID in the database.
     * @param id The ID of the user to update
     * @param user The updated user data
     * @return The updated user, or null if the user does not exist
     */
    public User updateUser(String id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(bcryptEncoder.encode(user.getPassword()));
            updatedUser.setEmail(user.getEmail());
            updatedUser.setFollowing(user.getFollowing());
            updatedUser.setCurrentWeight(user.getCurrentWeight());
            updatedUser.setTargetWeight(user.getTargetWeight());
            return userRepository.save(updatedUser);
        }
        return null;
    }

    /**
     * Deletes the user with the specified ID from the database.
     * @param id The ID of the user to delete
     * @throws IllegalArgumentException If the ID is null
     * @throws RuntimeException If the user does not exist or could not be deleted
     */
    public void deleteUser(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user", e);
        }
    }

    // Follow and unfollow
    public User followUser(String followerUserId, String receiverUserId) {
        Optional<User> followerUser = userRepository.findById(followerUserId);
        Optional<User> receiverUser = userRepository.findById(receiverUserId);
        if (followerUser.isPresent() && receiverUser.isPresent()) {
            User updatedFollowerUser = followerUser.get();
            List<String> following = updatedFollowerUser.getFollowing();

            if (following.contains(receiverUserId)) {
                System.out.println("Already following");
                return null;
            }

            following.add(receiverUserId);
            updatedFollowerUser.setFollowing(following);
            userRepository.save(updatedFollowerUser);
            return updatedFollowerUser;
        }
        return null;
    }

    public User unfollowUser(String followerUserId, String receiverUserId) {
        Optional<User> followerUser = userRepository.findById(followerUserId);
        if (followerUser.isPresent()) {
            User updatedFollowerUser = followerUser.get();
            List<String> following = updatedFollowerUser.getFollowing();
            for (int i = 0; i < following.size(); i++) {
                if (following.get(i).equals(receiverUserId)) {
                    following.remove(i);
                    updatedFollowerUser.setFollowing(following);
                    userRepository.save(updatedFollowerUser);
                    return updatedFollowerUser;
                }
            }
        }
        return null;
    }
}