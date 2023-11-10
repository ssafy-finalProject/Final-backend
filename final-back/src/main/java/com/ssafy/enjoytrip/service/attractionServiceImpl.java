package com.ssafy.enjoytrip.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.dto.AttractionInfoDto;
import com.ssafy.enjoytrip.dto.GugunDto;
import com.ssafy.enjoytrip.dto.SidoDto;
import com.ssafy.enjoytrip.repository.attractionRepository;

@Service("attractionServiceImpl")
public class attractionServiceImpl implements attractionService {
//	SqlSession session;
//	
//	@Autowired
//	public attractionServiceImpl(SqlSession session) {
//		super();
//		this.session = session;
//	}
//
//	@Override
//	public List<SidoDto> getSido() {
//		List<SidoDto> list = session.getMapper(attractionRepository.class).getSido();
////		System.out.println("list : " + list);
//		return list;
//	}
//
//	@Override
//	public List<GugunDto> getGugun(String code) {
//		
//		return ;
//	}
//
//	@Override
//	public List<AttractionInfoDto> getAttractionInfo(String sidoCode, String gugunCode, String keyword) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	 
	private attractionRepository repo;
	
	@Autowired
	public attractionServiceImpl(attractionRepository repo) {
		this.repo = repo;
	}

	@Override
	public List<SidoDto> getSido() {
		System.out.println(repo.getSido());
		return repo.getSido();
	}

	@Override
	public List<GugunDto> getGugun(String code) {
		return repo.getGugun(code);
	}

	@Override
	public List<AttractionInfoDto> getAttractionInfo(Integer sidoCode, Integer gugunCode, String keyword) {
		return repo.getAttractionInfo(sidoCode, gugunCode, keyword);
	}
}
