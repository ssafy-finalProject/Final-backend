package com.ssafy.schedule.repository;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.schedule.ScheduleDto;
@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{
	SqlSession session;
	
	@Autowired
	public ScheduleRepositoryImpl(SqlSession session) {
		super();
		this.session = session;
	}

	String ns = "com.ssafy.schedule.repository.ScheduleRepository.";
	@Override
	public void insertMemo(ScheduleDto scheduleDto) throws SQLException {
		// TODO Auto-generated method stub
		session.insert(ns+"insertMemo",scheduleDto);
	}

	@Override
	public ScheduleDto getMemo(int schedule_id) throws SQLException {
		ScheduleDto dto = session.selectOne(ns+"getMemo",schedule_id);
		return dto;
	}

	@Override
	public void modifyMemo(ScheduleDto scheduleDto) throws SQLException {
		session.update(ns+"modifyMemo",scheduleDto);
		
	}

	@Override
	public void deleteMemo(int schedule_id) throws SQLException {
		session.delete(ns+"deleteMemo",schedule_id);
		
	}

}
