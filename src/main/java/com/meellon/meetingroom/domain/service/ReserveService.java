package com.meellon.meetingroom.domain.service;

import com.meellon.meetingroom.domain.reserve.Reserve;
import com.meellon.meetingroom.domain.reserve.ReserveJpaRepository;
import com.meellon.meetingroom.helper.MapperHelper;
import com.meellon.meetingroom.domain.rest.model.ReserveRestDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReserveService {
    private static final Integer ADDDAYS = 7;

    @Autowired
    ReserveJpaRepository iRepository;

    public List<ReserveRestDTO> retrieveReserves() {
        return iRepository.findAll()
            .stream()
            .map(reserve -> MapperHelper.convert(reserve, ReserveRestDTO.class))
            .collect(Collectors.toList());
    }

    public Map<LocalDate, List<ReserveRestDTO>> retrieveReserveDates() {
        return iRepository.findAll()
            .stream()
            .map(reserve -> MapperHelper.convert(reserve, ReserveRestDTO.class))
            .sorted(Comparator.comparing(ReserveRestDTO::getStartTime))
            .collect(Collectors.groupingBy(ReserveRestDTO::getUsedDate));
    }

    @Transactional
    public List<ReserveRestDTO> createReserve(ReserveRestDTO dto) throws Exception {
        Optional<Reserve> optionalReserve = iRepository.findByStartTimeAfterAndEndTimeBefore(dto.getRoomId(), dto.getUsedDate(), dto.getStartTime(),dto.getStartTime());

        if(optionalReserve.isPresent()) {
            throw new Exception("이미 등록된 회의실입니다.");
        }

        List<ReserveRestDTO> reserveRestDTOS = new ArrayList<>();
        Integer repeat = dto.getRepeat();
        for( Integer i = 0; i < repeat ; i++ ) {
            if(repeat > 1) {
                dto.setRepeat(i+1);
            } else {
                dto.setRepeat(i);
            }
            dto.plusDays(i * ADDDAYS);
            Reserve reserve = iRepository.save(dto.toEntity());
            reserveRestDTOS.add(MapperHelper.convert(reserve, ReserveRestDTO.class));
        }

        return reserveRestDTOS;
    }
}
