package com.squadfind.backend.service;

import com.squadfind.backend.dto.CreateUserGameRequest;
import com.squadfind.backend.dto.UserGameUpdateRequest;
import com.squadfind.backend.enums.PlayStyle;
import com.squadfind.backend.enums.Role;
import com.squadfind.backend.enums.SkillLevel;
import com.squadfind.backend.model.Game;
import com.squadfind.backend.model.User;
import com.squadfind.backend.model.UserGame;
import com.squadfind.backend.repo.GameRepo;
import com.squadfind.backend.repo.UserGameRepo;
import com.squadfind.backend.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional // Needed since multiple database calls are made
@Service
public class UserGameService {
    private final UserGameRepo userGameRepo;
    private final UserRepo userRepo;
    private final GameRepo gameRepo;

    // Establish UserGameRepo, UserRepo, GameRepo dependencies
    public UserGameService(UserGameRepo userGameRepo, UserRepo userRepo, GameRepo gameRepo) {
        this.userGameRepo = userGameRepo;
        this.userRepo = userRepo;
        this.gameRepo = gameRepo;
    }

    public UserGame createUserGame(Long userId, CreateUserGameRequest request){
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        Game game = gameRepo.findById(request.getGameId()).orElseThrow(()-> new RuntimeException("Game not found"));
        // Validate
        if (userGameRepo.existsByUserIdAndGameId(userId, request.getGameId())) {
            throw new RuntimeException("Game already exists in users profile");
        }
        // Set UserGame variables from request
        Role role = request.getRole();
        SkillLevel skillLevel = request.getSkillLevel();
        PlayStyle playStyle = request.getPlayStyle();

        UserGame userGame = new UserGame(role, skillLevel, playStyle, user, game);
        // Return and save UserGame
        return userGameRepo.save(userGame);
    }

    public void deleteUserGame(Long userGameId){
        // Validate
        if (!userGameRepo.existsById(userGameId)) {
            throw new RuntimeException("Game not found in users profile");
        }
        userGameRepo.deleteById(userGameId);
    }

    public UserGame updateUserGame(Long userGameId, UserGameUpdateRequest request){
        UserGame userGame =  userGameRepo.findById(userGameId).orElseThrow(() -> new RuntimeException("Game not found in users profile"));
        // Perform validation checks and then set values
        if (request.getRole() != null){
            userGame.setRole(request.getRole());
        }

        if (request.getSkillLevel() != null){
            userGame.setSkillLevel(request.getSkillLevel());
        }

        if (request.getPlayStyle() != null){
            userGame.setPlayStyle(request.getPlayStyle());
        }
        return userGameRepo.save(userGame);
    }

    public UserGame getUserGame(Long userGameId){
        return userGameRepo.findById(userGameId).orElseThrow(() -> new RuntimeException("Game not found in users profile"));
    }

    public List<UserGame> getUserGamesByUserId(Long userId) {
        return userGameRepo.findByUserId(userId);
    }

}
