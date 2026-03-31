package com.squadfind.backend.service;

import com.squadfind.backend.dto.LoginUserRequest;
import com.squadfind.backend.dto.RegisterUserRequest;
import com.squadfind.backend.dto.UserUpdateRequest;
import com.squadfind.backend.model.User;
import com.squadfind.backend.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {
    private final UserRepo userRepo;

    // Establish userRepo dependency
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public User registerUser(RegisterUserRequest request){
        // Check if username and email exists first
        if (userRepo.existsBySquadFindUsername(request.getSquadFindUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        //TODO: Hash password

        // Create User object from DTO fields
        String squadFindUsername = request.getSquadFindUsername();
        String password = request.getPassword();
        String email = request.getEmail();
        LocalDate birthday = request.getBirthday();
        String bio = "";
        return userRepo.save(new User(squadFindUsername, birthday, email, password, bio));

    }

    /*
    Updating a users information is packaged as one method. Takes a UserUpdateRequest object (DTO) and updates the user's
    information based on the request. The user updates their info in the frontend through a form, which React will then
    package the form as JSON, and send it to the API and is converted into a UserUpdateRequest object. This object is
    captured in this methods parameter, verifies the userId exists, then overwrites the user's information with the new
    information from the request.
    */
    public User updateUser(Long userId, UserUpdateRequest request){
        //TODO: add validation logic
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getSquadFindUsername() != null ) {
            String userCheck = request.getSquadFindUsername();
            if (userRepo.existsBySquadFindUsername(userCheck)) {
                throw new RuntimeException("Username already exists");
            }
            user.setSquadFindUsername(request.getSquadFindUsername());
        }

        if (request.getEmail() != null) {
            String emailCheck = request.getEmail();
            if (userRepo.existsByEmail(emailCheck)) {
                throw new RuntimeException("Email already exists");
            }
            user.setEmail(request.getEmail());
        }

        if (request.getBio() != null) {
            user.setBio(request.getBio());
        }

        return userRepo.save(user);
    }

    public User changePassword(Long userId, String oldPassword, String newPassword){
        //TODO: Implement
    }

    public void deleteUser(Long userId){
        if (!userRepo.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        userRepo.deleteById(userId);
    }

    public User login(LoginUserRequest request){
        User user = userRepo.findBySquadFindUsername(request.getSquadFindUsername())
                            .orElseThrow(() -> new RuntimeException("Username not found"));

        if (!request.getPassword().equals(user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    public User getUserById(Long userId){
        return userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
