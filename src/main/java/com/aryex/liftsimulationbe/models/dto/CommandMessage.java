package com.aryex.liftsimulationbe.models.dto;

import lombok.Builder;

@Builder
public record CommandMessage(Command command, PlayerDetails playerDetails) {
}
