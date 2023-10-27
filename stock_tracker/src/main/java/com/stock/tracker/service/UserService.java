package com.stock.tracker.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.stock.tracker.dto.UserDTO;
import com.stock.tracker.enums.UserRole;
import com.stock.tracker.model.User;
import com.stock.tracker.repository.UserRepository;
import com.stock.tracker.util.MapperUtil;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    public UserService(UserRepository userRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return A list of UserDTO containing information about all users.
     */
    public List<UserDTO> getAllUsers() {
        // Retrieve all users from the database.
        List<User> users = userRepository.findAll();

        // Map the list of User entities to a list of UserDTOs using a mapper.
        List<UserDTO> userDTOs = users.stream()
                .map(user -> mapperUtil.convertToDTO(user, UserDTO.class))
                .collect(Collectors.toList());

        return userDTOs;
    }

    /**
     * Retrieves a user by their unique ID from the database.
     *
     * @param id The unique ID of the user to retrieve.
     * @return A UserDTO containing information about the retrieved user, or null if
     *         not found.
     */
    public UserDTO getUserById(UUID id) {
        // Retrieve a user by their unique ID from the database.
        User user = userRepository.findById(id).orElse(null);

        // Map the User entity to a UserDTO using a mapper.
        UserDTO userDTO = mapperUtil.convertToDTO(user, UserDTO.class);

        return userDTO;
    }

    /**
     * Creates a new user with the provided information and saves it in the
     * database.
     *
     * @param username          The username of the user.
     * @param plainTextPassword The plain text password of the user.
     * @param role              The role of the user.
     * @return A UserDTO containing information about the created user.
     */
    public UserDTO createUser(String username, String plainTextPassword, UserRole role) {
        // Create a new User entity with the provided parameters.
        User user = new User(username, plainTextPassword, role);

        // Save the newly created user in the database.
        User createdUser = userRepository.save(user);

        // Map the created User entity to a UserDTO using a mapper.
        UserDTO userDTO = mapperUtil.convertToDTO(createdUser, UserDTO.class);

        return userDTO;
    }

    /**
     * Updates an existing user with the provided information.
     *
     * @param id                The unique ID of the user to update.
     * @param username          The new username for the user (or null to keep the
     *                          current username).
     * @param plainTextPassword The new plain text password for the user (or null to
     *                          keep the current password).
     * @param role              The new role for the user (or null to keep the
     *                          current role).
     * @return A UserDTO containing information about the updated user, or null if
     *         the user is not found.
     */
    public UserDTO updateUser(UUID id, String username, String plainTextPassword, UserRole role) {
        // Update an existing user in the database.
        // If any of the update parameters are null, the corresponding fields are not
        // updated.

        // Retrieve the user by their unique ID.
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            // Update user fields if valid parameters are provided.
            if (username != null) {
                user.setUsername(username);
            }

            if (plainTextPassword != null) {
                user.setPassword(plainTextPassword);
            }

            if (role != null) {
                user.setRole(role);
            }

            // Save the updated user in the database.
            userRepository.save(user);

            // Map the updated User entity to a UserDTO using a mapper.
            UserDTO userDTO = mapperUtil.convertToDTO(user, UserDTO.class);

            return userDTO;
        }

        return null;
    }

    /**
     * Deletes a user by their unique ID from the database.
     *
     * @param id The unique ID of the user to delete.
     * @return true if the user is successfully deleted, false if the user is not
     *         found.
     */
    public boolean deleteUser(UUID id) {
        // Delete a user by their unique ID.
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
