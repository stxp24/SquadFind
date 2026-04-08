package com.squadfind.backend.repo;

import com.squadfind.backend.enums.PlayStyle;
import com.squadfind.backend.enums.Role;
import com.squadfind.backend.enums.SkillLevel;
import com.squadfind.backend.model.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGameRepo extends JpaRepository<UserGame, Long> {
    boolean existsByUserIdAndGameId(Long userId, Long gameId);
    List<UserGame> findByUserId(Long userId);
    List<UserGame> findByGameId(Long gameId);

    List<UserGame> findByRole(Role role);

    List<UserGame> findByPlayStyle(PlayStyle playStyle);

    List<UserGame> findBySkillLevel(SkillLevel skillLevel);
}
