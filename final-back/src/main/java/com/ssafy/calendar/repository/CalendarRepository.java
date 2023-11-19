package com.ssafy.calendar.repository;

import java.sql.SQLException;

import com.ssafy.calendar.CalendarDto;

public interface CalendarRepository {

	void insertDate(CalendarDto calendarDto) throws SQLException;
	CalendarDto getDateInfo(int calendar_id)throws SQLException;
	void modifyDateInfo(CalendarDto calendarDto) throws SQLException;
	void deleteDateInfo(int calendar_id) throws SQLException;
}
