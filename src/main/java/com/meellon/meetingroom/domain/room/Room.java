package com.meellon.meetingroom.domain.room;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 12, nullable = false)
    private String name;

    @Column(length = 24, nullable = false)
    private String location;

    @Builder
    public Room(String name, String location) {
        this.name = name;
        this.location = location;
    }
}
