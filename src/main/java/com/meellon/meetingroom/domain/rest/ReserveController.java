package com.meellon.meetingroom.domain.rest;

import com.meellon.meetingroom.domain.reserve.Reserve;
import com.meellon.meetingroom.domain.service.ReserveService;
import com.meellon.meetingroom.domain.rest.model.ReserveRestDTO;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/reserves")
public class ReserveController {

    private ReserveService reserveService;

    /**
     * 예약 정보 가져오기
     * @return List<ReserveRestDTO> 예약리스트
     */
    @ApiOperation(value = "전체 예약 조회")
    @GetMapping
    public List<ReserveRestDTO> retrieveReserve() {
        return reserveService.retrieveReserves();
    }

    /**
     * 예약 정보 생성
     * @param dto
     * @return Reserve 예약 생성 정보
     */
    @ApiOperation(value = "예약 저장")
    @PostMapping
    @ResponseBody
    public List<ReserveRestDTO> saveReserve(@RequestBody @Valid ReserveRestDTO dto)
        throws Exception {
        return reserveService.createReserve(dto);
    }

    /**
     * 예약 등록된 일자 가져오기
     */
    @ApiOperation(value = "일자별 예약 데이터 가져오기")
    @GetMapping(path="/dates")
    public Map<LocalDate, List<ReserveRestDTO>> retrieveReserveDate() {
        return reserveService.retrieveReserveDates();
    }
}