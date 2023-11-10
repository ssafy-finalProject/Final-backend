package com.ssafy.member.repository;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.member.MemberDto;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

	SqlSession session;

	@Autowired
	public MemberRepositoryImpl(SqlSession session) {
		super();
		this.session = session;
	}

	String ns = "com.ssafy.member.repository.MemberRepository.";
	
	@Override
	public void registerMember(MemberDto memberDto) throws SQLException {
		// TODO Auto-generated method stub
		session.insert(ns + "registerMember", memberDto);
	}

	@Override
	public MemberDto login(MemberDto memberDto) throws SQLException {
		MemberDto dto = session.selectOne(ns + "login", memberDto);
		return dto;
	}

	@Override
	public void modifyMember(MemberDto memberDto) throws SQLException {
		session.update(ns + "modifyMember", memberDto);
	}

	@Override
	public int deleteMember(String userId) {
		// TODO Auto-generated method stub
		return session.delete(ns + "deleteMember", userId);
		
	}

	@Override
	public int idCheck(String userid) {
		// TODO Auto-generated method stub
		return session.selectOne(ns + "idCheck", userid);
	}

	@Override
	public MemberDto findPwd(MemberDto memberDto) throws SQLException {
		MemberDto dto = session.selectOne(ns +"findPwd", memberDto);
		return dto;
	}
	
	@Override
	public MemberDto findMember(String userId) throws SQLException {
		MemberDto dto = session.selectOne(ns + "findMember", userId);
		return dto;
	}

}
