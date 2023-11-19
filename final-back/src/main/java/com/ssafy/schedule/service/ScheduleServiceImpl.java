package com.ssafy.schedule.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.schedule.ScheduleDto;
import com.ssafy.schedule.repository.ScheduleRepository;

@Service
public class ScheduleServiceImpl implements ScheduleService{
	SqlSession session;
	
	@Autowired
	public ScheduleServiceImpl(SqlSession session) {
		super();
		this.session = session;
	}

	@Override
	public void insertMemo(ScheduleDto scheduleDto) throws Exception {
		// TODO Auto-generated method stub
		session.getMapper(ScheduleRepository.class).insertMemo(scheduleDto);
	}

	@Override
	public ScheduleDto getMemo(int schedule_id) throws SQLException {
		ScheduleDto dto = session.getMapper(ScheduleRepository.class).getMemo(schedule_id);
		return dto;
	}

	@Override
	public void modifyMemo(ScheduleDto scheduleDto) throws SQLException {
		session.getMapper(ScheduleRepository.class).modifyMemo(scheduleDto);
		
	}

	@Override
	public void deleteMemo(int schedule_id) throws SQLException {
		session.getMapper(ScheduleRepository.class).deleteMemo(schedule_id);
		
	}

}
