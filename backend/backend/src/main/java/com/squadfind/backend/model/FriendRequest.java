package com.squadfind.backend.model;


import com.squadfind.backend.enums.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "friend_request")
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    private Timestamp timestamp;

    @Column(nullable = false)
    private Status status;

    public FriendRequest(User sender, User receiver){
        this.sender = sender;
        this.receiver = receiver;
        this.status = Status.PENDING;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

}
