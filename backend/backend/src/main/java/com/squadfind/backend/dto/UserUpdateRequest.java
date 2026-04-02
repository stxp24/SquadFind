package com.squadfind.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
    private String squadFindUsername;
    private String email;
    private String bio;
}
