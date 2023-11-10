package com.ssafy.comment.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.comment.dto.CommentDto;

public interface CommentService {

	CommentDto getCommentById(int commentId) throws SQLException;
	void updateComment(CommentDto comment) throws SQLException;
	void deleteComment(int commentId) throws SQLException;
	void addComment(CommentDto comment) throws SQLException;
	List<CommentDto> getAllComments(Map<String, Object> param) throws SQLException;
	int getTotalCommentCount(Map<String, Object> param) throws SQLException;
}