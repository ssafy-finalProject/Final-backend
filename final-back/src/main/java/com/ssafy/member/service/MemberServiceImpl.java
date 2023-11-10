package com.ssafy.member.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.member.MemberDto;
import com.ssafy.member.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	SqlSession session;
	
	@Autowired
	public MemberServiceImpl(SqlSession session) {
		super();
		this.session = session;
	}

	@Override
	public void registerMember(MemberDto memberDto) throws SQLException {
		// TODO Auto-generated method stub
		session.getMapper(MemberRepository.class).registerMember(memberDto);
	}

	@Override
	public boolean login(MemberDto memberDto) throws SQLException {
		MemberDto login = session.getMapper(MemberRepository.class).login(memberDto);
		if(login != null) return true;
		else return false;
	}

	@Override
	public void modifyMember(MemberDto memberDto) throws SQLException {
		// TODO Auto-generated method stub
		session.getMapper(MemberRepository.class).modifyMember(memberDto);
	}

	@Override
	public int deleteMember(String userId) {
		// TODO Auto-generated method stub
		return session.getMapper(MemberRepository.class).deleteMember(userId);
	}

	@Override
	public int idCheck(String userid) {
		// TODO Auto-generated method stub
		return session.getMapper(MemberRepository.class).idCheck(userid);
		// 값이 있으면 1, 없으면 0 처리
	}

	@Override
	public MemberDto findPwd(MemberDto memberDto) throws SQLException {
		MemberDto findPwd = session.getMapper(MemberRepository.class).findPwd(memberDto);
		return findPwd;
	}
	
	@Override
	public MemberDto findMember(String userId) throws SQLException {
		MemberDto findMember = session.getMapper(MemberRepository.class).findMember(userId);
		return findMember;
	}

}
