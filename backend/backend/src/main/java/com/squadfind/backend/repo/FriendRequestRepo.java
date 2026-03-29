package com.squadfind.backend.repo;

import com.squadfind.backend.enums.Status;
import com.squadfind.backend.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRequestRepo extends JpaRepository<FriendRequest, Long> {
    boolean existsBySenderIdAndReceiverId(Long senderId, Long receiverId);
    Optional<FriendRequest> findBySenderIdAndReceiverId(Long senderId, Long receiverId);
    List<FriendRequest> findByReceiverIdAndStatus(Long receiverId, Status status);
    List<FriendRequest> findBySenderIdAndStatus(Long senderId, Status status);
    List<FriendRequest> findBySenderIdOrReceiverId(Long senderId, Long receiverId);
}
