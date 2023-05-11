package com.aryex.liftsimulationbe.service;

import com.aryex.liftsimulationbe.models.entity.Players;
import com.aryex.liftsimulationbe.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Players savePlayers(Players players){
        return playerRepository.save(players);
    }
}
