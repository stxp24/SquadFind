package com.squadfind.backend.repo;

import com.squadfind.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    boolean existsBySquadFindUsername(String squadFindUsername);
    boolean existsByEmail(String email);
    Optional<User> findBySquadFindUsername(String squadFindUsername); // Returns an Optional<User> because the user may not exist

}
