package com.ssafy.trip.note.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.note.model.Note;

public interface NoteService {

int insert(Note node) throws SQLException;
	
	int deleteNote(int num) throws SQLException;
	
	List<Note> selectReceiver(String receiver) throws SQLException;
	
	List<Note> selectSender(String sender) throws SQLException;

	int updateNote(int num) throws SQLException;
	
	Note getNote(int num) throws SQLException;
	
	int getNoteCnt(String receiver) throws SQLException;
}
