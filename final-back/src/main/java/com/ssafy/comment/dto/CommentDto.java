package com.ssafy.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

	private int commentId;
	private String userId;
	private int articleNo;
	private String commentText;
	private String commentDate;
}
