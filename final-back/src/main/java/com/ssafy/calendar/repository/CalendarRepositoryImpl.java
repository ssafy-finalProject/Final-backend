package com.ssafy.calendar.repository;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.calendar.CalendarDto;

@Repository
public class CalendarRepositoryImpl implements CalendarRepository {
	SqlSession session;
	
	@Autowired
	public CalendarRepositoryImpl(SqlSession session) {
		super();
		this.session = session;
	}
	
	String ns = "com.ssafy.calendar.repository.CalendarRepository.";
	
	@Override
	public void insertDate(CalendarDto calendarDto) throws SQLException {
		// TODO Auto-generated method stub
		session.insert(ns+"insertDate",calendarDto);
	}

	@Override
	public CalendarDto getDateInfo(int calendar_id) throws SQLException {
		// TODO Auto-generated method stub
		CalendarDto dto = session.selectOne(ns+"getDateInfo",calendar_id);
		return dto;
	}

	@Override
	public void modifyDateInfo(CalendarDto calendarDto) throws SQLException {
		session.update(ns+"modifyDateInfo",calendarDto);
		
	}

	@Override
	public void deleteDateInfo(int calendar_id) throws SQLException {
		// TODO Auto-generated method stub
		session.delete(ns+"deleteDateInfo",calendar_id);
	}

}
