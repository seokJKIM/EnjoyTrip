package com.ssafy.trip.comment.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.trip.comment.model.Comment;
import com.ssafy.trip.comment.model.service.CommentService;

@CrossOrigin("*")
@Controller
@RequestMapping("/comment")
public class CommentController {
	
	private CommentService service;

	public CommentController(CommentService service) {
		super();
		this.service = service;
	}

	@ResponseBody
	@GetMapping("/list")
	public List<Comment> list(@RequestParam int articleno) throws SQLException{
		return service.selectComment(articleno);
	}
	
	@ResponseBody
	@PostMapping("/insert")
	public Comment insert(@RequestBody Comment comment) throws SQLException {
		service.insert(comment);
		return comment;
	}
	
	@ResponseBody
	@DeleteMapping("/delete")
	public int delete(@RequestParam int num) throws SQLException {
		return service.deleteComment(num);
	}
	
	@ResponseBody
	@PutMapping("/modify")
	public Comment update(@RequestBody Comment comment) throws SQLException {
		service.updateComment(comment);
		return comment;
	}
	
}
