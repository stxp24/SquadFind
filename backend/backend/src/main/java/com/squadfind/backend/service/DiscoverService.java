package com.squadfind.backend.service;

import com.squadfind.backend.dto.FindAllUsersWithCriteriaRequest;
import com.squadfind.backend.dto.BasedOnLoggedInUserRequest;
import com.squadfind.backend.enums.Platform;
import com.squadfind.backend.model.User;
import com.squadfind.backend.model.UserGame;
import com.squadfind.backend.model.UserPlatform;
import com.squadfind.backend.repo.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DiscoverService {
    private final UserGameRepo userGameRepo;
    private final UserPlatformRepo userPlatformRepo;

    public DiscoverService(UserGameRepo userGameRepo, UserPlatformRepo userPlatformRepo) {
        this.userGameRepo = userGameRepo;
        this.userPlatformRepo = userPlatformRepo;
    }

    // Method to find users based on custom criteria
    /* Logic--> For each criteria, create a new empty set of users, as well as a list of userGames or userPlatforms that
    is populated with the users that match the criteria. For each user in the list, add it to the set. Take the set, then
    add it to the final set of users. Set ensures that all users are unique. Finally, remove the user who is viewing the
    results. Return the final set of users. */
    public Set<User> findUsersCustomCriteria(FindAllUsersWithCriteriaRequest request, Long userId){
        Set<User> users = null;

        // Narrow down the results
        if (request.getGameId() != null){
            Set<User> gameUsers = new HashSet<>();
            List<UserGame> userGames = userGameRepo.findByGameId(request.getGameId());
            for (UserGame userGame : userGames){
                gameUsers.add(userGame.getUser());
            }
            if (users == null){
                users = gameUsers;
            } else {
                users.retainAll(gameUsers);
            }
        }

        if (request.getPlatform() != null){
            Set<User> platformUsers = new HashSet<>();
            List<UserPlatform> userPlatforms = userPlatformRepo.findByPlatform(request.getPlatform());
            for (UserPlatform userPlatform : userPlatforms){
                platformUsers.add(userPlatform.getUser());
            }
            if (users == null){
                users = platformUsers;
            } else {
                users.retainAll(platformUsers);
            }
        }

        if (request.getSkillLevel() != null){
            Set<User> skillLevelUsers = new HashSet<>();
            List<UserGame> userGames = userGameRepo.findBySkillLevel(request.getSkillLevel());
            for (UserGame userGame : userGames){
                skillLevelUsers.add(userGame.getUser());
            }
            if (users == null){
                users = skillLevelUsers;
            } else {
                users.retainAll(skillLevelUsers);
            }
        }

        if (request.getRole() != null){
            Set<User> roleUsers = new HashSet<>();
            List<UserGame> userGames = userGameRepo.findByRole(request.getRole());
            for (UserGame userGame : userGames){
                roleUsers.add(userGame.getUser());
            }
            if (users == null){
                users = roleUsers;
            } else {
                users.retainAll(roleUsers);
            }
        }

        if (request.getPlayStyle() != null){
            Set<User> playStyleUsers = new HashSet<>();
            List<UserGame> userGames = userGameRepo.findByPlayStyle(request.getPlayStyle());
            for (UserGame userGame : userGames){
                playStyleUsers.add(userGame.getUser());
            }
            if (users == null){
                users = playStyleUsers;
            } else {
                users.retainAll(playStyleUsers);
            }
        }

        if (users == null) { // If no users match the criteria, return an empty set
            return new HashSet<>();
        }

        users.removeIf(user -> user.getId().equals(userId)); // Remove the user who is viewing the results
        return users;
    }
    /* gets users based on logged in users games and platforms */
    public Set<User> basedOnLoggedInUser(Long userId, BasedOnLoggedInUserRequest request){
        Set<User> users = new HashSet<>();
        Set<User> platformUsers = new HashSet<>();
        Long gameId = request.getGameId();
        Platform platform = request.getPlatform();

        List<UserGame> userGames = userGameRepo.findByGameId(gameId);
        List<UserPlatform> userPlatforms = userPlatformRepo.findByPlatform(platform);

        for (UserGame userGame : userGames){
            users.add(userGame.getUser());
        }
        for (UserPlatform userPlatform : userPlatforms){
            platformUsers.add(userPlatform.getUser());
        }
        users.retainAll(platformUsers); // Retain only users that are on both platforms and games
        users.removeIf(user -> user.getId().equals(userId)); // Exclude logged in user
        return users;
    }
}
