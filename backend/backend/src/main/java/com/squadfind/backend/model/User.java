package com.squadfind.backend.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity // Needed for object class
@NoArgsConstructor // Sets no arg constructor that is necessary for JPA
@Getter // Creates getters for all variables in User
@Setter // Creates setters for all variables in User
@Table(name = "users") // Sets table name for User class
public class User {
    @Id // Sets userId as primary id for User table
    @Setter(AccessLevel.NONE) // Ensures that a setter is not created for userId since it should not be changed
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment id (serialize)
    private Long id;

    @Column(unique = true, nullable = false) // Annotation ensures that username is unique and cannot be a null value in table
    private String squadFindUsername;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    private LocalDate birthday;
    private String bio;
    private LocalDateTime createdAt = LocalDateTime.now();

    public User(String squadFindUsername, LocalDate birthday, String email, String passwordHash, String bio) {
        this.squadFindUsername = squadFindUsername;
        this.birthday = birthday;
        this.email = email;
        this.passwordHash = passwordHash;
        this.bio = bio;
    }

}
