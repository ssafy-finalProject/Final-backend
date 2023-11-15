package com.ssafy.restcontroller;

import com.ssafy.detail.dto.DetailDto;
import com.ssafy.detail.service.DetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping("/detail")
@Api("지도 관련한 api 정보들")
public class DetailController {

    private DetailService service;

    public DetailController(DetailService service) {
        this.service = service;
    }

    @ApiOperation(value = "게시글에 관련한 지도 리스트", notes = "지도에 핀을 찍은 정보들을 전부 불러온다.")
    @GetMapping("/list/{article_no}")
    public ResponseEntity<List<DetailDto>> listDetail(@PathVariable @ApiParam(value = "게시글 번호 입력", required = true) int article_no) throws SQLException {
        List<DetailDto> detailList = service.listDetail(article_no);
        return new ResponseEntity<>(detailList, HttpStatus.OK);
    }

    @ApiOperation(value = "게시글에 관련한 지도 정보", notes = "게시판 번호에 딸린 여행 정보 위치를 가져온다.")
    @GetMapping("/find/{place_name}")
    public ResponseEntity<DetailDto> findDetail(@PathVariable @ApiParam(value = "지도 저장 장소", required = true) String place_name)
            throws SQLException {

        DetailDto detail = service.findDetail(place_name);
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    @ApiOperation(value = "지도 정보 작성", notes = "지도 정보를 작성한다.")
    @PostMapping
    public ResponseEntity<?> registerDetail(@RequestBody DetailDto detailDto) throws SQLException {
        try {
            service.registerDetail(detailDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "지도 정보 수정", notes = "지도 정보를 수정하는데, 제대로 안되면 pathVariable로 게시글 정보를 받자.")
    @PutMapping
    public ResponseEntity<?> modifyDetail(@RequestBody DetailDto detailDto) throws SQLException {
        service.modifyDetail(detailDto);
        log.debug("detailDto = {}", detailDto);
        return ResponseEntity.ok("수정 완료");
    }

    @ApiOperation(value = "지도 정보를 삭제 한다." , notes = "삭제할 지도 정보를 선택하는 거임.")
    @DeleteMapping("/{place_name}")
    public ResponseEntity<String> deleteDetail(@PathVariable @ApiParam(value = "삭제할 게시글 번호를 넣자", required = true) String place_name)
        throws SQLException {
        try {
            log.debug("place_name = {}", place_name);
            service.deleteDetail(place_name);
            return ResponseEntity.ok("삭제 성공");
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }


    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
