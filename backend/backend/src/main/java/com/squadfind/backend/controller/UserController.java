package com.squadfind.backend.controller;


import com.squadfind.backend.dto.CreateUserGameRequest;
import com.squadfind.backend.dto.CreateUserPlatformRequest;
import com.squadfind.backend.dto.UserGameUpdateRequest;
import com.squadfind.backend.dto.UserUpdateRequest;
import com.squadfind.backend.model.User;
import com.squadfind.backend.model.UserGame;
import com.squadfind.backend.model.UserPlatform;
import com.squadfind.backend.service.UserGameService;
import com.squadfind.backend.service.UserPlatformService;
import com.squadfind.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserPlatformService userPlatformService;
    private final UserGameService userGameService;

    public UserController(UserService userService, UserPlatformService userPlatformService, UserGameService userGameService) {
        this.userService = userService;
        this.userPlatformService = userPlatformService;
        this.userGameService = userGameService;
    }

    /*----- USER PROFILE ENDPOINTS -----*/

    // Endpoint for getting a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Endpoint for updating a user's profile
    // @PathVariable is used to extract the value of the URL parameter, @RequestBody is used to extract the JSON request body
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserProfile(@PathVariable Long id, @RequestBody UserUpdateRequest request){
        User user = userService.updateUser(id, request);
        return ResponseEntity.ok(user); // 200 OK
    }

    //Endpoint for deleting a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    /*----- USER PLATFORM ENDPOINTS -----*/

    // Endpoint for creating a new user platform
    @PostMapping("/{id}/platforms")
    public ResponseEntity<UserPlatform> createUserPlatform(@PathVariable Long id, @RequestBody CreateUserPlatformRequest request){
       UserPlatform userPlatform = userPlatformService.createUserPlatform(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userPlatform); // 201 Created
    }

    // Endpoint for deleting a user platform
    @DeleteMapping("/{id}/platforms/{platformId}")
    public ResponseEntity<Void> deleteUserPlatform(@PathVariable("id") Long userId, @PathVariable("platformId") Long platformId) {
        userPlatformService.deleteUserPlatform(platformId);
        return ResponseEntity.noContent().build();
    }

    // Endpoint for getting a user platform
    @GetMapping("/{id}/platforms/{platformId}")
    public ResponseEntity<UserPlatform> getUserPlatform(@PathVariable("id") Long userId, @PathVariable("platformId") Long platformId){
        UserPlatform userPlatform = userPlatformService.getUserPlatform(platformId);
        return ResponseEntity.ok(userPlatform);
    }

    // Endpoint for getting all user platforms
    @GetMapping("/{id}/platforms")
    public ResponseEntity<List<UserPlatform>> getUserPlatforms(@PathVariable Long id){
        List<UserPlatform> userPlatforms = userPlatformService.getUserPlatforms(id);
        return ResponseEntity.ok(userPlatforms);
    }

    /*----- USER GAME ENDPOINTS -----*/

    // Endpoint for creating a new user game
    @PostMapping("/{id}/games")
    public ResponseEntity<UserGame> createUserGame(@PathVariable Long id, @RequestBody CreateUserGameRequest request){
        UserGame userGame = userGameService.createUserGame(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userGame); // 201 Created
    }

    // Endpoint for deleting a user game
    @DeleteMapping("/{id}/games/{userGameId}")
    public ResponseEntity<Void> deleteUserGame(@PathVariable("id") Long userId, @PathVariable("userGameId") Long userGameId){
        userGameService.deleteUserGame(userGameId);
        return ResponseEntity.noContent().build();
    }

    // Endpoint for updating a user game
    @PutMapping("/{id}/games/{userGameId}")
    public ResponseEntity<UserGame> updateUserGame(@PathVariable("id") Long userId, @PathVariable("userGameId") Long userGameId, @RequestBody UserGameUpdateRequest request){
        UserGame userGame = userGameService.updateUserGame(userGameId, request);
        return ResponseEntity.ok(userGame);
    }

    // Endpoint for getting a user game
    @GetMapping("/{id}/games/{userGameId}")
    public ResponseEntity<UserGame> getUserGame(@PathVariable("id") Long userId, @PathVariable("userGameId") Long userGameId){
        UserGame userGame = userGameService.getUserGame(userGameId);
        return ResponseEntity.ok(userGame);
    }

    // Endpoint for getting all games for a user
    @GetMapping("/{id}/games")
    public ResponseEntity<List<UserGame>> getUserGames(@PathVariable Long id) {
        List<UserGame> userGames = userGameService.getUserGamesByUserId(id);
        return ResponseEntity.ok(userGames);
    }
}
