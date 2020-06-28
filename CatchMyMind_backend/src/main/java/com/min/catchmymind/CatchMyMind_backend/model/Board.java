package com.min.catchmymind.CatchMyMind_backend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String content;

    @Builder
    public Board(Long id, String title, User user, String content) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.content = content;
    }
}
