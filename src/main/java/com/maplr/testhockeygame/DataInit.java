package com.maplr.testhockeygame;

import com.maplr.testhockeygame.dao.PlayerDAO;
import com.maplr.testhockeygame.dao.TeamDAO;
import com.maplr.testhockeygame.entities.Player;
import com.maplr.testhockeygame.entities.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements ApplicationRunner {

    private TeamDAO teamDAO;
    private PlayerDAO playerDAO;

    @Autowired
    public DataInit(TeamDAO teamDAO, PlayerDAO playerDAO) {
        this.teamDAO = teamDAO;
        this.playerDAO = playerDAO;
    }

    @Override
    public void run(ApplicationArguments args) {
        long count = teamDAO.count();

        if (count == 0) {
            Team t1 = new Team();

            t1.setCoach("John");
            t1.setYear(2020);

            //
            Team t2 = new Team();

            t2.setCoach("Smith");
            t2.setYear(2019);

            teamDAO.save(t1);
            teamDAO.save(t2);


            //
            Player p1 = new Player();

            p1.setName("Emilien");
            p1.setLastName("Cholley");
            p1.setNumber(77);
            p1.setPosition(Player.PlayerPosition.DEFENSEMAN);
            p1.setCaptain(false);
            p1.setTeam(t2);

            //
            Player p2 = new Player();

            p2.setName("Marrion");
            p2.setLastName("Felix");
            p2.setNumber(33);
            p2.setPosition(Player.PlayerPosition.FORWARD);
            p2.setCaptain(true);
            p2.setTeam(t2);


            playerDAO.save(p1);
            playerDAO.save(p2);
        }
    }
}
