package com.aryex.liftsimulationbe.service;

import com.aryex.liftsimulationbe.models.PlayerRole;
import com.aryex.liftsimulationbe.models.dto.CreateRoomRecord;
import com.aryex.liftsimulationbe.models.dto.GameRoomDetails;
import com.aryex.liftsimulationbe.models.dto.JoinRoomRequest;
import com.aryex.liftsimulationbe.models.dto.PlayerDetails;
import com.aryex.liftsimulationbe.models.entity.GameRoom;
import com.aryex.liftsimulationbe.models.entity.Players;
import com.aryex.liftsimulationbe.repository.GameRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class GameRoomService {

    @Autowired
    private GameRoomRepository gameRoomRepository;

    @Autowired
    private PlayerService playerService;

    @Value("${room.settings.players}")
    private int MAX_PLAYERS;

    public PlayerDetails getPlayerDetailsForTheRoom(int playerId,int roomId){
        GameRoom gameRoom = gameRoomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Game room not found"));
        if(!gameRoom.getNumberOfPlayers().contains(Players.builder().id(playerId).build())){
            throw new RuntimeException("Player does not belong to the same room");
        }
        for (Players players : gameRoom.getNumberOfPlayers()){
            if(players.getId() == playerId) return new PlayerDetails(players.getPlayerName(),PlayerRole.valueOf(players.getPlayerRole()),playerId);
        }
        return null;
    }

    public GameRoomDetails createGameRoom(CreateRoomRecord createRoomRecord){
        Players savedAdmin = playerService.savePlayers(Players.builder().playerName(createRoomRecord.adminName()).playerRole(PlayerRole.ADMIN.name()).build());

        GameRoom gameRoom = gameRoomRepository.save(GameRoom.builder()
                .numberOfPlayers(Collections.singleton(savedAdmin))
                .numberOfFloors(createRoomRecord.numberOfFloors())
                .numberOfLifts(createRoomRecord.numberOfLifts())
                .build());


        return new GameRoomDetails(gameRoom.getRoomId(),savedAdmin.getId(),gameRoom.getNumberOfFloors(),gameRoom.getNumberOfLifts());
    }

    public GameRoomDetails joinRoom(JoinRoomRequest joinRoomRequest){
        Players savedPlayer = playerService.savePlayers(Players.builder().playerName(joinRoomRequest.playersDetails().playerName()).playerRole(PlayerRole.NOBODY.name()).build());
        GameRoom gameRoom = gameRoomRepository.findById(joinRoomRequest.roomId()).orElseThrow(() -> new RuntimeException("Game room not found"));
        if(gameRoom.getNumberOfPlayers().size() == MAX_PLAYERS) throw new RuntimeException("Room full");
        gameRoom.getNumberOfPlayers().add(savedPlayer);
        gameRoom = gameRoomRepository.save(gameRoom);
        return new GameRoomDetails(gameRoom.getRoomId(),savedPlayer.getId(),gameRoom.getNumberOfFloors(),gameRoom.getNumberOfLifts());
    }

}
