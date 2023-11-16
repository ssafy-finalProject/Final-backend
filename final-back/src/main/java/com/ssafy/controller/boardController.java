//package com.ssafy.controller;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.ssafy.board.BoardDto;
//import com.ssafy.board.service.boardService;
//import com.ssafy.comment.dto.CommentDto;
//import com.ssafy.member.MemberDto;
//import com.ssafy.util.PageNavigation;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Controller
//@RequestMapping("/article")
//@Slf4j
//public class boardController {
//	
//	private boardService boardService;
//	
//	public boardController(com.ssafy.board.service.boardService boardService) {
//	    super();
//	    this.boardService = boardService;
//	}
//
//	@GetMapping("/list")
//	public ModelAndView list(@RequestParam Map<String, String> map, ModelAndView mav, HttpServletRequest request,
//	        HttpServletResponse response) {
//	    HttpSession session = request.getSession();
//	    MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
//	    if (memberDto != null) {
//	        try { 
//	            PageNavigation pageNavigation;
////	            List<BoardDto> list = boardService.listArticle(map);
//	            pageNavigation = boardService.makePageNavigation(map);
////	            mav.addObject("articles", list);
//	            mav.addObject("navigation", pageNavigation);
//	            mav.addObject("pgno", map.get("pgno"));
//	            mav.addObject("key", map.get("key"));
//	            mav.addObject("word", map.get("word"));
//	            mav.setViewName("board/list");
//	        } catch (Exception e) {
//	            // TODO Auto-generated catch block
//	            e.printStackTrace();
//	            mav.setViewName("error/error");
//	        }
//
//	    }
//	    return mav;
//	}
//	
//	@GetMapping("/view")
//	public String view(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map, Model model)
//			throws Exception {
//		BoardDto boardDto = boardService.getArticle(articleNo);
//		boardService.updateHit(articleNo);
//		
//		model.addAttribute("article", boardDto);
////		model.addAttribute("comments", comments);
//		model.addAttribute("pgno", map.get("pgno"));
//		model.addAttribute("key", map.get("key"));
//		model.addAttribute("word", map.get("word"));
//		return "board/view";
//	}
//	
//	@GetMapping("/write")
//	public String mvwrite() {
//	    return "board/write";
//	}
//
//	@PostMapping("/write")
//	public ModelAndView write(BoardDto dto, ModelAndView mav) {
//	    try {
//	        boardService.writeArticle(dto);
//	        mav.setViewName("redirect:list?pgno=1&key=&word=");
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        mav.setViewName("error/error");
//	    }
//	    return mav;
//	}
//	
//	@PostMapping("/modify")
//    public String modify(BoardDto boardDto, @RequestParam Map<String, String> map,
//            RedirectAttributes redirectAttributes) throws Exception {
//        boardService.modifyArticle(boardDto);
//        redirectAttributes.addAttribute("pgno", map.get("pgno"));
//        redirectAttributes.addAttribute("key", map.get("key"));
//        redirectAttributes.addAttribute("word", map.get("word"));
//        return "redirect:/article/list";
//    }
//
//    @GetMapping("/delete")
//    public String delete(@RequestParam("article_no") int article_no, @RequestParam Map<String, String> map,
//            RedirectAttributes redirectAttributes) throws Exception {
//        boardService.deleteArticle(article_no);
//        redirectAttributes.addAttribute("pgno", map.get("pgno"));
//        redirectAttributes.addAttribute("key", map.get("key"));
//        redirectAttributes.addAttribute("word", map.get("word"));
//        return "redirect:/article/list";
//    }
//}
