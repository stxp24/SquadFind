package com.squadfind.backend.controller;

import com.squadfind.backend.dto.BasedOnLoggedInUserRequest;
import com.squadfind.backend.dto.FindAllUsersWithCriteriaRequest;
import com.squadfind.backend.model.User;
import com.squadfind.backend.service.DiscoverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/discover")
public class DiscoverController {
    private final DiscoverService discoverService;

    public DiscoverController(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @PostMapping
    public ResponseEntity<Set<User>> basedOnLoggedInUser(@RequestParam Long userId, @RequestBody BasedOnLoggedInUserRequest request){
        Set<User> users = discoverService.basedOnLoggedInUser(userId, request);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/search")
    public ResponseEntity<Set<User>> searchUsers(FindAllUsersWithCriteriaRequest request, @RequestParam Long userId){
        Set<User> users = discoverService.findUsersCustomCriteria(request, userId);
        return ResponseEntity.ok(users);
    }
}
