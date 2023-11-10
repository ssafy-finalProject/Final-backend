package com.ssafy.enjoytrip.repository;

import java.util.List;

import com.ssafy.enjoytrip.dto.AttractionInfoDto;
import com.ssafy.enjoytrip.dto.GugunDto;
import com.ssafy.enjoytrip.dto.SidoDto;

public interface attractionRepository {

	List<SidoDto> getSido();

	List<GugunDto> getGugun(String code);

	List<AttractionInfoDto> getAttractionInfo(Integer sidoCode, Integer gugunCode, String keyword);
}
