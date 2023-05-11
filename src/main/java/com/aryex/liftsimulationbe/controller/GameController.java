package com.aryex.liftsimulationbe.controller;

import com.aryex.liftsimulationbe.models.dto.CommandMessage;
import com.aryex.liftsimulationbe.models.dto.CreateRoomRecord;
import com.aryex.liftsimulationbe.models.dto.JoinRoomRequest;
import com.aryex.liftsimulationbe.models.dto.RoomCommand;
import com.aryex.liftsimulationbe.service.GameRoomService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GameController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired private GameRoomService gameRoomService;


    @MessageMapping("/command")
    public void processMessage(@Payload RoomCommand roomCommand) {
        System.out.println(roomCommand);
        messagingTemplate.convertAndSendToUser(
                String.valueOf(roomCommand.roomId()),"/queue/messages",
                CommandMessage.builder().command(roomCommand.command()).playerDetails(gameRoomService.getPlayerDetailsForTheRoom(roomCommand.playerId(), roomCommand.roomId())).build());

    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomRecord createRoomRecord){
        return ResponseEntity.ok(gameRoomService.createGameRoom(createRoomRecord));
    }

    @PutMapping("/join")
    public ResponseEntity<?> joinRoom(@RequestBody JoinRoomRequest joinRoomRequest){
        return ResponseEntity.ok(gameRoomService.joinRoom(joinRoomRequest));
    }

}
