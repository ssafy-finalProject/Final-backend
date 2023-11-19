package com.ssafy.calendar.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.calendar.CalendarDto;
import com.ssafy.calendar.repository.CalendarRepository;

@Service
public class CalendarServiceImpl implements CalendarService {
	SqlSession session;
	
	
	@Autowired
	public CalendarServiceImpl(SqlSession session) {
		super();
		this.session = session;
	}

	@Override
	public void insertDate(CalendarDto calendarDto) throws Exception {
		session.getMapper(CalendarRepository.class).insertDate(calendarDto);
		
	}

	@Override
	public CalendarDto getDateInfo(int calendar_id) throws SQLException {
		CalendarDto dto = session.getMapper(CalendarRepository.class).getDateInfo(calendar_id);
		return dto;
	}

	@Override
	public void modifyDateInfo(CalendarDto calendarDto) throws SQLException {
		session.getMapper(CalendarRepository.class).modifyDateInfo(calendarDto);
		
	}

	@Override
	public void deleteDateInfo(int calendar_id) throws SQLException {
		session.getMapper(CalendarRepository.class).deleteDateInfo(calendar_id);
		
	}

}
