package com.squadfind.backend.model;

import com.squadfind.backend.enums.PlayStyle;
import com.squadfind.backend.enums.Role;
import com.squadfind.backend.enums.SkillLevel;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "UserGame")
public class UserGame {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // Tells JPA what to store this variable as
    private Role role;

    @Enumerated(EnumType.STRING)
    private SkillLevel skillLevel;

    @Enumerated(EnumType.STRING)
    private PlayStyle playStyle;

    @ManyToOne // Sets the relationship between tables, many UserGames can belong to one User
    @JoinColumn(name = "user_id", nullable = false) // Joins the foreign key with 'name user_id' to the variable with the @Id annotation
    private User user; // Instantiates a private User variable that lets the application know what entity is being referenced

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    public UserGame(Role role, SkillLevel skillLevel, PlayStyle playStyle, User user, Game game){
        this.role = role;
        this.skillLevel = skillLevel;
        this.playStyle = playStyle;
        this.user = user; // Since JPA uses entity references rather than raw data, an actual reference to user and game
        this.game = game; // needs to be created, in which the user_id and game_id will be extracted from these references
    }

}
