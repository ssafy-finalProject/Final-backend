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

import com.ssafy.calendar.CalendarDto;
import com.ssafy.calendar.service.CalendarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins= {"*"})
@RestController
@Slf4j
@RequestMapping("/calendar")
@Api("달력 관련 API테스트")
public class RestCalendarController {

	private CalendarService calService;

	public RestCalendarController(CalendarService calService) {
		super();
		this.calService = calService;
	}
	
	@ApiOperation(value = "달력 정보 연월일 입력", notes = "달력에 대한 정보를 입력한다.")
	@PostMapping
	public ResponseEntity<?> insertDate(@RequestBody @ApiParam(value = "달력 정보 입력", required = true)CalendarDto calDto)throws Exception{
		try {
			log.debug("calendar article= {}", calDto);
			calService.insertDate(calDto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	 @ApiOperation(value = "달력 정보 읽어오기", notes = "저장된 연월일을 읽어온다") // 댓글 추가해야함.
	 @GetMapping("/{calendar_id}")
	 public ResponseEntity<CalendarDto> getDateInfo(@PathVariable("calendar_id") @ApiParam(value = "얻어올 달력의 키값 번호", required = true)
		int calendar_id) throws Exception {
		 CalendarDto dateInfo = calService.getDateInfo(calendar_id);

			return new ResponseEntity<CalendarDto>(dateInfo, HttpStatus.OK);
		}
	 
	 @ApiOperation(value = "달력 날짜 수정하기", notes = "달력을 수정한다.")
	    @PutMapping("/{calendar_id}")
	    public ResponseEntity<?> modifyDateInfo(
	            @PathVariable @ApiParam(value = "수정할 달력 키값", required = true) int calendar_id,
	            @RequestBody @ApiParam(value = "수정할 달력 정보 입력", required = true)
	            CalendarDto calendarDto) throws Exception {
		 calendarDto.setArticle_no(calendar_id);
	        calService.modifyDateInfo(calendarDto);
	        CalendarDto dateInfo = calService.getDateInfo(calendar_id);
	        log.debug("modify calendar = {}", dateInfo);
	        return ResponseEntity.ok("수정 완료");
	    }

	    @ApiOperation(value = "달력 데이터 삭제", notes = "달력 데이터 삭제를 진행한다.")
	    @DeleteMapping("/{calendar_id}")
	    public ResponseEntity<String> deleteDateInfo(@PathVariable @ApiParam(value = "달력 정보 입력", required = true)
	                                                int calendar_id) {
	        try {
	            calService.deleteDateInfo(calendar_id);
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
