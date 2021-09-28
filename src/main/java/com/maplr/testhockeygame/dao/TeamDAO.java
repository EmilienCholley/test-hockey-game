package com.maplr.testhockeygame.dao;

import com.maplr.testhockeygame.entities.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamDAO extends CrudRepository<Team, Long> {

    Team findByYear(long year);

}
