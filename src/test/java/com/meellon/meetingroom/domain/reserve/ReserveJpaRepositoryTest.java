package com.meellon.meetingroom.domain.reserve;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReserveJpaRepositoryTest {
    @Autowired
    ReserveJpaRepository reserveRepository;

    @After
    public void cleanup() {
        reserveRepository.deleteAll();
    }

    @Test
    @Transactional
    public void 예약_저장_불러오기() {
        //given
        reserveRepository.save(Reserve.builder()
            .roomId(1L)
            .userName("사용자1")
            .usedDate(LocalDate.of(2019, 1, 1))
            .startTime(LocalTime.of(12, 0, 0))
            .endTime(LocalTime.of(12, 30, 0))
            .repeat(1)
            .build());

        //when
        List<Reserve> reserves = reserveRepository.findAll();

        //then
        Reserve reserve = reserves.get(reserves.size() - 1);
        assertThat(reserve.getRoomId(), is(1L));
        assertThat(reserve.getUserName(), is("사용자1"));
        assertThat(reserve.getUsedDate().toString(), is("2019-01-01"));
        assertThat(reserve.getStartTime().toString(), is("12:00"));
        assertThat(reserve.getEndTime().toString(), is("12:30"));
        assertThat(reserve.getRepeat(), is(1));
    }
}