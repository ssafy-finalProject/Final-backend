package com.ssafy.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.enjoytrip.dto.AttractionInfoDto;
import com.ssafy.enjoytrip.dto.GugunDto;
import com.ssafy.enjoytrip.dto.SidoDto;
import com.ssafy.enjoytrip.service.attractionService;
import com.ssafy.enjoytrip.service.attractionServiceImpl;

//import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/att")
//@Slf4j 
public class attractionController {
	attractionService service;
	
	@Autowired
	public attractionController(attractionService service) {
		this.service = service;
	}

	@GetMapping("/newpage")
	public String mvAtt(HttpServletRequest request, Model model) {
		List<SidoDto> list = service.getSido();
		model.addAttribute("sidoList", list);
		return "newpage";
	}
	
	@GetMapping("/getGugun/{code}")
	public ResponseEntity<List<GugunDto>> getGugun(@PathVariable("code") String sidoCode) {
		List<GugunDto> list = service.getGugun(sidoCode);
		return new ResponseEntity<List<GugunDto>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/search/{sidoCode}/{gugunCode}/{keyword}")
	public ResponseEntity<List<AttractionInfoDto>> search(@PathVariable("sidoCode") Integer sidoCode, 
			@PathVariable("gugunCode") Integer gugunCode, @PathVariable("keyword") String keyword){
		System.out.println(sidoCode + " " + gugunCode + " " + keyword)	;
		List<AttractionInfoDto> list = service.getAttractionInfo(sidoCode, gugunCode, keyword);
		System.out.println(list);
		return new ResponseEntity<List<AttractionInfoDto>>(list, HttpStatus.OK);
	}
}
