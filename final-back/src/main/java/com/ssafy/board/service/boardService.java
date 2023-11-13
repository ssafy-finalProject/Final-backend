package com.ssafy.board.service;

import java.util.List;
import java.util.Map;

import com.ssafy.board.BoardDto;
import com.ssafy.board.BoardListDto;
import com.ssafy.util.PageNavigation;

public interface boardService {
	
	void writeArticle(BoardDto boardDto) throws Exception;
	List<BoardDto> listArticle(Map<String, String> map) throws Exception;
//	BoardListDto listArticle(Map<String, String> map) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	BoardDto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	
	void modifyArticle(BoardDto boardDto) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
}
