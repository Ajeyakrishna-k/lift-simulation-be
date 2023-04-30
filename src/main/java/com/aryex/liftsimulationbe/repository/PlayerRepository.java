package com.aryex.liftsimulationbe.repository;

import com.aryex.liftsimulationbe.models.entity.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Players,Integer> {
}
