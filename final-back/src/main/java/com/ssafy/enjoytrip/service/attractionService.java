package com.ssafy.enjoytrip.service;

import java.util.List;

import com.ssafy.enjoytrip.dto.AttractionInfoDto;
import com.ssafy.enjoytrip.dto.GugunDto;
import com.ssafy.enjoytrip.dto.SidoDto;

public interface attractionService {

	List<SidoDto> getSido();

	List<GugunDto> getGugun(String sidoCode);
	
	List<AttractionInfoDto> getAttractionInfo(Integer sidoCode, Integer gugunCode, String keyword);
}
