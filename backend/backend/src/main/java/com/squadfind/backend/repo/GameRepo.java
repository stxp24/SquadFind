package com.squadfind.backend.repo;

import com.squadfind.backend.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepo extends JpaRepository<Game, Long> {
    boolean existsByGameName(String gameName);
    Optional<Game> findByGameName(String gameName);
}
