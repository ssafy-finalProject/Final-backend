package com.ssafy.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.comment.dto.CommentDto;
import com.ssafy.file.FileDto;

import lombok.*;

@Getter
@Setter
@ToString
public class BoardDto {
	private int article_no;
	private String user_id;
	private String subject;
	private String content;
	private int hit;
	private String register_time;
	private MultipartFile[] files;
//	private List<CommentDto> comments;
}
