package com.meellon.meetingroom.domain.reserve;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReserveJpaRepository extends JpaRepository<Reserve, Long> {
    @Query("select r from Reserve r where r.roomId = ?1 and r.usedDate = ?2 and r.startTime <= ?3 and r.endTime >= ?4")
    Optional<Reserve> findByStartTimeAfterAndEndTimeBefore(Long roomId, LocalDate usedDate, LocalTime startTime, LocalTime startTime2);
    long countByStartTimeAfterAndEndTimeBefore(LocalTime startTime1, LocalTime startTime2);
}
