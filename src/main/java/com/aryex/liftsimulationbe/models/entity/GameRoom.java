package com.aryex.liftsimulationbe.models.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GameRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roomId;

    @OneToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Players> numberOfPlayers;

    private int numberOfFloors;

    private int numberOfLifts;
}
