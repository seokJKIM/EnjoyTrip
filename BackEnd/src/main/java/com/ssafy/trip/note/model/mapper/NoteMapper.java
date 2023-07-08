package com.ssafy.trip.note.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.note.model.Note;

@Mapper
public interface NoteMapper {
	int insert(Note node) throws SQLException;
	
	int deleteNote(int num) throws SQLException;
	
	int updateNote(int num) throws SQLException;
	
	List<Note> selectReceiver(String receiver) throws SQLException;
	
	List<Note> selectSender(String sender) throws SQLException;

	Note getNote(int num) throws SQLException;
	
	int getNoteCnt(String receiver) throws SQLException;
}
