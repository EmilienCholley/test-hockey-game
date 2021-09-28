package com.maplr.testhockeygame.dao;

import com.maplr.testhockeygame.entities.Player;
import com.maplr.testhockeygame.entities.Team;
import org.springframework.data.repository.CrudRepository;

public interface PlayerDAO extends CrudRepository<Player, Long> {

    Player findByTeamAndIsCaptain(Team team, Boolean isCaptain);

    Player findByTeamAndNumber(Team team, long number);

}
