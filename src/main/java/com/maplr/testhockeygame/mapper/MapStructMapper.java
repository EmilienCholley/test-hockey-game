package com.maplr.testhockeygame.mapper;

import com.maplr.testhockeygame.dto.PlayerDTO;
import com.maplr.testhockeygame.entities.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    @Named("StringToPositionEnum")
    static Player.PlayerPosition stringToPositionEnum(String string) {
        Player.PlayerPosition result;
        switch(string) {
            case "forward":
                result = Player.PlayerPosition.FORWARD;
                break;
            case "defenseman":
                result = Player.PlayerPosition.DEFENSEMAN;
                break;
            case "goaltender":
                result = Player.PlayerPosition.GOALTENDER;
                break;
            default:
                result = Player.PlayerPosition.FORWARD;
        }

        return result;

    }

    @Mapping(source = "position", target = "position", qualifiedByName = "StringToPositionEnum")
    Player playerDTOToPlayer(PlayerDTO playerDTO);

}
