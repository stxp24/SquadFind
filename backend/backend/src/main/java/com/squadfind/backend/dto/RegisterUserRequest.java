package com.squadfind.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterUserRequest {
    private String squadFindUsername;
    private String email;
    private String password;
    private LocalDate birthday;
}
