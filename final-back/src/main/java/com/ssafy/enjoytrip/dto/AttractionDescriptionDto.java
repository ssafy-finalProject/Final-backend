package com.ssafy.enjoytrip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttractionDescriptionDto {

	private int content_id;
	private String homepage;
	private String overview;
	private String telname;
}
