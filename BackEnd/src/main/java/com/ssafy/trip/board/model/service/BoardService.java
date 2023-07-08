package com.ssafy.trip.board.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.board.model.Board;

public interface BoardService {

	public int writeBoard(Board board) throws SQLException;

	List<Board> selectBoard(int boardId) throws SQLException;
	
	List<Board> searchBoardUser(String userId) throws SQLException;

	Board searchBoard(int num) throws SQLException;
	
	List<Board> searchBoard(int boardId, int condition, String keyword) throws SQLException;

	int deleteBoard(int num) throws SQLException;

	int updateBoard(Board board) throws SQLException;
	
	int updateHits(Board board) throws SQLException;
}


