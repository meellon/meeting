package com.meellon.meetingroom.domain.reserve;

import java.time.LocalDate;
import java.time.LocalTime;
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
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long roomId;

    @Column(length = 12, nullable = false)
    private String userName;

    @Column(nullable = false)
    private LocalDate usedDate;

    @Column(nullable = false)
    private LocalTime startTime;
    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private Integer repeat;

    @Builder
    public Reserve(Long roomId, String userName
        , LocalDate usedDate, LocalTime startTime, LocalTime endTime, Integer repeat) {
        this.userName = userName;
        this.roomId = roomId;
        this.usedDate = usedDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.repeat = repeat;
    }

    public Boolean isRange(LocalTime dueTime) {
        return !(this.startTime.isAfter(dueTime) && this.endTime.isBefore(dueTime));
    }
}
