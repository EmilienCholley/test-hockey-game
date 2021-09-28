package com.maplr.testhockeygame.services;

import com.maplr.testhockeygame.entities.Player;

public interface PlayerService {

    Player savePlayer(long teamYear, Player player);

    String defineCaptain(long id);

}
