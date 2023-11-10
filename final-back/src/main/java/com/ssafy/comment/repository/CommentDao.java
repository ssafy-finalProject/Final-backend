package com.ssafy.comment.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.comment.dto.CommentDto;

public interface CommentDao {

	CommentDto getCommentById(int commentId) throws SQLException;
    void update(CommentDto comment) throws SQLException;
    void delete(int commentId) throws SQLException;
    void addComment(CommentDto comment) throws SQLException;
    List<CommentDto> getAllComments(Map<String, Object> param) throws SQLException;
    int getTotalCommentCount(Map<String, Object> param) throws SQLException;
}
