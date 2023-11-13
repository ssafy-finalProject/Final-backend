package com.ssafy.restcontroller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.member.MemberDto;
import com.ssafy.member.service.MemberService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"},  maxAge = 6000)
@RestController
@RequestMapping("/member")
@Api("회원 관련 REST 테스트")
@Slf4j
public class RestMemberController {

    private MemberService service;

    public RestMemberController(MemberService service) {
        super();
        this.service = service;

    }

    @ApiOperation(value = "로그인 처리", notes = "로그인 가능 처리를 확인함.")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto member, HttpServletRequest request, HttpServletResponse response,
                                                     @RequestParam(value = "remember", required = false) String idsave) {
        Map<String, Object> list = new HashMap<>();
        try {
            boolean loginUser = service.login(member);
            if (loginUser) {
                MemberDto dto = new MemberDto();
                dto.setUserId(member.getUserId());
                MemberDto findMember = service.findMember(dto.getUserId());
                HttpSession session = request.getSession();
                session.setAttribute("userInfo", findMember);
                if ("on".equals(idsave)) {
                    Cookie cookie = new Cookie("userId", dto.getUserId());
                    cookie.setPath(request.getContextPath());

                    cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
                    response.addCookie(cookie);
                } else {
                    Cookie cookies[] = request.getCookies();
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if ("userId".equals(cookie.getName())) {
                                cookie.setMaxAge(0);
                                response.addCookie(cookie);
                                break;
                            }
                        }
                    }
                }
                list.put("resmsg", "로그인 성공");
                list.put("resdata", findMember);
            }
        } catch (Exception e) {
            e.printStackTrace();
            list.put("resmsg", "로그인 실패");
            list.put("resdata", e.getMessage());
        }
        return new ResponseEntity<Map<String, Object>>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "회원 가입", notes = "회원 가입 관련 처리를 진행합니다. return 은 void 처리")
    @PostMapping("/create")
    public ResponseEntity<String> memberCreate(@RequestBody MemberDto member) throws SQLException {
        log.debug("회원 추가={}", member);
        try {
            service.registerMember(member);
            return new ResponseEntity<>("회원 가입이 되었습니다", HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "아이디 중복 조회", notes = "idCheck를 통해서 값이 0이면 가능, 아니면 불가능 처리")
    @GetMapping("/idcheck/{id}")
    public Map<String, String> idCheck(@PathVariable String id) {
        int idCheck = service.idCheck(id);
        Map<String, String> map = new HashMap<>();
        map.put("result", idCheck == 0 ? "ok" : "no");
        return map;
    }

    @ApiOperation(value = "회원 삭제", notes = "아이디를 통한 회원 dto 삭제")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        log.debug("유저 아이디 = {}", userId);
        service.deleteMember(userId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "회원 아이디로 조회", notes = "아이디를 통한 회원정보 조회, 개인페이지 사용")
    @GetMapping("/{userId}")
    public ResponseEntity<MemberDto> GetUser(@PathVariable String userId) throws SQLException {
        MemberDto member = service.findMember(userId);
        log.debug("member dto 조회 = {}", member);
        return new ResponseEntity<MemberDto>(member, HttpStatus.OK);
    }

    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보 수정을 위한 엔드 포인트")
    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody MemberDto updatedMember) throws SQLException {
        try {
            updatedMember.setUserId(userId);
            service.modifyMember(updatedMember);

            return new ResponseEntity<>("회원 정보가 성공적으로 수정되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    
    @ApiOperation(value = "회원 아이디로 비밀번호 조회", notes = "비밀번호 찾기 위함")
    @GetMapping("/{userId}/{userName}")
    public ResponseEntity<MemberDto> findPass(@PathVariable String userId, @PathVariable String userName) throws SQLException {
    	MemberDto temp = new MemberDto();
    	temp.setUserId(userId);
    	temp.setUserName(userName);
        MemberDto member = service.findPwd(temp);
        return new ResponseEntity<MemberDto>(member, HttpStatus.OK);
    }
    

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
