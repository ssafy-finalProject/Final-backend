package com.ssafy.restcontroller;

import com.ssafy.board.BoardDto;
import com.ssafy.board.service.boardService;
import com.ssafy.comment.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST}, maxAge = 6000)
@RestController
@Slf4j
@RequestMapping("/board")
@Api("게시판 관련 API 테스트")
public class RestBoardController {

    private boardService boardService;
    private CommentService commentService;

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
    @ApiOperation(value = "게시판 글 목록", notes = "모든 게시글을 반환한다.")
    @GetMapping
    public ResponseEntity<?> getBoardList(
            @RequestParam(required = false) @ApiParam(value = "검색할 키", defaultValue = "subject") String key,
            @RequestParam(required = false) @ApiParam(value = "검색어") String word,
            @RequestParam(required = false) @ApiParam(value ="시작 페이지") String pgno) throws SQLException {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("key", key);
            map.put("word", word);
            map.put("pgno", pgno);

            log.debug("map = {}", map);
            List<BoardDto> list = boardService.listArticle(map);
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

        return new ResponseEntity<BoardDto>(article, HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 입력", notes = "게시글에 대한 정보를 입력한다.")
    @PostMapping
    public ResponseEntity<?> writeArticle(@RequestBody @ApiParam(value = "게시글 정보 입력", required = true) BoardDto boardDto) throws Exception {
        try {
            log.debug("write article= {}", boardDto);
            boardService.writeArticle(boardDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
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


    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}