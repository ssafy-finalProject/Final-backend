package com.ssafy.board;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.comment.dto.CommentDto;
import com.ssafy.detail.dto.DetailDto;
import com.ssafy.detail.dto.dataToSendDto;
import com.ssafy.file.FileDto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
	private int article_no;
	private String user_id;
	private String subject;
	private String content;
	private int hit;
	private String register_time;
	private MultipartFile[] files;
	private List<FileDto> dtos;
	private List<DetailDto> details;
	
	private dataToSendDto dataToSend;
//	private List<CommentDto> comments;
}
