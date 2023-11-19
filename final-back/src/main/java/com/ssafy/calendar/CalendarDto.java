package com.ssafy.calendar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CalendarDto {
	private int calendar_id;
	private int article_no;
	private String year;
	private String month;
	private String day;
	
}
