package com.ssafy.trip.alarm.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.alarm.model.Alarm;
import com.ssafy.trip.alarm.model.service.AlarmService;
@CrossOrigin("*")
@RestController
@RequestMapping("/alarm")
public class AlarmController {
	
	private AlarmService service;

	public AlarmController(AlarmService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/insert")
	public int insert(@RequestBody Alarm alarm) throws SQLException{
		return service.insert(alarm);
	}
	
	@GetMapping("/count")
	public int getCount(@RequestParam String userId) throws SQLException{
		return service.getCount(userId);
	}
	
	
	@GetMapping("/list")
	public List<Alarm> list(@RequestParam String userId) throws SQLException{
		return service.selectAlarm(userId);
	}
	
	@DeleteMapping("/deleteNote")
	public int deleteNote(@RequestParam String userid) throws SQLException{
		return service.deleteNoteAlarm(userid);
	}
	
	@DeleteMapping("/deleteComment")
	public int deleteComment(@RequestParam String articleId) throws SQLException{
		return service.deleteCommentAlarm(articleId);
	}
}
