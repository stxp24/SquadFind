package com.squadfind.backend.controller;

import com.squadfind.backend.dto.CreateFriendRequestRequest;
import com.squadfind.backend.model.FriendRequest;
import com.squadfind.backend.service.FriendRequestService;
import com.squadfind.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendRequestController {
    private final FriendRequestService friendRequestService;

    public FriendRequestController(UserService userService, FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    // Endpoint for sending friend request
    @PostMapping("/request")
    public ResponseEntity<FriendRequest> sendFriendRequest(@RequestParam Long sentById, @RequestBody CreateFriendRequestRequest request){
        FriendRequest frRequest = friendRequestService.createFriendRequest(sentById, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(frRequest); // 201 Created
    }

    // Endpoint for accepting friend request
    @PutMapping("/request/{requestId}/accept")
    public ResponseEntity<FriendRequest> acceptFriendRequest(@PathVariable Long requestId){
        FriendRequest frRequest = friendRequestService.acceptFriendRequest(requestId);
        return ResponseEntity.ok(frRequest);
    }

    // Endpoint for declining friend request
    @PutMapping("/request/{requestId}/decline")
    public ResponseEntity<Void> declineFriendRequest(@PathVariable Long requestId){
        friendRequestService.declineFriendRequest(requestId);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to view accepted friends
    @GetMapping
    public ResponseEntity<List<FriendRequest>> getFriends(@RequestParam Long userId) {
        List<FriendRequest> friends = friendRequestService.getFriends(userId);
        return ResponseEntity.ok(friends);
    }

    // Endpoint to view pending incoming friend requests
    @GetMapping("/request/incoming")
    public ResponseEntity<List<FriendRequest>> getIncomingRequests(@RequestParam Long userId) {
        List<FriendRequest> incoming = friendRequestService.getIncomingRequests(userId);
        return ResponseEntity.ok(incoming);
    }

    // Endpoint to view pending outgoing friend requests
    @GetMapping("/request/outgoing")
    public ResponseEntity<List<FriendRequest>> getOutgoingRequests(@RequestParam Long userId){
        List<FriendRequest> outgoing = friendRequestService.getOutgoingRequests(userId);
        return ResponseEntity.ok(outgoing);
    }

    // Endpoint for deleting a friend request
    @DeleteMapping("/request/{requestId}")
    public ResponseEntity<Void> deleteFriendRequest(@PathVariable Long requestId){
        friendRequestService.deleteFriendRequest(requestId);
        return ResponseEntity.noContent().build();
    }

}
