package com.ssafy.member.repository;

import java.sql.SQLException;

import com.ssafy.member.MemberDto;

public interface MemberRepository {

	void registerMember(MemberDto memberDto) throws SQLException;

	MemberDto login(MemberDto memberDto) throws SQLException;

	void modifyMember(MemberDto memberDto) throws SQLException;

	int deleteMember(String userId);

	int idCheck(String userid);

	MemberDto findPwd(MemberDto memberDto) throws SQLException;

	MemberDto findMember(String userId) throws SQLException;
	
}
