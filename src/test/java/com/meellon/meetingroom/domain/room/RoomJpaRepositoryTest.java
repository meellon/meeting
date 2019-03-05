package com.meellon.meetingroom.domain.room;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.util.List;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomJpaRepositoryTest {
    @Autowired
    RoomJpaRepository roomRepository;

    @After
    public void cleanup() {
        roomRepository.deleteAll();
    }

    @Test
    public void 룸_저장_불러오기() {
        //given
        roomRepository.save(Room.builder()
            .name("테스트 회의실")
            .location("테스트 위치")
            .build());

        //when
        List<Room> rooms = roomRepository.findAll();

        //then
        Room room = rooms.get(rooms.size() - 1);
        assertThat(room.getName(), is("테스트 회의실"));
        assertThat(room.getLocation(), is("테스트 위치"));
    }
}