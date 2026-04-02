package com.squadfind.backend.service;

import com.squadfind.backend.dto.CreateFriendRequestRequest;
import com.squadfind.backend.enums.Status;
import com.squadfind.backend.model.FriendRequest;
import com.squadfind.backend.model.User;
import com.squadfind.backend.repo.FriendRequestRepo;
import com.squadfind.backend.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class FriendRequestService {
    private final UserRepo userRepo;
    private final FriendRequestRepo friendRequestRepo;

    public FriendRequestService(UserRepo userRepo, FriendRequestRepo friendRequestRepo) {
        this.userRepo = userRepo;
        this.friendRequestRepo = friendRequestRepo;
    }

    public FriendRequest createFriendRequest(Long senderId, CreateFriendRequestRequest request){
        User sentBy = userRepo.findById(senderId).orElseThrow(() -> new RuntimeException("User not found"));
        User sentTo = userRepo.findById(request.getSentToId()).orElseThrow(() -> new RuntimeException("User not found"));

        // Validate that friend request has not already been sent
        if (friendRequestRepo.existsBySenderIdAndReceiverId(senderId, request.getSentToId())){
            throw new RuntimeException("Friend request has already been sent");
        }

        // FriendRequest constructor automatically sets status to pending and generates timestamp
        return friendRequestRepo.save(new FriendRequest(sentBy, sentTo));
    }

    public void deleteFriendRequest(Long friendRequestId){
        // Validate that the friend request exists
        if (!friendRequestRepo.existsById(friendRequestId)){
            throw new RuntimeException("Friend request not found");
        }
        friendRequestRepo.deleteById(friendRequestId);
    }

    public FriendRequest acceptFriendRequest(Long friendRequestId){
        // Validate that the friend request exists
       FriendRequest request = friendRequestRepo.findById(friendRequestId).orElseThrow(() -> new RuntimeException("Friend request not found"));

       // Validate that the friend request is pending
        if (request.getStatus() != Status.PENDING){
            throw new RuntimeException("Friend request has already been accepted or declined");
        }

        request.setStatus(Status.ACCEPTED);
        return friendRequestRepo.save(request);
    }

    public FriendRequest declineFriendRequest(Long friendRequestId){
        // Validate that the friend request exists
       FriendRequest request = friendRequestRepo.findById(friendRequestId).orElseThrow(() -> new RuntimeException("Friend request not found"));

       // Validate that the friend request is pending
        if (request.getStatus() != Status.PENDING){
            throw new RuntimeException("Friend request has already been accepted or declined");
        }

        request.setStatus(Status.DECLINED);
        return friendRequestRepo.save(request);
    }

    public List<FriendRequest> getFriends(Long userId) {
        return friendRequestRepo.findBySenderIdOrReceiverId(userId, userId)
                .stream()
                .filter(fr -> fr.getStatus() == Status.ACCEPTED)
                .toList();
    }
}
