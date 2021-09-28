package com.maplr.testhockeygame.services;

import com.maplr.testhockeygame.dao.PlayerDAO;
import com.maplr.testhockeygame.dao.TeamDAO;
import com.maplr.testhockeygame.entities.Player;
import com.maplr.testhockeygame.entities.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private PlayerDAO playerDAO;

    @Override
    public Player savePlayer(long teamYear, Player player) {
        Team team = teamDAO.findByYear(teamYear);

        if (team == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Team not found for the year choosed");
        }

        if (playerDAO.findByTeamAndNumber(team,player.getNumber()) != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Number already used by a player of the team");
        }

        if (player.isCaptain() && playerDAO.findByTeamAndIsCaptain(team,true) != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Another player is already captain in this team");
        }

        player.setTeam(team);

        return playerDAO.save(player);

    }

    @Override
    public String defineCaptain(long id) {
        Optional<Player> newCaptainOptional = playerDAO.findById(id);

        if (newCaptainOptional.isPresent()){
            Player newCaptain = newCaptainOptional.get();

            Team team = newCaptain.getTeam();
            Player oldCaptain = playerDAO.findByTeamAndIsCaptain(team,true);

            oldCaptain.setCaptain(false);
            newCaptain.setCaptain(true);
            playerDAO.save(oldCaptain);
            playerDAO.save(newCaptain);

            return "OK";
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "The id provided does not correspond to any player");
        }
    }
}
