package com.ssafy.enjoytrip.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.dto.AttractionInfoDto;
import com.ssafy.enjoytrip.dto.GugunDto;
import com.ssafy.enjoytrip.dto.SidoDto;

@Repository("attractionRepositoryImpl")
public class attractionRepositoryImpl implements attractionRepository {
	String ns = "com.ssafy.enjoytrip.repository.attractionRepository.";
	SqlSession session;
	
	@Autowired
	public attractionRepositoryImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<SidoDto> getSido() {
		System.out.println("sido : " + session.selectList(ns + "getSido"));
		return session.selectList(ns + "getSido");
	}

	@Override
	public List<GugunDto> getGugun(String code) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sido_code", code);
		System.out.println("gugun : "+session.selectList(ns + "getGugun", map));
		return session.selectList(ns + "getGugun", map);
	}

	@Override
	public List<AttractionInfoDto> getAttractionInfo(Integer sidoCode, Integer gugunCode, String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sido_code", sidoCode);
		map.put("gugun_code", gugunCode);
		map.put("keyword", keyword);
		System.out.println(sidoCode + " " + gugunCode + " " + keyword);
		return session.selectList(ns + "getAttractionInfo", map);
	}

}
