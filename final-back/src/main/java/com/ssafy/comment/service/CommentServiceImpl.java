package com.ssafy.comment.service;

import com.ssafy.comment.dto.CommentDto;
import com.ssafy.comment.repository.CommentDao;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService{
    private CommentDao commentRepository;

    public CommentServiceImpl(CommentDao commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto getCommentById(int commentId) throws SQLException {
        return commentRepository.getCommentById(commentId);
    }

    @Override
    public void updateComment(CommentDto comment) throws SQLException {
        commentRepository.update(comment);
    }

    @Override
    public void deleteComment(int commentId) throws SQLException {
        commentRepository.delete(commentId);
    }

    @Override
    public void addComment(CommentDto comment) throws SQLException {
        commentRepository.addComment(comment);
    }

//    @Override
//    public List<CommentDto> getAllComments(Map<String, Object> param) throws SQLException {
//        return commentRepository.getAllComments(param);
//    }
//
//    @Override
//    public int getTotalCommentCount(Map<String, Object> param) throws SQLException {
//        return commentRepository.getTotalCommentCount(param);
//    }
}
