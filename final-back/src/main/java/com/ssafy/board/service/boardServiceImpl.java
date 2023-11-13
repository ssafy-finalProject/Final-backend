package com.ssafy.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.board.BoardListDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.BoardDto;
import com.ssafy.board.repository.BoardRepository;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.SizeConstant;

@Service
public class boardServiceImpl implements boardService {

	SqlSession session;
	
	@Autowired
	public boardServiceImpl(SqlSession session) {
		super();
		this.session = session;
	}

	@Override
	public void writeArticle(BoardDto boardDto) throws Exception {
		session.getMapper(BoardRepository.class).writeArticle(boardDto);
	}

	@Override
	public List<BoardDto> listArticle(Map<String, String> map) throws Exception {
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
//		int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
//		int start = currentPage * sizePerPage - sizePerPage;
//		param.put("start", start);
//		param.put("listsize", sizePerPage);
//
//		String key = map.get("key");
//		param.put("key", key == null ? "" : key);
//		if ("user_id".equals(key))
//			param.put("key", key == null ? "" : "b.user_id");
//		List<BoardDto> list = session.getMapper(BoardRepository.class).listArticle(param);
//
//		if ("user_id".equals(key))
//			param.put("key", key == null ? "" : "user_id");
//		int totalArticleCount = session.getMapper(BoardRepository.class).getTotalArticleCount(param);
//		int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;
//
//		BoardListDto boardListDto = new BoardListDto();
//		boardListDto.setArticles(list);
//		boardListDto.setCurrentPage(currentPage);
//		boardListDto.setTotalPageCount(totalPageCount);
//
//		return boardListDto;
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		if("user_id".equals(key))
			key = "b.user_id";
		param.put("key", key == null ? "" : key);
		System.out.println("key = " + key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		String word = map.get("word");
		System.out.println("word = " + word);
		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		System.out.println(map.get("pgno"));
//		System.out.println(pgNo);
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		System.out.println("start = " + start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		System.out.println("listsize = " + param.get("listsize"));

		List<BoardDto> listArticle = session.getMapper(BoardRepository.class).listArticle(param);

		System.out.println("test");
		System.out.println("listArticle " + listArticle );
		System.out.println("param : " + param);
		return listArticle;
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		System.out.println(map.get("pgno"));
		int currentPage = Integer.parseInt(map.get("pgno"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		if ("user_id".equals(key))
			key = "user_id";
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int totalCount = session.getMapper(BoardRepository.class).getTotalArticleCount(param);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
	}

	@Override
	public BoardDto getArticle(int articleNo) throws Exception {
		BoardDto dto = session.getMapper(BoardRepository.class).getArticle(articleNo);
		return dto;
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		session.getMapper(BoardRepository.class).updateHit(articleNo);
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws Exception {
		session.getMapper(BoardRepository.class).modifyArticle(boardDto);
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		session.getMapper(BoardRepository.class).deleteArticle(articleNo);
	}


}
