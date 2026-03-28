package com.squadfind.backend.model;


import com.squadfind.backend.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "FriendRequest")
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    private Timestamp timestamp;
    private Status status;

    public FriendRequest(User sender, User receiver, Status status){
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

}
