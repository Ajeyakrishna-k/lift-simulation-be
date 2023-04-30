package com.aryex.liftsimulationbe.models.entity;

import com.aryex.liftsimulationbe.models.PlayerRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Players {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String playerName;

    private String playerRole;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Players players = (Players) o;
        return id == players.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
