package com.ssafy.trip.comment.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.comment.model.Comment;

public interface CommentService {
	int insert(Comment comment) throws SQLException;

	List<Comment> selectComment(int articleno) throws SQLException;

	int updateComment(Comment comment) throws SQLException;

	int deleteComment(int num) throws SQLException;
	
}
