package com.ssafy.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.board.BoardDto;
import com.ssafy.board.BoardListDto;
import com.ssafy.file.FileDto;
import com.ssafy.util.PageNavigation;

public interface boardService {

    void writeArticle(MultipartFile[] files,BoardDto boardDto) throws Exception;

    //	List<BoardDto> listArticle(Map<String, String> map) throws Exception;
    List<BoardDto> totalList() throws SQLException;

    BoardListDto listArticle(Map<String, String> map) throws Exception;
    
    BoardListDto listMyArticle(Map<String,String>map) throws Exception;

    PageNavigation makePageNavigation(Map<String, String> map) throws Exception;

    BoardDto getArticle(int articleNo) throws Exception;

    void updateHit(int articleNo) throws Exception;

    void modifyArticle(BoardDto boardDto) throws Exception;

    void deleteArticle(int articleNo) throws Exception;
    
    List<FileDto> getImages(int articleNo) throws SQLException;
}
