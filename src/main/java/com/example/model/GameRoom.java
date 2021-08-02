package com.example.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameRoom {
    @Id
    @GeneratedValue
    @Type(type = "pg-uuid")
    @Column(name = "id")
    private UUID id;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private Game game;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_room_id", referencedColumnName = "id")
    private Set<Player> players;

    public GameRoom(Game game, Set<Player> players) {
        this.game = game;
        this.players = players;
    }
}
