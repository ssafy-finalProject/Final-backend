package com.ssafy.enjoytrip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttractionDetailDto {

	private int content_id;
	private String cat1;
	private String cat2;
	private String cat3;
	private String created_time;
	private String modified_time;
	private String booktour;
}
