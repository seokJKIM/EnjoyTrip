package com.ssafy.trip.comment.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.trip.comment.model.Comment;
import com.ssafy.trip.comment.model.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService{

	CommentMapper mapper;
	
	public CommentServiceImpl(CommentMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public int insert(Comment comment) throws SQLException {
		return mapper.insert(comment);
	}

	@Override
	public List<Comment> selectComment(int articleno) throws SQLException {
		return mapper.selectComment(articleno);
	}

	@Override
	public int updateComment(Comment comment) throws SQLException {
		return mapper.updateComment(comment);
	}

	@Override
	public int deleteComment(int num) throws SQLException {
		return mapper.deleteComment(num);
	}

}
