package com.meellon.meetingroom.config;

import com.meellon.meetingroom.domain.rest.model.ReserveRestDTO;
import com.meellon.meetingroom.domain.service.ReserveService;
import com.meellon.meetingroom.domain.service.RoomService;
import com.meellon.meetingroom.domain.rest.model.RoomRestDTO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class H2Configuration implements CommandLineRunner {

    @Autowired
    RoomService roomService;
    @Autowired
    ReserveService reserveService;

    @Override
    public void run(String... args) throws Exception {
        // 기본 룸 생성
        List<RoomRestDTO> rooms = new ArrayList<>();
        rooms.add(new RoomRestDTO("회의실 A", "1층"));
        rooms.add(new RoomRestDTO("회의실 B", "2층"));
        rooms.add(new RoomRestDTO("회의실 C", "3층"));
        rooms.add(new RoomRestDTO("회의실 D", "4층"));
        rooms.add(new RoomRestDTO("회의실 E", "5층"));
        rooms.add(new RoomRestDTO("회의실 F", "6층"));
        rooms.add(new RoomRestDTO("회의실 G", "7층"));
        rooms.add(new RoomRestDTO("회의실 H", "8층"));
        rooms.add(new RoomRestDTO("회의실 I", "9층"));
        rooms.add(new RoomRestDTO("회의실 J", "10층"));

        rooms
            .forEach(roomRestDTO -> roomService.createRoom(roomRestDTO));

        List<ReserveRestDTO> reserves = new ArrayList<>();
        reserves.add(new ReserveRestDTO(2L, "사용자 A", LocalDate.of(2019, 3, 6)
            , LocalTime.of(10, 0, 0), LocalTime.of(12, 0, 0), 1));
        reserves.add(new ReserveRestDTO(3L, "사용자 B", LocalDate.of(2019, 3, 6)
            , LocalTime.of(13, 0, 0), LocalTime.of(15, 0, 0), 1));
        reserves.add(new ReserveRestDTO(4L, "사용자 C", LocalDate.of(2019, 3, 6)
            , LocalTime.of(13, 0, 0), LocalTime.of(17, 0, 0), 1));
        reserves.add(new ReserveRestDTO(5L, "사용자 B", LocalDate.of(2019, 3, 6)
            , LocalTime.of(10, 0, 0), LocalTime.of(10, 30, 0), 1));
        reserves.add(new ReserveRestDTO(6L, "사용자 C", LocalDate.of(2019, 3, 6)
            , LocalTime.of(10, 30, 0), LocalTime.of(11, 30, 0), 1));
        reserves.add(new ReserveRestDTO(7L, "사용자 F", LocalDate.of(2019, 3, 6)
            , LocalTime.of(17, 0, 0), LocalTime.of(19, 0, 0), 2));

        reserves
            .forEach(reserveRestDTO -> {
                try {
                    reserveService.createReserve(reserveRestDTO);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            });
    }
}
