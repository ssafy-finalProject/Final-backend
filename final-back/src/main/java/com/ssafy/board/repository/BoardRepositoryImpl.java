package com.ssafy.board.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.board.BoardDto;

@Repository
public class BoardRepositoryImpl implements BoardRepository {

	SqlSession session;
	
	@Autowired
	public BoardRepositoryImpl(SqlSession session) {
		super();
		this.session = session;
	}

	String ns = "com.ssafy.board.repository.BoardRepository.";

	@Override
	public void writeArticle(BoardDto boardDto) throws SQLException {
		// TODO Auto-generated method stub
		session.insert(ns + "writeArticle", boardDto);
	}

	@Override
	public List<BoardDto> totalList() throws SQLException {
		List<BoardDto> list = session.selectList(ns + "totalList");
		return list;
	}

	@Override
	public List<BoardDto> listArticle(Map<String, Object> param) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("param : " + param);
		List<BoardDto> list = session.selectList(ns + "listArticle", param);
		return list;
	}

	@Override
	public int getTotalArticleCount(Map<String, Object> param) throws SQLException {
		int cnt = session.selectOne(ns + "getTotalArticleCount", param);
		return cnt;
	}

	@Override
	public BoardDto getArticle(int articleNo) throws SQLException {
		// TODO Auto-generated method stub
		BoardDto dto = session.selectOne(ns + "getArticle", articleNo);
		return dto;
	}

	@Override
	public void updateHit(int articleNo) throws SQLException {
		session.update(ns + "updateHit", articleNo);
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws SQLException {
		session.update(ns + "modifyArticle", boardDto);
	}

	@Override
	public void deleteArticle(int articleNo) throws SQLException {
		session.delete(ns +"deleteArticle", articleNo);
	}

}
