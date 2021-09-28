package com.maplr.testhockeygame.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDTO {

    @NotNull
    @JsonProperty("number")
    long number;

    @NotNull
    @JsonProperty("name")
    String name;

    @NotNull
    @JsonProperty("lastname")
    String lastName;

    @NotNull
    @JsonProperty("position")
    String position;

    @NotNull
    @JsonProperty("isCaptain")
    boolean isCaptain;
}
