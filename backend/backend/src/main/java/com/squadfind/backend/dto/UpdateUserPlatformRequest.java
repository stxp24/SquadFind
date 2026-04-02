package com.squadfind.backend.dto;

import com.squadfind.backend.enums.Platform;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserPlatformRequest {
    private Platform platform;
    private String gamertag;
}
