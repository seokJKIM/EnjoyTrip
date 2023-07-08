package com.ssafy.trip.note.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.trip.note.model.Note;
import com.ssafy.trip.note.model.mapper.NoteMapper;

@Service
public class NoteServiceImpl implements NoteService {

	NoteMapper mapper;
	
	public NoteServiceImpl(NoteMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public int insert(Note node) throws SQLException {
		return mapper.insert(node);
	}

	@Override
	public int deleteNote(int num) throws SQLException {
		return mapper.deleteNote(num);
	}

	@Override
	public int updateNote(int num) throws SQLException {
		return mapper.updateNote(num);
	}

	@Override
	public List<Note> selectReceiver(String receiver) throws SQLException {
		return mapper.selectReceiver(receiver);
	}

	@Override
	public List<Note> selectSender(String sender) throws SQLException {
		return mapper.selectSender(sender);
	}

	@Override
	public Note getNote(int num) throws SQLException {
		return mapper.getNote(num);
	}

	@Override
	public int getNoteCnt(String receiver) throws SQLException {
		return mapper.getNoteCnt(receiver);
	}
	
	
}
