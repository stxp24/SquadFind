package com.squadfind.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasedOnLoggedInUserRequest {
    Long gameId;
    Long platformId;
}
