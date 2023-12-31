package com.ssafy.board.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.spi.CalendarDataProvider;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.board.BoardDto;
import com.ssafy.calendar.CalendarDto;
import com.ssafy.detail.dto.DetailDto;
import com.ssafy.file.FileDto;

public interface BoardRepository {

	void writeArticle(BoardDto boardDto) throws SQLException;
	List<BoardDto> totalList() throws SQLException;
	List<BoardDto> listArticle(Map<String, Object> param) throws SQLException;
	List<BoardDto> listMyArticle(Map<String,Object>param) throws SQLException;
	List<BoardDto> getWholeList(Map<String,Object>param) throws SQLException;
	List<DetailDto>getDetails(Map<String,Object>param)throws Exception;
	List<CalendarDto>getDateInfo(Map<String,Object>param)throws Exception;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	BoardDto getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	
	void modifyArticle(BoardDto boardDto) throws SQLException;
	void deleteArticle(int articleNo) throws SQLException;
	void fileUpload(FileDto filedto);
	List<FileDto> getImages(int articleNo) throws SQLException;
	void insertDetail(DetailDto detailDto) throws SQLException;
	void insertDate(CalendarDto calendarDto) throws SQLException;
}
