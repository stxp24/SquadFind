package com.squadfind.backend.dto;

import com.squadfind.backend.enums.PlayStyle;
import com.squadfind.backend.enums.Role;
import com.squadfind.backend.enums.SkillLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserGameRequest {
    private Role role;
    private SkillLevel skillLevel;
    private PlayStyle playStyle;
    private Long gameId;
    /*
    * userId does not need to be included when receiving the packaged JSON for the DTO, as userId is extracted
    * from the endpoint POST /api/users/{id}/games
    */
}
