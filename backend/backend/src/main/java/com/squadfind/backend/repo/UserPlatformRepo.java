package com.squadfind.backend.repo;

import com.squadfind.backend.enums.Platform;
import com.squadfind.backend.model.UserPlatform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPlatformRepo extends JpaRepository<UserPlatform, Long> {
    boolean existsByUserIdAndPlatform(Long userId, Platform platform);
    List<UserPlatform> findByUserId(Long userId);
}
