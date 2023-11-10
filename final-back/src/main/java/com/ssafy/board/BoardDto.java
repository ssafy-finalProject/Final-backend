package com.ssafy.board;

import java.util.List;

import com.ssafy.comment.dto.CommentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
	private int article_no;
	private String user_id;
	private String subject;
	private String content;
	private int hit;
	private String register_time;
	private List<CommentDto> comments;
}
