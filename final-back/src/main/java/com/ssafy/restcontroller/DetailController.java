package com.ssafy.restcontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.detail.dto.DetailDto;
import com.ssafy.detail.dto.dataToSendDto;
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

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        log.debug("article_no = {}", article_no);
        List<DetailDto> detailList = service.listDetail(article_no);
        log.debug("list 의 값 = {}", detailList);
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
    public ResponseEntity<?> registerDetail(@RequestBody Map<String, Object> formData) throws SQLException {
        try {
            Object stopover = formData.get("stopover");
            log.debug("stopover = {}", stopover);
            List<DetailDto> detailDtos = convertToDetailDtos(formData);
            log.debug("들어오는 formData 형식 = {}", detailDtos);
            
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    private List<DetailDto> convertToDetailDtos(Map<String, Object> formData) {
        List<DetailDto> detailDtos = new ArrayList<>();

        for (String key : formData.keySet()) {
            Object value = formData.get(key);

            if (value instanceof List) {
                // List 형태의 경우 처리
                List<Map<String, Object>> listValue = (List<Map<String, Object>>) value;
                for (Map<String, Object> item : listValue) {
                    DetailDto detailDto = convertToDetailDto(item);
                    detailDtos.add(detailDto);
                }
            } else {
                // 단일 값인 경우 처리
                DetailDto detailDto = convertToDetailDto(value);
                detailDtos.add(detailDto);
            }
        }

        return detailDtos;
    }

    private DetailDto convertToDetailDto(Object json) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(json, DetailDto.class);
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
