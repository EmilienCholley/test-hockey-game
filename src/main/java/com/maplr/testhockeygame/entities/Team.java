package com.maplr.testhockeygame.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Table(name = "team")
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "coach", length = 64, nullable = false)
    private String coach;

    @Column(name = "year", nullable = false)
    private long year;

    @JsonManagedReference
    @OneToMany( targetEntity=Player.class, mappedBy = "team")
    private List<Player> playerlist;

}