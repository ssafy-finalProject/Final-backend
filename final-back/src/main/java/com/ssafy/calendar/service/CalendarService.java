package com.ssafy.calendar.service;

import java.sql.SQLException;

import com.ssafy.calendar.CalendarDto;

public interface CalendarService {
	void insertDate(CalendarDto calendarDto) throws Exception;
	CalendarDto getDateInfo(int calendar_id)throws SQLException;
	void modifyDateInfo(CalendarDto calendarDto) throws SQLException;
	void deleteDateInfo(int calendar_id) throws SQLException;
}
