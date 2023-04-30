package com.aryex.liftsimulationbe.repository;

import com.aryex.liftsimulationbe.models.entity.GameRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRoomRepository extends JpaRepository<GameRoom,Integer> {
}
