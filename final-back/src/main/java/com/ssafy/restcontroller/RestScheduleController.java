package com.ssafy.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.schedule.ScheduleDto;
import com.ssafy.schedule.service.ScheduleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins= {"*"})
@RestController
@Slf4j
@RequestMapping("/schedule")
@Api("스케쥴(메모) 관련 API테스트")
public class RestScheduleController {
	private ScheduleService service;

	public RestScheduleController(ScheduleService service) {
		super();
		this.service = service;
	}
	
	@ApiOperation(value = "스케쥴 정보 연월일 입력", notes = "스케쥴에 대한 정보를 입력한다.")
	@PostMapping
	public ResponseEntity<?> insertMemo(@RequestBody @ApiParam(value = "스케쥴 정보 입력", required = true)ScheduleDto scheduleDto)throws Exception{
		try {
			log.debug("calendar article= {}", scheduleDto);
			service.insertMemo(scheduleDto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	 @ApiOperation(value = "스케쥴 정보 읽어오기", notes = "저장된 메모 읽어온다") 
	 @GetMapping("/{schedule_id}")
	 public ResponseEntity<ScheduleDto> getMemo(@PathVariable("schedule_id") @ApiParam(value = "얻어올 스케쥴의 키값 번호", required = true)
		int schedule_id) throws Exception {
		 ScheduleDto Memo = service.getMemo(schedule_id);

			return new ResponseEntity<ScheduleDto>(Memo, HttpStatus.OK);
		}
	 
	 @ApiOperation(value = "스케쥴 날짜 수정하기", notes = "스케쥴을 수정한다.")
	    @PutMapping("/{schedule_id}")
	    public ResponseEntity<?> modifyMemo(
	            @PathVariable @ApiParam(value = "수정할 스케쥴 키값", required = true) int schedule_id,
	            @RequestBody @ApiParam(value = "수정할 스케쥴 정보 입력", required = true)
	            ScheduleDto scheduleDto) throws Exception {
		 scheduleDto.setSchedule_id(schedule_id);
	        service.modifyMemo(scheduleDto);
	        ScheduleDto memo = service.getMemo(schedule_id);
	        log.debug("modify schedule = {}", memo);
	        return ResponseEntity.ok("수정 완료");
	    }

	    @ApiOperation(value = "스케쥴 데이터 삭제", notes = "스케쥴 데이터 삭제를 진행한다.")
	    @DeleteMapping("/{schedule_id}")
	    public ResponseEntity<String> deleteMemo(@PathVariable @ApiParam(value = "스케쥴 정보 입력", required = true)
	                                                int schedule_id) {
	        try {
	            service.deleteMemo(schedule_id);
	            return ResponseEntity.ok("success");
	        } catch (Exception e) {
	            return exceptionHandling(e);
	        }
	    }
	
	
    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
