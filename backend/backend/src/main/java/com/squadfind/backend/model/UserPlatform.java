package com.squadfind.backend.model;

import com.squadfind.backend.enums.Platform;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_platform")
public class UserPlatform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Platform platform;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(unique = true, nullable = false)
    private String gamertag;

    public UserPlatform(Platform platform, String gamertag, User user){
        this.platform = platform;
        this.gamertag = gamertag;
        this.user = user;
    }
}
