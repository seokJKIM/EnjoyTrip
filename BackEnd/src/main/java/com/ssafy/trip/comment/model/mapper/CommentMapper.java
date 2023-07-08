package com.ssafy.trip.comment.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.comment.model.Comment;

@Mapper
public interface CommentMapper {
	int insert(Comment comment) throws SQLException;
	
	List<Comment> selectComment(int articleno) throws SQLException;
	
	int updateComment(Comment comment) throws SQLException;
	
	int deleteComment(int num) throws SQLException;
	
}
	