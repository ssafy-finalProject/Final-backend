package com.ssafy.board.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.board.BoardDto;

public interface BoardRepository {

	void writeArticle(BoardDto boardDto) throws SQLException;
	List<BoardDto> totalList() throws SQLException;
	List<BoardDto> listArticle(Map<String, Object> param) throws SQLException;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	BoardDto getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	
	void modifyArticle(BoardDto boardDto) throws SQLException;
	void deleteArticle(int articleNo) throws SQLException;
}
