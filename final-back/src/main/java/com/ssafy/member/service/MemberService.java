package com.ssafy.member.service;

import java.sql.SQLException;
import com.ssafy.member.MemberDto;

public interface MemberService {

	void registerMember(MemberDto memberDto) throws SQLException;

	boolean login(MemberDto memberDto) throws SQLException;

	void modifyMember(MemberDto memberDto) throws SQLException;

	int deleteMember(String userId);

	int idCheck(String userid);

	MemberDto findPwd(MemberDto memberDto) throws SQLException;

	MemberDto findMember(String userId) throws SQLException;
	
}
