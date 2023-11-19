package com.ssafy.schedule.repository;

import java.sql.SQLException;

import com.ssafy.schedule.ScheduleDto;


public interface ScheduleRepository {
	void insertMemo(ScheduleDto scheduleDto) throws SQLException;
	ScheduleDto getMemo(int schedule_id)throws SQLException;
	void modifyMemo(ScheduleDto scheduleDto) throws SQLException;
	void deleteMemo(int schedule_id) throws SQLException;
}
