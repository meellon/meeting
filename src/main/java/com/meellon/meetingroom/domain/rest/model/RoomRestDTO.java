package com.meellon.meetingroom.domain.rest.model;

import com.meellon.meetingroom.domain.room.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomRestDTO {

    private Long id;
    private String name;
    private String location;

    public RoomRestDTO(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Room toEntity(){
        return Room.builder()
            .name(name)
            .location(location)
            .build();
    }
}