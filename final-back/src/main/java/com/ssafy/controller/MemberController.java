//package com.ssafy.controller;
//
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.ssafy.member.MemberDto;
//import com.ssafy.member.service.MemberService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Controller
//@RequestMapping("/member")
//@Slf4j
//public class MemberController {
//
//	private MemberService memberService;
//
//	@Autowired
//	public MemberController(MemberService memberService) {
//		super();
//		this.memberService = memberService;
//	}
//
//	@GetMapping("/login")
//	public String login() {
//		return "member/login";
//	}
//
//	@PostMapping("/login")
//		public String loginMethod(@RequestParam("userid") String id, MemberDto memberDto,  @RequestParam(name = "remember", required = false) String remember
//				,HttpServletRequest request, HttpServletResponse response) throws SQLException {
//			boolean login = memberService.login(memberDto);
//			log.info("member={}", memberDto);
//			if(login) {
//				MemberDto findMember = memberService.findMember(memberDto.getUserid());
//				request.getSession().setAttribute("userInfo", findMember);
//
//				Cookie cookie = new Cookie("userId", id);
//				cookie.setPath("/");
//				if("ok".equals(remember))
//					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
//				else
//					cookie.setMaxAge(0);
//				response.addCookie(cookie);
//				log.info("cookie ={}", cookie);
//				log.info("dto={}", memberDto);
//				return "redirect:/";
//			}
//			else  {
//				return "index";
//			}
//		}
//
//
//	@GetMapping("/logout")
//	public String logout(HttpServletRequest request) {
//		try {
//			request.getSession().invalidate();
//			return "redirect:/";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "error/error";
//		}
//	}
//
//	@GetMapping("/join")
//	public String join() {
//		return "member/join";
//	}
//
//	@PostMapping("/join")
//	public String joinMem(MemberDto memberDto) throws SQLException {
//		memberService.registerMember(memberDto);
//		return "redirect:/";
//	}
//
//	@GetMapping("/checkId/{id}")
//	@ResponseBody
//	public Map<String, String> checkId(@PathVariable("id") String id) throws SQLException {
//
//		MemberDto findMember = memberService.findMember(id);
//		Map<String, String> map = new HashMap<String, String>();
//		if(findMember == null ) {
//			map.put("result", "ok");
//		} else {
//			map.put("result", "no");
//		}
//		return map;
//	}
//
//	@GetMapping("/mypage")
//	public String myPage() {
//		return "member/mypage";
//	}
//
//	@PostMapping("/modify")
//    public String modifyMember(MemberDto member, HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//        MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
//        try {
//            memberService.modifyMember(memberDto);
//            memberDto.setUserid(memberDto.getUserid());
//            memberDto.setUsername(member.getUsername());
//            memberDto.setUserpass(member.getUserpass());
//            session.setAttribute("userInfo", memberDto);
//            return "redirect:mypage";
//        }catch (Exception e) {
//            e.printStackTrace();
//            request.setAttribute("msg", "정보 수정 중 오류 발생");
//            return "error/error";
//        }
//    }
//
//
//	@GetMapping("/delete")
//    public String deleteMember(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//        MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
//        try {
//            memberService.deleteMember(memberDto.getUserid());
//            session.invalidate();
//            return "redirect:/";
//        }catch (Exception e) {
//            e.printStackTrace();
//            request.setAttribute("msg", "멤버삭제 중 오류 발생");
//            return "mypage";
//        }
//    }
//}
