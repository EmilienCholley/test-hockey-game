package com.maplr.testhockeygame.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@Table(name = "player")
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "number", nullable = false)
    private long number;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "position", nullable = false)
    private PlayerPosition position;

    public enum PlayerPosition {
        DEFENSEMAN,
        FORWARD,
        GOALTENDER
    }

    @Column(name = "captain", nullable = false)
    boolean isCaptain;

    @JsonBackReference
    @ManyToOne @JoinColumn(name="idTeam", nullable=false)
    private Team team;

}