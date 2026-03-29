package com.squadfind.backend.repo;

import com.squadfind.backend.model.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGameRepo extends JpaRepository<UserGame, Long> {
    boolean existsByUserIdAndGameId(Long userId, Long gameId);
    List<UserGame> findByUserId(Long userId);
    List<UserGame> findByGameId(Long gameId);
}
