package com.squadfind.backend.service;

import com.squadfind.backend.dto.CreateUserPlatformRequest;
import com.squadfind.backend.dto.UpdateUserPlatformRequest;
import com.squadfind.backend.enums.Platform;
import com.squadfind.backend.model.User;
import com.squadfind.backend.model.UserPlatform;
import com.squadfind.backend.repo.UserPlatformRepo;
import com.squadfind.backend.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional // Needed since multiple database calls are made
@Service
public class UserPlatformService {
    // Establish UserPlatformRepo, UserRepo dependencies
    private final UserPlatformRepo userPlatformRepo;
    private final UserRepo userRepo;

    public UserPlatformService(UserPlatformRepo userPlatformRepo, UserRepo userRepo) {
        this.userPlatformRepo = userPlatformRepo;
        this.userRepo = userRepo;
    }

    public UserPlatform createUserPlatform(Long userId, CreateUserPlatformRequest request){
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        // Validate
        if (userPlatformRepo.existsByUserIdAndPlatform(userId, request.getPlatform())){
            throw new RuntimeException("Platform already exists in users profile");
        }
        // Inject information into UserPlatform constructor
        Platform platform = request.getPlatform();
        String gamertag = request.getGamertag();
        // Return and save
        return userPlatformRepo.save(new UserPlatform(platform, gamertag, user));
    }

    public UserPlatform updateUserPlatform(Long userPlatformId, UpdateUserPlatformRequest request){
        UserPlatform userPlatform = userPlatformRepo.findById(userPlatformId).orElseThrow(() -> new RuntimeException("User not found"));
        // Perform validation checks and then set values
        if (request.getPlatform() != null){
            userPlatform.setPlatform(request.getPlatform());
        }

        if (request.getGamertag() != null){
            userPlatform.setGamertag(request.getGamertag());
        }
        return userPlatformRepo.save(userPlatform);

    }

    public void deleteUserPlatform(Long userPlatformId){
        UserPlatform userPlatform = userPlatformRepo.findById(userPlatformId).orElseThrow(() -> new RuntimeException("User not found"));
        userPlatformRepo.delete(userPlatform);
    }

    public UserPlatform getUserPlatform(Long userPlatformId){
        return userPlatformRepo.findById(userPlatformId).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
