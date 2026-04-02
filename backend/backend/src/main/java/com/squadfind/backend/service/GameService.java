package com.squadfind.backend.service;

import com.squadfind.backend.dto.CreateGameRequest;
import com.squadfind.backend.dto.UpdateGameRequest;
import com.squadfind.backend.model.Game;
import com.squadfind.backend.repo.GameRepo;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepo gameRepo;

    public GameService(GameRepo gameRepo) {
        this.gameRepo = gameRepo;
    }

    public Game createGame(CreateGameRequest request){
        // Validate
        if (gameRepo.existsByGameName(request.getGameName())){
            throw new RuntimeException("Game already exists");
        }
        // Create and return new Game
        return gameRepo.save(new Game(request.getGameName()));
    }

    public void deleteGame(Long gameId){
        if (!gameRepo.existsById(gameId)) {
            throw new RuntimeException("Game not found");
        }
        gameRepo.deleteById(gameId);
    }

    public Game updateGame(Long gameId, UpdateGameRequest request){
        Game game = gameRepo.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
        // Validate and set name
        if (request.getGameName() != null){
            game.setGameName(request.getGameName());
        }
        return gameRepo.save(game);
    }

}
