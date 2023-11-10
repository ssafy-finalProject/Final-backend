package com.ssafy.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class MemberDto {

	private String userId;
	private String userName;
	private String userPass;
	private String joinDate;
//	private String salt;
	
}
