package com.squadfind.backend.dto;

import com.squadfind.backend.enums.Platform;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserPlatformRequest {
    private Platform platform;
    private String gamertag;
    /*
     * userId does not need to be included when receiving the packaged JSON for the DTO, as userId is extracted
     * from the endpoint POST /api/users/{id}/platforms
     */
}
