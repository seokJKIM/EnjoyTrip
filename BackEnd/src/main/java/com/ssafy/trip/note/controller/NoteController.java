package com.ssafy.trip.note.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.trip.note.model.Note;
import com.ssafy.trip.note.model.service.NoteService;

@CrossOrigin("*")
@Controller
@RequestMapping("/note")
public class NoteController {
	
	private NoteService service;

	public NoteController(NoteService service) {
		super();
		this.service = service;
	}
	
	@ResponseBody
	@PostMapping("/insert")
	public Note insert(@RequestBody Note note) throws SQLException{
		service.insert(note);
		return note;
	}
	
	@ResponseBody
	@GetMapping("/searchReceive")
	public List<Note> getReceiverList(@RequestParam String receiver) throws SQLException{
		return service.selectReceiver(receiver);
	}
	
	@ResponseBody
	@GetMapping("/searchSend")
	public List<Note> getSendList(@RequestParam String sender) throws SQLException{
		return service.selectSender(sender);
	}
	
	@ResponseBody
	@PutMapping("/modify")
	public int modify(@RequestBody Note note) throws SQLException{
		return service.updateNote(note.getNum());
	}
	
	@ResponseBody
	@GetMapping("/detail")
	public Note getNote(@RequestParam int num) throws SQLException{
//		return service.selectSender(sender);
		return service.getNote(num);
	}
	
	@ResponseBody
	@GetMapping("/notecount")
	public int getNoteCount(@RequestParam String userId) throws SQLException{
		return service.getNoteCnt(userId);
	}
	
	
}
