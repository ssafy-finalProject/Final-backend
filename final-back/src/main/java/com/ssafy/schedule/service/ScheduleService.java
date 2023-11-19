package com.ssafy.schedule.service;

import java.sql.SQLException;

import com.ssafy.schedule.ScheduleDto;


public interface ScheduleService {
	void insertMemo(ScheduleDto scheduleDto) throws Exception;
	ScheduleDto getMemo(int schedule_id)throws SQLException;
	void modifyMemo(ScheduleDto scheduleDto) throws SQLException;
	void deleteMemo(int schedule_id) throws SQLException;
}
