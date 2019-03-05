package com.meellon.meetingroom.domain.service;

import com.meellon.meetingroom.domain.room.Room;
import com.meellon.meetingroom.domain.room.RoomJpaRepository;
import com.meellon.meetingroom.helper.MapperHelper;
import com.meellon.meetingroom.domain.rest.model.RoomRestDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomService {
    @Autowired
    RoomJpaRepository iRepository;

    public List<RoomRestDTO> retrieveRooms() {
        return iRepository.findAll()
            .stream()
            .map(room -> MapperHelper.convert(room, RoomRestDTO.class))
            .collect(Collectors.toList());
    }

    @Transactional
    public Room createRoom(RoomRestDTO dto) {
        return iRepository.save(dto.toEntity());
    }
}
