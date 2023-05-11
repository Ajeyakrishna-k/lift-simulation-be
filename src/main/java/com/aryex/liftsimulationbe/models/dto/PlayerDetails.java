package com.aryex.liftsimulationbe.models.dto;

import com.aryex.liftsimulationbe.models.PlayerRole;

public record PlayerDetails(String playerName, PlayerRole role, int playerId) {
}
