package com.meellon.meetingroom.domain.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meellon.meetingroom.domain.reserve.Reserve;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReserveRestDTO {

    @NonNull
    private Long roomId;

    @Size(min = 2, max = 20)
    @NotBlank(message = "이름을 작성해주세요.")
    private String userName;

    @NonNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate usedDate;

    @NonNull
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime startTime;

    @NonNull
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime endTime;

    private Integer repeat = 1;

    public void setRoomId(Long roomId) throws Exception {
        if(roomId <= 0L) {
            throw new Exception("룸을 선택하세요.");
        } else {
            this.roomId = roomId;
        }
    }

    public void setStartTime(LocalTime startTime) throws Exception {
        if(startTime.getMinute() % 30 != 0 ) {
            throw new Exception("30분 단위로 입력하세요.");
        } else {
            this.startTime = startTime;
        }
    }

    public void setEndTime(LocalTime endTime) throws Exception {
        if(endTime.getMinute() % 30 != 0 ) {
            throw new Exception("30분 단위로 입력하세요.");
        } else {
            this.endTime = endTime;
        }
    }

    public void setRepeat(Integer repeat) {
        if(repeat != null) {
            this.repeat = repeat;
        }
    }

    public void timeDuration() throws Exception {
        if(this.startTime.hashCode() >= this.endTime.hashCode()) {
            throw new Exception("종료시간은 시작시간보다 커야합니다.");
        }
    }

    public ReserveRestDTO(@NonNull Long roomId,
        @Size(min = 2, max = 20) @NotBlank(message = "이름을 작성해주세요.") String userName,
        @NonNull LocalDate usedDate, @NonNull LocalTime startTime,
        @NonNull LocalTime endTime, Integer repeat) {
        this.roomId = roomId;
        this.userName = userName;
        this.usedDate = usedDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.repeat = repeat;
    }

    public Reserve toEntity(){
        return Reserve.builder()
            .roomId(roomId)
            .userName(userName)
            .usedDate(usedDate)
            .startTime(startTime)
            .endTime(endTime)
            .repeat(repeat)
            .build();
    }

    public void plusDays(Integer days) {
        this.usedDate = this.usedDate.plusDays(days);
    }
}