package com.squadfind.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserRequest {
    private String squadFindUsername;
    private String password;
}
