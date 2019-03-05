package com.meellon.meetingroom.domain.rest;

import com.meellon.meetingroom.domain.room.Room;
import com.meellon.meetingroom.domain.service.RoomService;
import com.meellon.meetingroom.domain.rest.model.RoomRestDTO;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/rooms")
public class RoomController {

    private RoomService roomService;

    /**
     * 룸 정보 가져오기
     * @return List<RoomRestDTO> 룸리스트
     */
    @ApiOperation(value = "전체 룸 조회")
    @GetMapping
    public List<RoomRestDTO> retrieveRooms() {
        return roomService.retrieveRooms();
    }

    /**
     * 룸정보 저장
     * @param dto
     * @return Room 저장된룸정보
     */
    @ApiOperation(value = "룸 생성")
    @PostMapping
    public Room savePosts(@RequestBody RoomRestDTO dto){
        return roomService.createRoom(dto);
    }
}