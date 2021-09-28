package com.maplr.testhockeygame.web.controller;

import com.maplr.testhockeygame.dao.TeamDAO;
import com.maplr.testhockeygame.dto.PlayerDTO;
import com.maplr.testhockeygame.entities.Player;
import com.maplr.testhockeygame.entities.Team;
import com.maplr.testhockeygame.mapper.MapStructMapper;
import com.maplr.testhockeygame.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TeamController {

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MapStructMapper mapStructMapper;

    /**
     * get a year's team
     *
     * @param year  year of the team
     * @return Team selected
     */
    @GetMapping(value = "/team/{year}")
    public ResponseEntity<Team> getTeam(@PathVariable long year) {

        Team result = teamDAO.findByYear(year);

        if (result != null) {
            return new ResponseEntity<>(result,
                    HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Team not found for the year choosed");
        }
    }

    /**
     * adds a player to a one-year team
     *
     * @param year  year of the team
     * @param playerDTO   player to save
     * @return player added
     */
    @PostMapping(value = "/team/{year}")
    public ResponseEntity<Player> addPlayer(@PathVariable long year,
                                 @RequestBody PlayerDTO playerDTO) {

        Player result = playerService.savePlayer(year, mapStructMapper.playerDTOToPlayer(playerDTO));

        if (result != null) {
            return new ResponseEntity<>(result,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null,
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Define a player as capitain of his team
     *
     * @param id   id of the player to define as captain
     * @return result of the request
     */
    @PutMapping(value = "/player/captain/{id}")
    public ResponseEntity<String> defineCaptain(@PathVariable long id) {
        String result =  playerService.defineCaptain(id);

        if (result.equals("OK")) {
            return new ResponseEntity<>("Captain defined",
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Captain not defined",
                    HttpStatus.BAD_REQUEST);
        }
    }
}
