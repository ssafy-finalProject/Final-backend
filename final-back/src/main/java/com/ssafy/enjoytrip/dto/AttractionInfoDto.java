package com.ssafy.enjoytrip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttractionInfoDto {

	private int content_id;
	private int content_type_id;
	private String title;
	private String addr1;
	private String addr2;
	private String zipcode;
	private String tel;
	private String first_image;
	private String first_image2;
	private int readcount;
	private Integer sido_code;
	private Integer gugun_code;
	private double latitude;
	private double longitude;
	private String mlevel;
}
