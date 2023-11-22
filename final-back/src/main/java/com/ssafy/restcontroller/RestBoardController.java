package com.ssafy.restcontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.board.BoardDto;
import com.ssafy.board.BoardListDto;
import com.ssafy.board.service.boardService;
import com.ssafy.calendar.CalendarDto;
import com.ssafy.calendar.SendCalInformDto;
import com.ssafy.comment.service.CommentService;
import com.ssafy.detail.dto.DetailDto;
import com.ssafy.detail.dto.dataToSendDto;
import com.ssafy.file.FileDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
@Slf4j
@RequestMapping("/board")
@Api("게시판 관련 API 테스트")
public class RestBoardController {

    private boardService boardService;
    private CommentService commentService;
    private String uploadPath = "/server/upload";
    public RestBoardController(com.ssafy.board.service.boardService boardService, CommentService commentService) {
        this.boardService = boardService;
        this.commentService = commentService;
    }

    //    @ApiOperation(value = "게시판 글 목록", notes = "모든 게시글을 반환한다.")
//    @GetMapping("/list")
//    public ResponseEntity<?> getBoardList(@RequestParam @ApiParam(value = "게시글들 출력을 위한 정보", required = true)
//                                          Map<String, String> map) throws SQLException {
//        try {
//            log.debug("map = {}", map);
//            List<BoardDto> list = boardService.listArticle(map);
//            HttpHeaders header = new HttpHeaders();
//            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//            return ResponseEntity.ok().headers(header).body(list);
//        } catch (Exception e) {
//            return exceptionHandling(e);
//        }
//    }

    @ApiOperation(value = "게시판 초기 조회 목록", notes = "초기 실행 시에 모든 글을 반환한다.")
    @GetMapping
    public ResponseEntity<List<BoardDto>> firstList() throws SQLException {
        List<BoardDto> list = boardService.totalList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "게시판 검색 조회 목록", notes = "검색 관련 게시글을 반환한다.")
    @GetMapping("/list")
    public ResponseEntity<?> getBoardList(
            @RequestParam(required = false) @ApiParam(value = "검색할 키", defaultValue = "subject") String key,
            @RequestParam(required = false) @ApiParam(value = "검색어") String word,
            @RequestParam(required = false) @ApiParam(value = "시작 페이지") String pgno) throws SQLException {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("key", key);
            map.put("word", word);
            map.put("pgno", pgno);

            log.debug("map = {}", map);
//            List<BoardDto> list = boardService.listArticle(map);
            BoardListDto list = boardService.listArticle(map);
            log.debug("list의 값은 = {}", list);

            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            return ResponseEntity.ok().headers(header).body(list);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    
    @ApiOperation(value = "내가 쓴글 검색 조회 목록", notes = "검색 관련 게시글을 반환한다.")
    @GetMapping("/mylist")
    public ResponseEntity<?> getMyFeedList(
    		@RequestParam(required = false) @ApiParam(value = "아이디") String id,
            @RequestParam(required = false) @ApiParam(value = "검색어") String word,
            @RequestParam(required = false) @ApiParam(value = "시작 페이지") String pgno) throws SQLException {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("id", id);
            map.put("word", word);
            map.put("pgno", pgno);

            log.debug("map = {}", map);
//            List<BoardDto> list = boardService.listArticle(map);
            BoardListDto list = boardService.listMyArticle(map);
            log.debug("list의 값은 = {}", list);

            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            return ResponseEntity.ok().headers(header).body(list);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    
    @ApiOperation(value = "메인 검색 조회 목록", notes = "검색 관련 게시글을 반환한다.")
    @GetMapping("/wholelist")
    public ResponseEntity<?> getWholeList(
            @RequestParam(required = false) @ApiParam(value = "검색어") String word) throws SQLException {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("word", word);

            log.debug("map = {}", map);
//            List<BoardDto> list = boardService.listArticle(map);
            BoardListDto list = boardService.getWholeList(map);
            log.debug("list의 값은 = {}", list);

            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            return ResponseEntity.ok().headers(header).body(list);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    
    

    @ApiOperation(value = "게시판 글 조회", notes = "선택된 게시글을 읽어온다.") // 댓글 추가해야함.
    @GetMapping("/{articleno}")
    public ResponseEntity<BoardDto> getArticle(@PathVariable("articleno") @ApiParam(value = "얻어올 글의 번호", required = true)
                                               int articleno) throws Exception {
        BoardDto article = boardService.getArticle(articleno);
        boardService.updateHit(articleno);
        List<FileDto> files = boardService.getImages(articleno);
        article.setDtos(files);

        return new ResponseEntity<BoardDto>(article, HttpStatus.OK);
    }

    
    @ApiOperation(value = "게시글 입력, 첫번째 인자 파일배열, 두번째는 dto", notes = "게시글에 대한 정보를 입력한다.")
    @PostMapping
    public ResponseEntity<?> writeArticle(
    		@RequestParam("user_id")String user_id,
    		@RequestParam(value="subject", required=false)String subject,
    		@RequestParam(value="content", required=false)String content,
    		@RequestParam(value="files", required=false)MultipartFile[] files,
    		@RequestParam("dataToSend") String dataToSends,
    		@RequestParam("sendCalInform") String sendCalInforms
    		)
//    		@ModelAttribute BoardDto boardDto) 
    		throws Exception {
        try {
//        	log.debug("이름 가보자= {}",boardDto);
//        	for (int i = 0; i < boardDto.getFiles().length; i++) {
//        		log.debug("파일 이름= {}",boardDto.getFiles()[i].getOriginalFilename());
//			}
        	ObjectMapper objectMapper = new ObjectMapper();
        	dataToSendDto dataToSend = objectMapper.readValue(dataToSends, dataToSendDto.class);
        	log.debug("dataToSend정보{}",dataToSends);
        	log.debug("sendcal정보{}",sendCalInforms);
        	ObjectMapper objectMapper2 = new ObjectMapper();
//            log.debug("디테일 dto는 {}", dataToSend.getMarkers());
//            log.debug("전환후는{}", vi);
        	List<CalendarDto> calendarDtos = objectMapper.readValue(sendCalInforms, new TypeReference<List<CalendarDto>>() {});
        	
        	SendCalInformDto sendCalInform = new SendCalInformDto();
        	sendCalInform.setCalendarDto(calendarDtos);
        	
        	BoardDto boardDto = new BoardDto();
        	boardDto.setUser_id(user_id);
        	boardDto.setSubject(subject);
        	boardDto.setContent(content);
        	boardDto.setFiles(files);
        	
        	
            boardService.writeArticle(boardDto.getFiles(),boardDto,dataToSend,sendCalInform);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

//    @ApiOperation(value = "수정 할 글 얻기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = BoardDto.class)
//    @GetMapping("/modify/{articleno}")
//    public ResponseEntity<BoardDto> getModifyArticle(
//            @PathVariable("articleno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int articleno)
//            throws Exception {
//        log.info("getModifyArticle - 호출 : " + articleno);
//        return new ResponseEntity<>(boardService.getArticle(articleno), HttpStatus.OK);
//    }

    @ApiOperation(value = "게시글 수정하기", notes = "게시글을 수정한다. 다만, article_no와 user_id는 변경되면 안된다.")
    @PutMapping("/{articleno}")
    public ResponseEntity<?> modifyArticle(
            @PathVariable @ApiParam(value = "수정할 게시글 번호", required = true) int articleno,
            @RequestBody @ApiParam(value = "수정할 게시글 정보 입력", required = true)
            BoardDto boardDto) throws Exception {
        boardDto.setArticle_no(articleno);
        boardService.modifyArticle(boardDto);
        BoardDto article = boardService.getArticle(articleno);
        log.debug("modify article = {}", article);
        return ResponseEntity.ok("수정 완료");
//        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글 삭제를 진행한다.")
    @DeleteMapping("/{articleno}")
    public ResponseEntity<String> deleteArticle(@PathVariable @ApiParam(value = "게시글 정보 입력", required = true)
                                                int articleno) {
        try {
            boardService.deleteArticle(articleno);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    
    @ApiOperation(value = "날짜,이미지 풀경로", notes = "접근경로 생성해주기")
    @GetMapping("/{date}/{imagepath}")
    public ResponseEntity<byte[]> getImageFile(@PathVariable("imagepath") String imagepath,@PathVariable("date") String date) throws IOException {
    	InputStream imageStream = new FileInputStream(uploadPath + "/"+date+"/"+imagepath);
    	byte[] imageByteArray = IOUtils.toByteArray(imageStream);
    	imageStream.close();
    	return new ResponseEntity<byte[]>(imageByteArray,HttpStatus.OK);
    }
    
    @ApiOperation(value = "글의 detail 정보 반환", notes = "detail정보를 반환한다.")
    @GetMapping("/getDetails/{articleno}")
    public ResponseEntity<?> getWholeList(
    		@PathVariable @ApiParam(value = "게시글 정보 입력", required = true) int articleno) throws SQLException {
        try {
            Map<String, Integer> map = new HashMap<>();
            map.put("article_no", articleno);

            log.debug("map = {}", map);
            List<DetailDto> list = boardService.getDetails(map);
            log.debug("list의 값은 = {}", list);

            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            return ResponseEntity.ok().headers(header).body(list);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    
    @ApiOperation(value = "글의 달력 정보 반환", notes = "calendar 정보를 반환한다.")
    @GetMapping("/getCalendars/{articleno}")
    public ResponseEntity<?> getWholeCalendar(
    		@PathVariable @ApiParam(value = "게시글 정보 입력", required = true) int articleno) throws SQLException {
        try {
            Map<String, Integer> map = new HashMap<>();
            map.put("article_no", articleno);

            log.debug("map = {}", map);
            List<CalendarDto> list = boardService.getDateInfo(map);
            log.debug("list의 값은 = {}", list);

            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            return ResponseEntity.ok().headers(header).body(list);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    
    
    
    

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
