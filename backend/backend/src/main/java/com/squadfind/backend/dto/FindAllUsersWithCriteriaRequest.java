package com.squadfind.backend.dto;

import com.squadfind.backend.enums.Platform;
import com.squadfind.backend.enums.PlayStyle;
import com.squadfind.backend.enums.Role;
import com.squadfind.backend.enums.SkillLevel;
import com.squadfind.backend.model.Game;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindAllUsersWithCriteriaRequest {
    private Platform platform;
    private Long gameId;
    private Role role;
    private PlayStyle playStyle;
    private SkillLevel skillLevel;
}
